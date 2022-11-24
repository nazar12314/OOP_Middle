package com.example.demo.controllers;

import com.example.demo.dto.RequestDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.services.ScrappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/scrap-data")
public class ScrappingController {

    private final ScrappingService scrappingService;

    @Autowired
    public ScrappingController(ScrappingService scrappingService) {
        this.scrappingService = scrappingService;
    }

    @PostMapping
    public ResponseDTO scrapData(@RequestBody RequestDTO requestDTO) throws IOException {
        return scrappingService.scrapData(requestDTO);
    }
}
