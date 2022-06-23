package com.example.programm_8.Client;

import com.example.programm_8.Commands.Connect;
import com.example.programm_8.Data.User;
import com.example.programm_8.Utility.Deserializer;
import com.example.programm_8.Utility.Serializer;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Client {

    private InetSocketAddress addr;
    private DatagramChannel datagramChannel;
    private Deserializer deserializer;
    private Serializer serializer;
    private User user;

    public Client(int p, User user) throws IOException {
        addr = new InetSocketAddress("localhost", p);
        this.user=user;
        deserializer = Deserializer.get();
        serializer = new Serializer();
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            String answer = (run(new Connect("connect", "desc", user)));
            if(!(answer.contains("Выполнение успешно."))){
                throw new IOException(answer);
            }
    }

    public User getUser() {
        return user;
    }

    public String run(Serializable o1) {
        String out = "";
        try {
            open();
            sendObject(o1);
            out = (String) getObject();
            close();
        } catch (IOException e) {
            return "Отсутствует связь с сервером.";
        }
        return out;
    }

    private void sendObject(Serializable object) throws IOException {
        datagramChannel.send(serializer.serialize(object), addr);
    }

    private Object getObject() throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(10 * 1024);
        WaitAnswer answer = new WaitAnswer();
        answer.waitReceive(datagramChannel, buf);
        Object o = deserializer.deSerialization(buf);
        return o;
    }

    private void close() throws IOException {
        datagramChannel.close();
    }

    private void open() throws IOException {
        datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
    }

    //    private void findServer() throws Disconnect {
//        System.out.println("Подключаюсь к серверу...");
//        String result = run(new Connect("connect","подключение к серверу."));
//        if(!(result.equals("Выполнение успешно.\n"))) {
//            throw new Disconnect("Подключение не установлено");
//        }
//        System.out.println(result);
//    }
    class WaitAnswer {

        public boolean waitReceive(DatagramChannel channel, ByteBuffer byteBuffer, long timeout) throws IOException {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < (timeout * 1000)) {
                SocketAddress address = channel.receive(byteBuffer);
                if (address != null) {
                    return true;
                }
            }
            throw new IOException();
        }

        public boolean waitReceive(DatagramChannel channel, ByteBuffer byteBuffer) throws IOException {
            return waitReceive(channel, byteBuffer, 5);
        }


    }
}