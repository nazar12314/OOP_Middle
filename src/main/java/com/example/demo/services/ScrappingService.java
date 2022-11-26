package com.example.demo.services;

import com.example.demo.dto.RequestDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.repositories.ResponseDTORepository;
import com.example.demo.scrappers.AZUREDataScrapper;
import com.example.demo.scrappers.PDLDataScrapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class ScrappingService {

    private final PDLDataScrapper pdlScrapper = PDLDataScrapper.getInstance();
    private final AZUREDataScrapper azureDataScrapper = AZUREDataScrapper.getInstance();
    private final ResponseDTORepository repository;
    private String name;

    @Autowired
    public ScrappingService(ResponseDTORepository repository) {
        this.repository = repository;
    }

    public ResponseDTO scrapData(RequestDTO requestDTO) throws IOException {
        if (repository.findByDomain(requestDTO.getName()).isPresent()) {
            return repository.findByDomain(requestDTO.getName()).get();
        }

        JSONObject jsonObject = pdlScrapper.scrapData(requestDTO.getName());
        name = parseField(jsonObject, "name");

        return repository.save(
                new ResponseDTO(
                        name,
                        parseField(jsonObject, "twitter_url"),
                        parseField(jsonObject, "facebook_url"),
                        parseField(jsonObject, "logo"),
                        parseField(jsonObject, "icon"),
                        Integer.parseInt(Objects.requireNonNull(parseField(jsonObject, "employee_count"))),
                        parseField(jsonObject, "location"),
                        requestDTO.getName()
                ));
    }

    private String parseField(JSONObject jsonObject, String field) throws IOException {
        if (jsonObject.has(field) && !jsonObject.isNull(field)) {

            if (Objects.equals(field, "location")) {
                JSONObject location = jsonObject.getJSONObject(field);

                return formatString(parseField(location, "street_address"), false)
                        +
                        formatString(parseField(location, "name"), true);
            }

            return String.valueOf(jsonObject.get(field));
        }

        if (field.equals("facebook_url") || field.equals("twitter_url")) {
            JSONObject dataFromAzure = azureDataScrapper.scrapData(
                    name
                            +
                            " "
                            +
                            (field.equals("twitter_url") ? "twitter" : "facebook")
            );

            JSONObject webpages = dataFromAzure.getJSONObject("webPages");
            JSONArray values = webpages.getJSONArray("value");

            return values.getJSONObject(0).getString("url");
        }

        if (field.equals("logo") || field.equals("icon")) {
            JSONObject dataFromAzure = azureDataScrapper.scrapImages(
                    name
                            +
                            " "
                            +
                            field
            );

            JSONArray imgObjects = dataFromAzure.getJSONArray("value");
            return imgObjects.getJSONObject(0).getString("contentUrl");
        }

        return null;
    }

    private String formatString(String str, boolean closing) {
        if (str == null) {
            return "";
        }

        return closing ? str : str + ", ";
    }
}