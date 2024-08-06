package com.example.demo.controller;

import com.example.demo.dto.request.RoleRequest;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getRoles(){
        return roleService.getRole();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoleRequest roleRequest){
        return roleService.create(roleRequest);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody RoleRequest roleRequest){
        return roleService.updateRole(roleRequest);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRole(@RequestParam String userName){
        return roleService.deleteRole(userName);
    }
}
