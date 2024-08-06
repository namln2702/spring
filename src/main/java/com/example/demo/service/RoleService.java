package com.example.demo.service;

import com.example.demo.dto.request.RoleRequest;
import com.example.demo.model.Role;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    public ResponseEntity<?> create(RoleRequest roleRequest);
    public ResponseEntity<?> getRole();

    public ResponseEntity<?> deleteRole(String roleName);
    public ResponseEntity<?> updateRole(RoleRequest roleRequest);
}
