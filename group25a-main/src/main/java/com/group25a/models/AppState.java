package com.group25a.models;

// This class represents the current state of the application
public class AppState {
    // Static variable to store the logged-in user
    private static User loggedInUser;

    // Method to retrieve the logged-in user
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    // Method to set the logged-in user
    public static void setLoggedInUser(User loggedInUser) {
        AppState.loggedInUser = loggedInUser;
    }
}
