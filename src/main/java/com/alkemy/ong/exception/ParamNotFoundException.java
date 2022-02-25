package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParamNotFoundException extends RuntimeException {

    public ParamNotFoundException(String message) {
        super(message);
    }
}
