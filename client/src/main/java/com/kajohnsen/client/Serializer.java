package com.kajohnsen.client;

import com.kajohnsen.shared.CommandData;
import com.kajohnsen.shared.Results;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Serializer {
    public static Results deserializeResults(InputStream resultsStream) throws IOException {
        ObjectInputStream si = new ObjectInputStream(resultsStream);
        try {
            return (Results) si.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void serializeCommandData(CommandData cd, OutputStream os) throws IOException {
        ObjectOutputStream so = new ObjectOutputStream(os);
        so.writeObject(cd);
        so.flush();
    }
}
