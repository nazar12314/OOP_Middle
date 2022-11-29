package com.example.demo.scrappers;

import org.json.JSONObject;

import java.io.IOException;

public interface Scrappable {
    JSONObject scrapData(String query) throws IOException;
}
