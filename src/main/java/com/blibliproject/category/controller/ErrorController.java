package com.blibliproject.category.controller;

import com.blibliproject.category.exception.ApiKeyNotFoundException;
import com.blibliproject.category.exception.ApiKeyNotIdentifiedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ApiKeyNotFoundException.class)
    public String ApiKey(ApiKeyNotFoundException exception){
        return "API KEY NOT FOUND";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ApiKeyNotIdentifiedException.class)
    public String NotIdentified(ApiKeyNotIdentifiedException exception){
        return "API KEY NOT IDENTIFIED";
    }

}
