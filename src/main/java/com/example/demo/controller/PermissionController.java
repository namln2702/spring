package com.example.demo.controller;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.model.Permission;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.PermissionService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/permission")

public class PermissionController {

    @Autowired
    PermissionService permissionService;



    @PostMapping
    ResponseEntity<?> createPermission(@RequestBody PermissionRequest permissionRequest){
        return permissionService.create(permissionRequest);
    }

    @GetMapping
    ResponseEntity<?> getPermissions(){
        return permissionService.getPermission();
    }

    @PutMapping
    ResponseEntity<?> updatePermission(@RequestBody PermissionRequest permissionRequest){
        return permissionService.updatePermission(permissionRequest);
    }

    @DeleteMapping
    ResponseEntity<?> deletePermission(@RequestParam String usernName){
        return permissionService.deletePermission(usernName);
    }

}
