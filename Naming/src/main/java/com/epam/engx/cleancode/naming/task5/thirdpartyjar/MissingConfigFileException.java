package com.epam.engx.cleancode.naming.task5.thirdpartyjar;

public class MissingConfigFileException extends RuntimeException {
	final String message;
    public MissingConfigFileException(String str) {
        super(str);
        this.message = str;
    }

    public MissingConfigFileException(String str, Exception e) {
        super(str,e);
        this.message = str;
    }
    @Override
    public String getMessage() {
        return message;
    }
    
    
}
