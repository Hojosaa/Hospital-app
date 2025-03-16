package com.group25a.exceptions.custom_exceptions;

public class InvalidEmailException extends Exception{

    public InvalidEmailException(String email){super(email + "is not available");}}
