package com.kajohnsen.client;

public class ServerException extends Exception {
    public ServerException() {
    }

    public ServerException(String s) {
        super(s);
    }

    public ServerException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
