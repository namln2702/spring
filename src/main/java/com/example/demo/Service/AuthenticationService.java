package com.example.demo.Service;

import com.example.demo.DTO.Request.AuthenticationRequest;
import com.example.demo.DTO.Response.AuthenticationResponse;

public interface AuthenticationService {
    public boolean authentication(AuthenticationRequest authenticationRequest);
}
