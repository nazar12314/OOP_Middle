package com.example.demo.scrappers;

import com.example.demo.config.Config;
import com.example.demo.request.RequestObject;
import com.example.demo.request.RequestSender;
import com.example.demo.request.RequestType;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AZUREDataScrapper implements Scrappable {

    private static AZUREDataScrapper scrapper;
    private final String endpoint = Config.get("AZURE_SEARCH_LINK");
    private final RequestSender requestSender = RequestSender.getInstance();

    private AZUREDataScrapper() {}

    public JSONObject scrapData(String query) throws IOException {
        RequestObject request = formRequest(endpoint + "search?", query);
        return requestSender.sendRequest(request);
    }

    public JSONObject scrapImages(String query) throws IOException {
        RequestObject request = formRequest(endpoint + "images/search?", query);
        return requestSender.sendRequest(request);
    }

    private RequestObject formRequest(String endpoint, String query) throws IOException {
        String token = Config.get("AZURE_SEARCH_TOKEN");

        RequestObject requestObject = RequestObject
                .builder()
                .endpoint(endpoint)
                .requestMethod(RequestType.GET)
                .header("Ocp-Apim-Subscription-Key", token)
                .query("q", URLEncoder.encode(query, StandardCharsets.UTF_8))
                .build();

        requestObject.generateUrl();

        return requestObject;
    }

    public static AZUREDataScrapper getInstance() {
        if (scrapper == null) {
            scrapper = new AZUREDataScrapper();
        }

        return scrapper;
    }
}