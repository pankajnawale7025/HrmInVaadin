package org.vaadin.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vaadin.example.config.RestTemplateConfig;
import org.vaadin.example.model.Response;

@Service
public class UserService {

    public final RestTemplate restTemplate;


    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Object validateUSer(String url) {
//        String urlWithParams = url + "?emailAddress=" + emailAddress + "&contactNumber=" + contactNumber;
        System.out.println("urlWith  Params===>" + url);
        System.out.println("hardcodedParams===>" + "http://localhost:8081/customer/validateCustomer?emailAddress=pankajnawale7025@gmail.com&contactNumber=8806444288");

        Response response = restTemplate.getForObject(url, Response.class);
        return response;
    }


}
