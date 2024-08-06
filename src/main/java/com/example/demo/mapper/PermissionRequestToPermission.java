package com.example.demo.mapper;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.model.Permission;
import org.springframework.stereotype.Component;

@Component
public interface PermissionRequestToPermission {
    public Permission toPermission(PermissionRequest permissionRequest);
}
