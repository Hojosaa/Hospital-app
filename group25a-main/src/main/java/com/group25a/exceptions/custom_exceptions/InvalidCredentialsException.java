package com.group25a.exceptions.custom_exceptions;

public class InvalidCredentialsException extends Exception{

    public InvalidCredentialsException(String username){
        super("Invalid credentials for user " + username);

    }
}
