package com.kajohnsen.server;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private HttpServer httpServer;

    public void start() throws IOException {
        int port = 8000;
        httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.setExecutor(null);
        httpServer.createContext("/trim", new TrimHandler());
        httpServer.createContext("/toLowerCase", new ToLowerCaseHandler());
        httpServer.createContext("/parseDouble", new ParseDoubleHandler());
        httpServer.createContext("/execCommand", new ExecCommandHandler());
        httpServer.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                throw new IOException();
            }
        });
        httpServer.start();
        System.out.println("server started at " + port);
    }

    public static void main(String[] args) {
        try {
            new Server().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
