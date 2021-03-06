package com.seal_de.controller;

import com.seal_de.service.exception.ControllerException;
import com.seal_de.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionRestController {
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<Error> controllerException(ControllerException e) {
        String message = e.getMessage();
        Error error = new Error(4, message);
        Object errorStatus = e.getErrorStatus();
        if(errorStatus instanceof HttpStatus){
            return ResponseEntity.status((HttpStatus)errorStatus).body(error);
        }
        return ResponseEntity.status((Integer)errorStatus).body(error);
    }
}
