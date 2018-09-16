package com.kajohnsen.server;

import com.kajohnsen.shared.IStringProcessor;
import com.kajohnsen.shared.Results;

public class ToLowerCaseHandler extends HandlerBase {
    @Override
    protected Object processString(String requestString) {
        IStringProcessor sp = StringProcessor.getInstance();
        System.out.println("Converting string to lower-case");
        return sp.toLowerCase(requestString);
    }
}
