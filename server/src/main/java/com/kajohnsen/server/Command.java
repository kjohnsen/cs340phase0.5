package com.kajohnsen.server;

import com.kajohnsen.shared.CommandData;
import com.kajohnsen.shared.CommandType;
import com.kajohnsen.shared.Results;

import java.lang.reflect.Method;

public class Command {
    private CommandData commandData;
    private String methodId;

    Command(CommandData commandData) {
        this.commandData = commandData;
        if (commandData.getType() == CommandType.TO_LOWER_CASE) methodId = "toLowerCase";
        else if (commandData.getType() == CommandType.TRIM) methodId = "trim";
        else if (commandData.getType() == CommandType.PARSE_DOUBLE) methodId = "parseDouble";
    }

    public Results execute() {
        boolean success;
        Object data = null;
        String errorInfo = null;
        try {
            Class receiver = Class.forName("com.kajohnsen.server.StringProcessor");
            Method method = receiver.getMethod(methodId, String.class);
            data = method.invoke(StringProcessor.getInstance(), commandData.getData());
            success = true;
        } catch (Exception e) {
            success = false;
            errorInfo = e.getMessage();
        }
        return new Results(success, data, errorInfo);
    }
}
