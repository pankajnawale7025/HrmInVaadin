package org.vaadin.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;


@Setter
@Getter
@ToString
public class Customer {
    private int id=32;
    @NotBlank

    private String name;
    private String surName;
    private String contactNumber;
    private String emailAddress;
    private String address;
    private String password;

    public Customer() {

    }

}
