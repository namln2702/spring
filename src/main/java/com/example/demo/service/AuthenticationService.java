package com.example.demo.service;

import com.example.demo.DTO.request.AuthenticationRequest;
import com.example.demo.DTO.request.IntrospectRequest;
import com.example.demo.DTO.response.AuthenticationResponse;
import com.example.demo.DTO.response.IntrospectResponse;

public interface AuthenticationService {
    public AuthenticationResponse createToken(AuthenticationRequest authenticationRequest);
    public IntrospectResponse checkToken(IntrospectRequest introspectRequest);
}
