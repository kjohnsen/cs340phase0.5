package com.kajohnsen.server;

import com.kajohnsen.shared.IStringProcessor;
import com.kajohnsen.shared.Results;

public class TrimHandler extends HandlerBase {
    @Override
    protected Object processString(String requestString) {
        IStringProcessor sp = StringProcessor.getInstance();
        System.out.println("Trimming string");
        return sp.trim(requestString);
    }
}
