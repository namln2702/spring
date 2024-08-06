package com.example.demo.mapper.mapperImp;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.mapper.PermissionToPermissionResponseMapper;
import org.springframework.stereotype.Component;


@Component
public class PermissionToPermissionResponseMapperImp implements PermissionToPermissionResponseMapper {

    @Override
    public PermissionResponse toPermissionResponse(PermissionRequest permissionRequest) {
        return PermissionResponse.builder()
                .name(permissionRequest.getName())
                .description(permissionRequest.getDescription())
                .build();
    }
}
