package com.kajohnsen.client;

import com.kajohnsen.shared.IStringProcessor;
import com.kajohnsen.shared.Results;

class StringProcessorProxy implements IStringProcessor {
    private static final StringProcessorProxy ourInstance = new StringProcessorProxy();

    static StringProcessorProxy getInstance() {
        return ourInstance;
    }

    private StringProcessorProxy() {
    }

    @Override
    public String toLowerCase(String s) {
        Results results = ClientCommunicator.getInstance().send(s, "toLowerCase");
    }

    @Override
    public String trim(String s) {
        Results results = ClientCommunicator.getInstance().send(s, "trim");
    }

    @Override
    public Double parseDouble(String s) {
        Results results = ClientCommunicator.getInstance().send(s, "parseDouble");
    }
}
