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

    private Object processString(String s, String methodId) throws ServerException {
        Results results = ClientCommunicator.getInstance().send(s, methodId);
        if (results.isSuccess()) {
            return results.getData();
        } else {
            throw new ServerException(results.getErrorInfo());
        }

    }

    @Override
    public String toLowerCase(String s) {
        String result = null;
        try {
            result = (String) processString(s, "toLowerCase");
        } catch (ServerException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String trim(String s) {
        String result = null;
        try {
            result = (String) processString(s, "trim");
        } catch (ServerException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Double parseDouble(String s){
        Double result = null;
        try {
            result =  (Double) processString(s, "parseDouble");
        } catch (ServerException e) {
            e.printStackTrace();
        }
        return result;
    }
}
