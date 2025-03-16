package com.group25a.exceptions.custom_exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username) {
        super("User not found with username" + username);
    }
}
