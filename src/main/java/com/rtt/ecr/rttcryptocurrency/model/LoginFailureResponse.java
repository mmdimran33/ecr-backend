package com.rtt.ecr.rttcryptocurrency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginFailureResponse {
    private String message;
    private String reason;
    private String details;
   // private int attemptNo;


}
