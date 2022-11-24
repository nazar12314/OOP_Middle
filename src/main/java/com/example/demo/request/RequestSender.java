package com.example.demo.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

import org.json.JSONObject;

public class RequestSender {

    private static RequestSender requestSender;

    private RequestSender() {}

    public JSONObject sendRequest(RequestObject request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) request.getUrl().openConnection();
        connection.setRequestMethod(request.getRequestMethod());

        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        return new JSONObject(response.toString());
    }

    public static RequestSender getInstance() {
        if (requestSender == null) {
            requestSender = new RequestSender();
        }

        return requestSender;
    }
}