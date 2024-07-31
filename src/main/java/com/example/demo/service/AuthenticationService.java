package com.example.demo.service;

import com.example.demo.dto.request.AuthenticationRequest;

public interface AuthenticationService {
    public boolean authentication(AuthenticationRequest authenticationRequest);
}
