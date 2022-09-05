package com.rtt.ecr.rttcryptocurrency.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CostomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
       Map<String, Object> data = new HashMap<String , Object>();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        data.put("message","Invalid user name and Password");
        data.put("statusCode","401");

    }
}
