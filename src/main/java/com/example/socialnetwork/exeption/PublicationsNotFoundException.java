package com.example.socialnetwork.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PublicationsNotFoundException extends RuntimeException {
    public PublicationsNotFoundException(String s) {
    }
}
