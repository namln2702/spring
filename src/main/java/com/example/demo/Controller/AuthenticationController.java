package com.example.demo.Controller;

import com.example.demo.DTO.Request.AuthenticationRequest;
import com.example.demo.Service.Implement.AuthenticationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authentication")

public class AuthenticationController {

    @Autowired
    AuthenticationServiceImp authenticationService;

    @GetMapping
    public Boolean check(@RequestBody AuthenticationRequest authenticationRequest){
       return authenticationService.authentication(authenticationRequest);
    }

}
