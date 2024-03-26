package com.github.project.study.api.dtos;

import com.github.project.study.api.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private Role role;
}
