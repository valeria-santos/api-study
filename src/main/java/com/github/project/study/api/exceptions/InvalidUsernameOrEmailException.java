package com.github.project.study.api.exceptions;

public class InvalidUsernameOrEmailException extends RuntimeException{
    public InvalidUsernameOrEmailException(String message) {
        super(message);
    }
}
