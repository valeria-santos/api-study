package com.github.project.study.api.entities;

import com.github.project.study.api.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Size(max = 255)
    @NotBlank(message = "The field name is mandatory")
    private String name;

    @NotBlank(message = "The field code is mandatory")
    @Column(unique = true)
    @Size(max = 10)
    private String code;

    @NotBlank(message = "The field instructor is mandatory")
    @Size(max = 255)
    private String instructor;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "inactivation_date")
    private LocalDateTime inactivationDate;
}
