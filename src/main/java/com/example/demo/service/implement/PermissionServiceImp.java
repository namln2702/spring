package com.example.demo.service.implement;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.mapper.PermissionRequestToPermission;
import com.example.demo.mapper.mapperImp.PermissionToPermissionResponseMapperImp;
import com.example.demo.model.Permission;
import com.example.demo.payload.ResponseData;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PermissionServiceImp implements PermissionService {

    @Autowired
    public PermissionRepository permissionRepository;

    @Autowired
    public PermissionToPermissionResponseMapperImp permissionResponseMapperImp;

    @Autowired
    public PermissionRequestToPermission permissionRequestToPermission;
    @Override
    public ResponseEntity<?> create(PermissionRequest request) throws RuntimeException{
        Permission permission = permissionRequestToPermission.toPermission(request);

        if (permissionRepository.findById(request.getName()).isPresent()){
            return  ResponseEntity.ok().body(ResponseData.builder()
                            .status(HttpStatus.OK.value())
                            .message("User already exits")
                    .build());
        }

        permissionRepository.save(permission);

        return ResponseEntity.ok().body(ResponseData.builder()
                        .status(HttpStatus.OK.value())
                        .message("Create complete")
                        .data(PermissionResponse.builder()
                                .name(permission.getName())
                                .description(permission.getDescription())
                                .build())
                        .build()

        );

    }

    @Override
    public ResponseEntity<?> getPermission() throws RuntimeException {
        List<Permission> save = new ArrayList<>();

        for(Permission pos : permissionRepository.findAll()){
            if (!pos.isDelete()){
                save.add(pos);
            }
        }

        return ResponseEntity.ok().body(ResponseData.builder()
                        .status(HttpStatus.OK.value())
                        .message("Get permissions complete")
                        .data(save)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> updatePermission(PermissionRequest permissionRequest) {
        Optional<Permission> permission = permissionRepository.findById(permissionRequest.getName());
        Permission permission1 = Permission.builder()
                .name(permissionRequest.getName())
                .description(permissionRequest.getDescription())
                .build();


        if(!permission.isPresent()){
            permissionRepository.save(permission1);
            return ResponseEntity.ok().body(ResponseData.builder()
                            .status(HttpStatus.OK.value())
                            .message("Update complete")
                            .data(permission1)
                    .build());
        }

        return ResponseEntity.ok().body(ResponseData.builder()
                .status(HttpStatus.OK.value())
                .message("User already exists")
                .data(null)
                .build());
    }

    @Override
    public ResponseEntity<?> deletePermission(String name) {
        Optional<Permission> permission = permissionRepository.findById(name);

        permission.get().setDelete(true);
        permissionRepository.save(permission.get());

        return ResponseEntity.ok().body(ResponseData.builder()
                        .status(HttpStatus.OK.value())
                        .message("Delete complete")
                        .data(permission.get())

                .build());
    }
}
