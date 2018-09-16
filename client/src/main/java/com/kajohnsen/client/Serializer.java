package com.kajohnsen.client;

import com.kajohnsen.shared.Results;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Serializer {
    public static Results deserializeResults(String serResults) throws IOException {
        byte b[] = serResults.getBytes();
        ByteArrayInputStream bi = new ByteArrayInputStream(b);
        ObjectInputStream si = new ObjectInputStream(bi);
        try {
            return (Results) si.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
