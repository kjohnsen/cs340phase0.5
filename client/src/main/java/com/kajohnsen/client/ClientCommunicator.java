package com.kajohnsen.client;

import com.kajohnsen.shared.CommandData;
import com.kajohnsen.shared.CommandType;
import com.kajohnsen.shared.Results;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.kajohnsen.shared.StreamStringer.readString;
import static com.kajohnsen.shared.StreamStringer.writeString;

class ClientCommunicator {
    private static final ClientCommunicator ourInstance = new ClientCommunicator();

    static ClientCommunicator getInstance() {
        return ourInstance;
    }

    private ClientCommunicator() {
    }

    private String HOST = "127.0.0.1";
    private String PORT = "8000";

    Results send(String data, String methodId) {
        Results results = null;
        try {
            URL url = new URL("http://" + HOST + ":" + PORT + "/" + methodId);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setDoOutput(true);
            http.connect();
            OutputStream reqBody = http.getOutputStream();
            writeString(data, reqBody);
            reqBody.close();

            InputStream respBody = http.getInputStream();
            results = Serializer.deserializeResults(respBody);
            System.out.println(String.format("Received result from server"));
        }
        catch (IOException e) {
            // An exception was thrown, so display the exception's stack trace
            e.printStackTrace();
        }

        return results;
    }

    Results send(CommandData commandData) {
        Results results = null;
        try {
            String methodId = "";URL url = new URL("http://" + HOST + ":" + PORT + "/execCommand");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setDoOutput(true);
            http.connect();
            OutputStream reqBody = http.getOutputStream();
            Serializer.serializeCommandData(commandData, reqBody);
            reqBody.close();

            InputStream respBody = http.getInputStream();
            results = Serializer.deserializeResults(respBody);
            System.out.println(String.format("Received result from server"));
        }
        catch (IOException e) {
            // An exception was thrown, so display the exception's stack trace
            e.printStackTrace();
        }

        return results;

    }
}
