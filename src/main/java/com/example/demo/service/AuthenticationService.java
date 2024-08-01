package com.example.demo.service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;

public interface AuthenticationService {
    public AuthenticationResponse createToken(AuthenticationRequest authenticationRequest);
    public IntrospectResponse checkToken(IntrospectRequest introspectRequest);
}
