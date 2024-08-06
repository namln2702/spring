package com.example.demo.service;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.model.Permission;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PermissionService {
    public ResponseEntity<?> create(PermissionRequest request);
    public ResponseEntity<?> getPermission();

    public ResponseEntity<?> updatePermission(PermissionRequest permissionRequest);

    public ResponseEntity<?> deletePermission(String name);



}
