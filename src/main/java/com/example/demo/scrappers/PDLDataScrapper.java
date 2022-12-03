package com.example.demo.scrappers;

import com.example.demo.config.Config;
import com.example.demo.request.RequestObject;
import com.example.demo.request.RequestSender;
import com.example.demo.request.RequestType;
import org.json.JSONObject;
import java.io.IOException;

public class PDLDataScrapper implements Scrappable {

    private static PDLDataScrapper scrapper;
    private final String endpoint = Config.get("PDL_LINK");
    private final RequestSender requestSender = RequestSender.getInstance();

    private PDLDataScrapper() {}

    public JSONObject scrapData(String query) throws IOException {
        RequestObject requestObject = RequestObject
                .builder()
                .endpoint(endpoint)
                .requestMethod(RequestType.GET)
                .header("x-api-key", Config.get("PDL_TOKEN"))
                .query("website", query)
                .build();
        requestObject.generateUrl();

        return requestSender.sendRequest(requestObject);
    }

    public static PDLDataScrapper getInstance() {
        if (scrapper == null) {
            scrapper = new PDLDataScrapper();
        }

        return scrapper;
    }
}