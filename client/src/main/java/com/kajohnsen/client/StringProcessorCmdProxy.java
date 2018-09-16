package com.kajohnsen.client;

import com.kajohnsen.shared.CommandData;
import com.kajohnsen.shared.CommandType;
import com.kajohnsen.shared.IStringProcessor;
import com.kajohnsen.shared.Results;

public class StringProcessorCmdProxy implements IStringProcessor {
    private static final StringProcessorCmdProxy ourInstance = new StringProcessorCmdProxy();

    public static StringProcessorCmdProxy getInstance() {
        return ourInstance;
    }

    private StringProcessorCmdProxy() {
    }

    private Object sendCommandData(CommandData commandData) {
        Results results = null;
        try{
            results = ClientCommunicator.getInstance().send(commandData);
            if (results.isSuccess()) {
                return results.getData();
            } else {
                throw new ServerException(results.getErrorInfo());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        }

        return results;

    }

    @Override
    public String toLowerCase(String s) {
        CommandData commandData = new CommandData(CommandType.TO_LOWER_CASE, s);
        return (String) sendCommandData(commandData);
    }

    @Override
    public String trim(String s) {
        CommandData commandData = new CommandData(CommandType.TRIM, s);
        return (String) sendCommandData(commandData);
    }

    @Override
    public Double parseDouble(String s) {
        CommandData commandData = new CommandData(CommandType.PARSE_DOUBLE, s);
        return (Double) sendCommandData(commandData);
    }
}
