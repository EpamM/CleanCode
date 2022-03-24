package com.epam.engx.cleancode.naming.task5.thirdpartyjar;

public class InvalidDirectoryException extends RuntimeException{
    final String message;
    public InvalidDirectoryException(String str) {
        super();
        this.message = str;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
