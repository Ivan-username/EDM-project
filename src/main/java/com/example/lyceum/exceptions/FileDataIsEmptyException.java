package com.example.lyceum.exceptions;

public class FileDataIsEmptyException extends RuntimeException{
    public FileDataIsEmptyException(String message, Throwable cause){
        super(message, cause);
    }
}
