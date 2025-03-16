package com.group25a.exceptions.custom_exceptions;

public class InvalidUsernameException extends Exception{

    public InvalidUsernameException(String username){super(username + "is already taken");}
}
