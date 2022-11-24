package com.example.demo.dto;

public record ResponseDTO(
        String name,
        String twitter,
        String facebook,
        String logo,
        String icon,
        Integer employees,
        String address
) {}