package com.example.demo.service.implement;

import com.example.demo.dto.request.RoleRequest;
import com.example.demo.model.Role;
import com.example.demo.payload.ResponseData;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public ResponseEntity<?> create(RoleRequest roleRequest) throws RuntimeException{
        Role role = Role.builder()
                .name(roleRequest.getName())
                .description(roleRequest.getDescription())
                .permissions(new HashSet<>(permissionRepository.findAllById(roleRequest.getPermissions())))
                .build();

        roleRepository.save(role) ;

        return ResponseEntity.ok().body(
                ResponseData.builder()
                        .status(HttpStatus.OK.value())
                        .message("Complete")
                        .data(role)
                        .build()
        );

    }

    @Override
    public ResponseEntity<?> getRole() {

        List<Role> saveRole = new ArrayList<>();

        for (Role role : roleRepository.findAll()){
            if (!role.isDelete()){
                saveRole.add(role);
            }
        }

        return ResponseEntity.ok().body(ResponseData.builder()
                        .status(HttpStatus.OK.value())
                        .message("Complete")
                        .data(saveRole)
                .build());
    }

    @Override
    public ResponseEntity<?> deleteRole(String roleName) throws RuntimeException{
        Optional<Role> role = roleRepository.findById(roleName);

        if(role.isPresent()){
            role.get().setDelete(true);
        }

        return ResponseEntity.ok().body(ResponseData.builder()
                        .status(HttpStatus.OK.value())
                        .message("Delete complete")
                        .data(role)
                .build()

        );

    }

    @Override
    public ResponseEntity<?> updateRole(RoleRequest roleRequest) throws RuntimeException{
        Optional<Role> save = roleRepository.findById(roleRequest.getName());
        String message = "Updata error";
        Role role = new Role();
        if( save.isPresent() ) {
            role = Role.builder()
                    .name(roleRequest.getName())
                    .description(roleRequest.getDescription())
                    .permissions(new HashSet<>(permissionRepository.findAllById(roleRequest.getPermissions())))
                    .build();
            message = "Update successfully";
            roleRepository.save(role);
        }

        return ResponseEntity.ok().body(ResponseData.builder()
                        .status(HttpStatus.OK.value())
                        .message(message)
                .build()
        );
    }
}
