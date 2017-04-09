package com.seal_de.controller;

import com.seal_de.exception.ControllerException;
import com.seal_de.exception.Error;
import com.seal_de.exception.NotFoundException;
import org.hibernate.DuplicateMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionRestController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Error notFound(NotFoundException e) {
        int code = e.getErrorStatus();
        String message = e.getMessage();
        return new Error(code, message);
    }

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

    @ExceptionHandler(DuplicateMappingException.class)
    public Error handleDuplicateMappingException(){
        return null;
    }
}
