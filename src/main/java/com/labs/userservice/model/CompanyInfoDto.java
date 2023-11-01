package com.labs.userservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyInfoDto {
    private String id;
    private String companyName;
    private String directorId;
    private String description;
}
