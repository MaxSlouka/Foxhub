package com.gfa.foxbook.foxbook.models.dtos.security;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
