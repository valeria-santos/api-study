package com.github.project.study.api.exceptions;

public class EnrollmentAlreadyExistsException extends RuntimeException{
    public EnrollmentAlreadyExistsException(String message) {
        super(message);
    }
}
