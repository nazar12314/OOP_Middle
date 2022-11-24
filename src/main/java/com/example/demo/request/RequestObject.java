package com.example.demo.request;

import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Getter
public class RequestObject {
    /**
     * After adding queries to RequestObject, you should apply method save,
     * otherwise those changes would not be added
     */
    private URL url;
    private final String endpoint;
    private final RequestType requestMethod;
    private final Map<String, String> queries = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public RequestObject(String endpoint, RequestType requestMethod) throws IOException {
        this.endpoint = endpoint;
        this.requestMethod = requestMethod;
        setUrl();
    }

    private void setUrl() throws IOException {
        StringBuilder tempUrl = new StringBuilder(endpoint);

        for (Map.Entry<String, String> entry : queries.entrySet()) {
            tempUrl.append(String.format("%s=%s&", entry.getKey(), entry.getValue()));
        }

        this.url = new URL(tempUrl.toString());
    }

    public RequestObject addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public RequestObject addQuery(String key, String value) {
        queries.put(key, value);
        return this;
    }

    public RequestObject addQueries(Map<String, String> queries) {
        this.queries.putAll(queries);
        return this;
    }

    public String getRequestMethod() {
        return requestMethod.toString();
    }

    public void save() throws IOException {
        setUrl();
    }
}
