package com.example.QuickPick.exception;

public class NoCabsAvailableException extends RuntimeException{
    public NoCabsAvailableException(String message){
        super(message);
    }
}
