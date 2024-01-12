package org.vaadin.example.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name = "user-service", url = "http://localhost:/localhost:8081/customer") // Replace with your actual service name and URL
public interface UserServiceFeignClient {
    @GetMapping("/ViewAllCustomer")
    List<Object> getUser();


}
