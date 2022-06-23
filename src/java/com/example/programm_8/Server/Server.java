package com.example.programm_8.Server;

import com.example.programm_8.Commands.AbstractCommand;
import com.example.programm_8.Utility.*;
import com.example.programm_8.exceptions.PropertiesReadingException;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.concurrent.*;

public class Server {
    private DatagramSocket datagramSocket;
    private int port;
    private Serializer serializer;
    private int portForAns;
    private InetAddress hostForAns;
    private Deserializer deserializer;
    private AbstractCommand command;

    public Server(int p) throws PropertiesReadingException {
        this.port = p;
        boolean success = false;
        while (!success) {
            try {
                datagramSocket = new DatagramSocket(port);
                success = true;
                System.out.println("Сервер поднят и доступен по порту " + port + " .");
            } catch (SocketException e) {
                port = (int) (Math.random() * 20000 + 10000);
            }
        }
        PropHelper.getProperties();
        DataBaseHandler dataBaseHandler = new DataBaseHandler(PropHelper.getHost(), PropHelper.getPort(), PropHelper.getUser(), PropHelper.getPassword(), PropHelper.getBasename());
        DataBaseUserManager userManager = new DataBaseUserManager(dataBaseHandler);
        DataBaseCollectionManager dataBaseCollectionManager = new DataBaseCollectionManager(dataBaseHandler, userManager);
        Module.setCollectionManager(new CollectionManager(dataBaseCollectionManager));
        serializer = new Serializer();
        deserializer = Deserializer.get();
    }

    public void run() {
        try {
            ForkJoinPool pool = ForkJoinPool.commonPool();
            pool.invoke(new ForkJoinTask<Object>() {
                @Override
                public Object getRawResult() {
                    return null;
                }

                @Override
                protected void setRawResult(Object value) {

                }

                @Override
                protected boolean exec() {
                    Object o = null;
                    try {
                        while (o == null) {
                            o=getObject();
                            command = (AbstractCommand) o;
                        }
                        B b = getB();
                        b.setHostForAns2(hostForAns);
                        b.setPortForAns2(portForAns);
                        new Thread(b).start();
                        return true;
                    } catch (Exception e) {
                        System.out.println((String) o);
                        e.printStackTrace();
                        return true;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private B getB(){
        return new B();
    }
    class B implements Runnable {

        private int portForAns2;
        private InetAddress hostForAns2;

        public void setPortForAns2(int portForAns2) {
            this.portForAns2 = portForAns2;
        }

        public void setHostForAns2(InetAddress hostForAns2) {
            this.hostForAns2 = hostForAns2;
        }

        @Override
        public void run() {
            boolean result = Module.running(command);
            if (result) {
                Module.addMessage("Выполнение успешно.");
            } else {
                Module.addMessage("Выполнить команду не удалось.");
            }
            System.out.println(Module.getMessage());
            ExecutorService service = Executors.newCachedThreadPool();
            C c = getC();
            c.setHostForAns3(hostForAns2);
            c.setPortForAns3(portForAns2);
            service.execute(c);
            service.shutdown();
        }
    }

    public C getC(){
        return new C();
    }

    class C implements Runnable{

        private int portForAns3;
        private InetAddress hostForAns3;

        public void setPortForAns3(int portForAns3) {
            this.portForAns3 = portForAns3;
        }

        public void setHostForAns3(InetAddress hostForAns3) {
            this.hostForAns3 = hostForAns3;
        }

        @Override
        public void run() {
            try {
                sendObject(Module.messageFlush(), hostForAns3, portForAns3);
            } catch (IOException ignore) {
                //ignore
            }
        }
    }

    private Object getObject() throws IOException {
        DatagramPacket datagramPacketget = new DatagramPacket(new byte[10 * 1024], 10 * 1024);
        datagramSocket.receive(datagramPacketget);
        hostForAns = datagramPacketget.getAddress();
        portForAns = datagramPacketget.getPort();
        return deserializer.deSerialization(ByteBuffer.wrap(datagramPacketget.getData()));
    }

    private void sendObject(Serializable o, InetAddress host, int port) throws IOException {
        ByteBuffer buf = serializer.serialize(o);
        DatagramPacket datagramPacketsend = new DatagramPacket(buf.array(), buf.array().length, host, port);
        datagramSocket.send(datagramPacketsend);
    }
}
