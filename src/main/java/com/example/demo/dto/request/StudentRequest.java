package com.example.demo.dto.request;

import com.example.demo.validator.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StudentRequest {
    String name;
    String email;
    String username;
    String password;

    @DobConstraint(min = 18 )
    Integer age;
    List<String> roles;
}
