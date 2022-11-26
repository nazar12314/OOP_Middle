package com.example.demo.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Table
@Entity
public class ResponseDTO {
    public ResponseDTO(
            String name,
            String twitter,
            String facebook,
            String logo,
            String icon,
            Integer employees,
            String address,
            String domain
    ) {
        this.name = name;
        this.twitter = twitter;
        this.facebook = facebook;
        this.logo = logo;
        this.icon = icon;
        this.employees = employees;
        this.address = address;
        this.domain = domain;
    }
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