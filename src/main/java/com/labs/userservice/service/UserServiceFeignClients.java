package com.labs.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "company-service",
        path = "/companies",
        url = "http://localhost:8081"
)
public interface UserServiceFeignClients {

    @GetMapping("/exists-by-id/{companyId}")
    Boolean existsById (@PathVariable String companyId);
}
