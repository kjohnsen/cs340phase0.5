package com.kajohnsen.server;

import com.kajohnsen.shared.Results;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class Serializer {
    public static String serializeResults(Results results) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream so = new ObjectOutputStream(bo);
        so.writeObject(results);
        so.flush();
        return bo.toString();
    }
}
