package com.github.project.study.api.entities;

import com.github.project.study.api.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @NotBlank(message = "The field name is mandatory")
    @Size(max = 255)
    private String name;
    @NotBlank(message = "The field username is mandatory")
    @Pattern(regexp = "^[a-z]+$", message = "The username must contain only lowercase characters")
    @Column(unique = true)
    @Size(max = 20)
    private String username;
    @NotBlank(message = "\n" + "The field email is mandatory")
    @Email(message = "The email must be in a valid format")
    @Column(unique = true)
    @Size(max = 255)
    private String email;
    @NotBlank(message = "The field password is mandatory")
    @Size(max = 255)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

}
