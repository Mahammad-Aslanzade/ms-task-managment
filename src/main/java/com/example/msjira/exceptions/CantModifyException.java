package com.example.msjira.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CantModifyException extends RuntimeException{
    private String message;
    private String logMessage;
    public CantModifyException(String message, String logMessage) {
        super(message);
        this.message = message;
        this.logMessage = logMessage;
    }
}
