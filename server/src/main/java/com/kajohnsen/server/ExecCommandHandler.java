package com.kajohnsen.server;

import com.kajohnsen.shared.CommandData;
import com.kajohnsen.shared.Results;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import static com.kajohnsen.shared.StreamStringer.readString;

public class ExecCommandHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Results results = null;
        InputStream is = httpExchange.getRequestBody();
        CommandData cd = Serializer.deserializeCommandData(is);
        Command cmd = new Command(cd);
        results = cmd.execute();
        System.out.println("Processed command with string " + cd.getData());
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        OutputStream os = httpExchange.getResponseBody();
        Serializer.serializeResults(results, os);
        os.close();

    }
}
