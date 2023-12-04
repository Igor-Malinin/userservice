package com.labs.userservice.service;

import com.labs.userservice.model.CompanyInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "company-service",
        path = "/company",
        url = "http://localhost:8040"
)
public interface UserServiceFeignClients {

    @GetMapping("/exists-by-id/{companyId}")
    Boolean existsById (@PathVariable String companyId);

    @GetMapping("/get-by-id/{companyId}")
    CompanyInfoDto getById (@PathVariable String companyId);

    @GetMapping("/get-all")
    List<CompanyInfoDto> getAllCompanies ();
}
