package com.group25a.exceptions.custom_exceptions;

public class ValidationException extends Exception{

    public ValidationException(){ super("Validation failed, please try again.");}
}