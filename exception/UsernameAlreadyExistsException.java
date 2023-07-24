package com.bms.schoolmanagementsystem.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String name){
        super(name);
    }
}
