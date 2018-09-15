package com.kajohnsen.server;

import com.kajohnsen.shared.IStringProcessor;

public class StringProcessor implements IStringProcessor {
    private static final StringProcessor ourInstance = new StringProcessor();

    public static StringProcessor getInstance() {
        return ourInstance;
    }

    private StringProcessor() {
    }

    @Override
    public String toLowerCase(String s) {
        return s.toLowerCase();
    }

    @Override
    public String trim(String s) {
        return s.trim();
    }

    @Override
    public Double parseDouble(String s) throws NumberFormatException {
        return Double.parseDouble(s);
    }
}
