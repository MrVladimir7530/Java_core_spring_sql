package com.example.springsql.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AvatarNotFoundExceptions extends RuntimeException{
    public AvatarNotFoundExceptions() {
    }

    public AvatarNotFoundExceptions(String message) {
        super(message);
    }

    public AvatarNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public AvatarNotFoundExceptions(Throwable cause) {
        super(cause);
    }

    public AvatarNotFoundExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
