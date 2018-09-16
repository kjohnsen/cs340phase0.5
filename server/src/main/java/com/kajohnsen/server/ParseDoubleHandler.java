package com.kajohnsen.server;

import com.kajohnsen.shared.IStringProcessor;
import com.kajohnsen.shared.Results;

public class ParseDoubleHandler extends HandlerBase {
    @Override
    protected Object processString(String requestString) {
        IStringProcessor sp = StringProcessor.getInstance();
        System.out.println("Parsing double");
        return sp.parseDouble(requestString);
    }
}
