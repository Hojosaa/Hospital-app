package com.group25a.services;

import com.group25a.exceptions.custom_exceptions.*;
import com.group25a.models.User;
import com.group25a.models.UserRegistrationContract;

public interface IUserService {
    User authenticate(String username, String password) throws UserNotFoundException, InvalidCredentialsException;
    void register(UserRegistrationContract user) throws ValidationException, InvalidEmailException, InvalidUsernameException;
    void changeUserDoctor(int userID, int doctorId) throws DoctorChangeException;
    User getUser(int id);

}
