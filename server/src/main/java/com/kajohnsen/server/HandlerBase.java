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

import static com.kajohnsen.shared.StreamStringer.readString;
import static com.kajohnsen.shared.StreamStringer.writeString;

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
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
//        String serResults = Serializer.serializeResults(results);
        OutputStream os = httpExchange.getResponseBody();
        Serializer.serializeResults(results, os);
//        writeString(serResults, os);
        os.close();
    }

    protected abstract Object processString(String requestString);

}
