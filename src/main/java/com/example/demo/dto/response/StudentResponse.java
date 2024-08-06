package com.example.demo.dto.response;

import com.example.demo.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StudentResponse {
    String email;
    String name;
    String username;
    Integer age;
    Set<Role> roles;
}
