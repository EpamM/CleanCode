package com.epam.engx.cleancode.naming.task5.thirdpartyjar;

public class InvalidFileTypeException extends RuntimeException {
    final String message;
    public InvalidFileTypeException(String str) {
        super();
        this.message = str;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
