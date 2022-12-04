package com.example.demo.controllers;

import com.example.demo.dto.ErrorDTO;
import com.example.demo.dto.RequestDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.services.ScrappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class ScrappingController {

    private final ScrappingService scrappingService;

    @Autowired
    public ScrappingController(ScrappingService scrappingService) {
        this.scrappingService = scrappingService;
    }

    @GetMapping
    public String greeting(Model model) {
        model.addAttribute("requestDTO", new RequestDTO());
        return "index";
    }

    @PostMapping
    public String scrapData(@ModelAttribute RequestDTO requestDTO, Model model) throws IOException {
        try {
            ResponseDTO responseDTO = scrappingService.scrapData(requestDTO);
            model.addAttribute("responseDTO", responseDTO);
        } catch (FileNotFoundException ex) {
            model.addAttribute(new ErrorDTO("There is no such Company with this domain or it can't be accessed"));
        }
        return "index";
    }
}
