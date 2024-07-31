package com.example.demo.controller;

import com.example.demo.DTO.request.AuthenticationRequest;
import com.example.demo.DTO.request.IntrospectRequest;
import com.example.demo.DTO.response.AuthenticationResponse;
import com.example.demo.DTO.response.IntrospectResponse;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/authentication")

public class AuthenticationController {

    @Autowired
    @Qualifier("authenticationServiceImp")
    AuthenticationService authenticationService;

    @PostMapping (path = "/createToken")
    public AuthenticationResponse authenticationResponse(@RequestBody AuthenticationRequest authenticationRequest){
       return authenticationService.createToken(authenticationRequest);
    }

    @PostMapping(path  ="/checkToken")
    public IntrospectResponse introspectResponse(@RequestBody IntrospectRequest introspectRequest){
        return authenticationService.checkToken(introspectRequest);
    }

}
