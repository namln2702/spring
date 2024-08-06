package com.example.demo.mapper.mapperImp;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.mapper.PermissionRequestToPermission;
import com.example.demo.model.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionRequestToPermissionImp implements PermissionRequestToPermission {
    @Override
    public Permission toPermission(PermissionRequest permissionRequest) {
        return Permission.builder()
                .name(permissionRequest.getName())
                .description(permissionRequest.getDescription())
                .build();
    }
}
