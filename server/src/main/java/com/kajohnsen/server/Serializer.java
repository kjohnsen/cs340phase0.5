package com.kajohnsen.server;

import com.kajohnsen.shared.CommandData;
import com.kajohnsen.shared.Results;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class Serializer {
    public static void serializeResults(Results results, OutputStream os) throws IOException {
//        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream so = new ObjectOutputStream(os);
        so.writeObject(results);
        so.flush();
//        return bo.toString();
    }

    public static CommandData deserializeCommandData(InputStream is) throws IOException {
        ObjectInputStream si = new ObjectInputStream(is);
        try {
            return (CommandData) si.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
