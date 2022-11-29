package com.example.demo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Getter
@Builder
public class RequestObject {
    /**
     * After using build in RequestObject, you should apply method generateUrl,
     * otherwise those changes that you add before (queries, headers) would not be added
     */
    private URL url;
    private String endpoint;
    private RequestType requestMethod;

    @Singular private final Map<String, String> queries;
    @Singular private final Map<String, String> headers;

    public void generateUrl() throws IOException {
        StringBuilder tempUrl = new StringBuilder(endpoint);

        for (Map.Entry<String, String> entry : queries.entrySet()) {
            tempUrl.append(String.format("%s=%s&", entry.getKey(), entry.getValue()));
        }

        this.url = new URL(tempUrl.toString());
    }

    public String getRequestMethod() {
        return requestMethod.toString();
    }
}
