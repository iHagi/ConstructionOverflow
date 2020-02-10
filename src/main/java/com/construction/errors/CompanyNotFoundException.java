package com.construction.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such company")
public class CompanyNotFoundException extends RuntimeException  {
    public CompanyNotFoundException(String message){
        super(message);
    }
}
