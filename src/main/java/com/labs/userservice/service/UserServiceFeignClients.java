package com.labs.microver4.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "company-service",
        path = "/api/companies",
        url = "http://localhost:8081"
)
public interface CompanyServiceFeignClients {

    @GetMapping("/exists-by-id/{companyId}")
    Boolean existsById (@PathVariable Long companyId);
}
