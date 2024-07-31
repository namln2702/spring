package com.example.demo.service;

import com.example.demo.DTO.request.AuthenticationRequest;
import com.example.demo.DTO.response.AuthenticationResponse;

public interface AuthenticationService {
    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest);
}
