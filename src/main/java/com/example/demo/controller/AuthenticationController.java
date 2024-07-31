package com.example.demo.controller;

import com.example.demo.DTO.request.AuthenticationRequest;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authentication")

public class AuthenticationController {

    @Autowired
    @Qualifier("authenticationServiceImp")
    AuthenticationService authenticationService;

    @GetMapping
    public Boolean check(@RequestBody AuthenticationRequest authenticationRequest){
       return authenticationService.authentication(authenticationRequest);
    }

}
