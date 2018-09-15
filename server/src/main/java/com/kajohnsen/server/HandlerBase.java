package com.kajohnsen.server;

import com.kajohnsen.shared.Results;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public abstract class HandlerBase implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        boolean success;
        Object data = null;
        String errorInfo = null;
        try {
            InputStream is = httpExchange.getRequestBody();
            String requestString = readString(is);
            data = processString(requestString);
            success = true;
            System.out.println("Processed string " + requestString);
        } catch (NumberFormatException e) {
            success = false;
            errorInfo = e.getMessage();
        }
        Results results = new Results(success, data, errorInfo);
        String serResults = Serializer.serializeResults(results);
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        OutputStream os = httpExchange.getResponseBody();
        OutputStreamWriter outputWriter = new OutputStreamWriter(os);
        outputWriter.write(serResults);
        os.close();
    }

    protected abstract Object processString(String requestString);

    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

}
