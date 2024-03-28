package com.github.project.study.api.dtos;

import lombok.Data;

import java.math.BigInteger;

@Data
public class EnrollmentDto {
    private BigInteger userId;
    private BigInteger courseId;
}
