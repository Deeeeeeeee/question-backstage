package com.seal_de.exception;


public class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private int errorStatus;
    private String message;

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(int errorStatus, String message) {
        this.errorStatus = errorStatus;
        this.message = message;
    }

    public int getErrorStatus(){
        return errorStatus;
    }

    public String getMessage() {
        return message;
    }
}
