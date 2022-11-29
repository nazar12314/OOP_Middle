package com.example.demo.config;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    final private static String configFilePath = "src/main/java/com/example/demo/config/config.properties";

    public static String get(String query) {
        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties properties = new Properties();
            properties.load(propsInput);
            return properties.getProperty(query);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
