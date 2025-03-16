package com.group25a.services;

import com.group25a.data_access.IUserDataAccess;
import com.group25a.exceptions.custom_exceptions.*;
import com.group25a.models.User;
import com.group25a.models.UserRegistrationContract;

public class UserService implements IUserService {
    private final IUserDataAccess authenticationDataAccess;

    public UserService(IUserDataAccess authenticationDataAccess) {
        this.authenticationDataAccess = authenticationDataAccess;
    }

    public User authenticate(String username, String password)
            throws UserNotFoundException, InvalidCredentialsException {
        var user = this.authenticationDataAccess.getUser(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException(username);
        }

        return user;
    }

    public void register(UserRegistrationContract user)
            throws ValidationException, InvalidEmailException, InvalidUsernameException {
        boolean isEmailUnique = this.authenticationDataAccess.isEmailUnique(user.getEmail());
        boolean isLoginUnique = this.authenticationDataAccess.isUsernameUnique(user.getUsername());

        if (!isEmailUnique){
            throw new InvalidEmailException(user.getEmail());
        }
        if (!isLoginUnique){
            throw new InvalidUsernameException(user.getUsername());
        }
        if (user.getPhoneNumber().length() != 11){
            throw new InvalidPhoneNumberException();
        }
        if(!(user.getEmail().endsWith(".com") || user.getEmail().endsWith(".co.uk"))){
            throw new InvalidEmailException(user.getEmail());
        }
        

        this.authenticationDataAccess.addUser(user);
    }
    @Override
    public void changeUserDoctor(int userID, int doctorId) {
        this.authenticationDataAccess.updateUserDoctor(userID, doctorId);
    }

    @Override
    public User getUser(int id) {
       return  this.authenticationDataAccess.getUserByID(id);

    }


}
