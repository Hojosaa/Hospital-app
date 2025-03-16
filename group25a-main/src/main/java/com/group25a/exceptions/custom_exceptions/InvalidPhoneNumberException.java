package com.group25a.exceptions.custom_exceptions;

public class InvalidPhoneNumberException extends IllegalArgumentException{
    public InvalidPhoneNumberException(){
        super("Invalid phone number");
    }
}
