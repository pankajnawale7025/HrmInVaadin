package org.vaadin.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Setter
@Getter
@ToString
public class Customer {
    private int id=32;
    @NotBlank
    private String name;
    @NotBlank
    private String surName;
    @NotBlank
    private String contactNumber;
    @NotBlank
    private String emailAddress;
    @NotBlank
    private String address;
    @NotBlank
    private String password;

    public Customer() {

    }

}
