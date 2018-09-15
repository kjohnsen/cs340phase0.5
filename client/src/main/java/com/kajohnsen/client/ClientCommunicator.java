package com.kajohnsen.client;

class ClientCommunicator {
    private static final ClientCommunicator ourInstance = new ClientCommunicator();

    static ClientCommunicator getInstance() {
        return ourInstance;
    }

    private ClientCommunicator() {
    }
}
