package com.example.demo.mapper;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermissionResponse;

public interface PermissionToPermissionResponseMapper {
    public PermissionResponse toPermissionResponse(PermissionRequest permissionRequest);
}
