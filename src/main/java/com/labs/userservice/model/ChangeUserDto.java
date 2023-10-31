package com.labs.userservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeUserDto {
    private String name;
    private String email;
    private String companyId;
}
