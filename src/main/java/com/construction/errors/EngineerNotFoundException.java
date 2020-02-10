package com.construction.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such engineer")
public class EngineerNotFoundException extends RuntimeException {

    public EngineerNotFoundException(String message){
        super(message);
    }
}
