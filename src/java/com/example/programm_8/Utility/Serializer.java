package com.example.programm_8.Utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

public class Serializer implements AutoCloseable{
    ByteArrayOutputStream outputStream;
    ObjectOutputStream oos;



    public Serializer() {
    }

    public ByteBuffer serialize(Serializable obj){
        try{
            outputStream = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ByteBuffer.wrap(outputStream.toByteArray());
    }



    @Override
    public void close(){
        try {
            outputStream.close();
            oos.close();
        } catch (IOException ignored) {}
    }
}

