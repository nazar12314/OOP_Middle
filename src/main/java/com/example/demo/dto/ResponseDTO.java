package com.example.demo.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table
@Entity
public class ResponseDTO {

    private String domain;
    private String name;
    private String twitter;
    private String facebook;
    private String logo;
    private String icon;
    private Integer employees;
    private String address;
    @Id
    private final String id = UUID.randomUUID().toString();
}