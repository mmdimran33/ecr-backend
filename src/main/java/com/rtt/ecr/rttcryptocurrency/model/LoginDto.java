package com.rtt.ecr.rttcryptocurrency.model;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
