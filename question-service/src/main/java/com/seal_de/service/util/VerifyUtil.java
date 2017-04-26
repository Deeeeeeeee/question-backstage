package com.seal_de.service.util;

import com.seal_de.service.exception.ControllerException;

public class VerifyUtil {
    private static void verify(boolean statement, Object errorStatus, String errorMessage){
        if(statement == false)
            throw new ControllerException(errorStatus, errorMessage);
    }

    public static <T> void notNull(T element, Object errorStatus, String errorMessage){
        if(element == null) {
            verify(false, errorStatus, errorMessage);
        }
    }

    public static <T> void isNull(T element, Object errorStatus, String errorMessage) {
        if(element != null)
            verify(false, errorStatus, errorMessage);
    }

    public static void stringEquals(String pre, String fol, Object errorStatus, String errorMessage){
        if(!pre.equals(fol))
            verify(false, errorStatus, errorMessage);
    }

    public static void isTrue(boolean statement, Object errorStatus, String errorMessage){
        if(statement == false) {
            verify(false, errorStatus, errorMessage);
        }
    }
}
