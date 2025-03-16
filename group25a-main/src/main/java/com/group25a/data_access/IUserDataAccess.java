package com.group25a.data_access;

import com.group25a.models.User;
import com.group25a.models.UserRegistrationContract;

public interface IUserDataAccess {
    User getUser(String username);
    void addUser(UserRegistrationContract user);
    boolean isEmailUnique(String email);
    boolean isUsernameUnique(String username);
    void updateUserDoctor(int userID, int doctorID);

    User getUserByID(int id);
    void saveUser(User user);
}
