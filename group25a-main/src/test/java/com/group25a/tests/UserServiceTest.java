package com.group25a.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.group25a.data_access.IUserDataAccess;
import com.group25a.exceptions.custom_exceptions.*;
import com.group25a.models.Gender;
import com.group25a.models.User;
import com.group25a.models.UserRegistrationContract;
import com.group25a.services.UserService;

import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;
    private IUserDataAccess userDataAccess;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(IUserDataAccess.class);
        userService = new UserService(userDataAccess);
    }

    @Test
public void successful_login_1() throws UserNotFoundException, InvalidCredentialsException {
    User existingUser = mock(User.class);
    // Ensure getPassword() returns a non-null value
    when(existingUser.getPassword()).thenReturn("rizz");
    when(userDataAccess.getUser("daniel")).thenReturn(existingUser);
    
    userService.authenticate("daniel", "rizz");
    
    verify(userDataAccess).getUser("daniel");
}

@Test
public void successful_login_2() throws UserNotFoundException, InvalidCredentialsException {
    User existingUser = mock(User.class);
    // Ensure getPassword() returns a non-null value
    when(existingUser.getPassword()).thenReturn("password2");
    when(userDataAccess.getUser("testuser2")).thenReturn(existingUser);
    
    userService.authenticate("testuser2", "password2");
    
    verify(userDataAccess).getUser("testuser2");
}


    @Test
    public void LoginWrongUsername() {
        when(userDataAccess.getUser("daniela")).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.authenticate("daniela", "rizz"));
    }

    @Test
    public void LoginWrongPassword() {
        User user = mock(User.class);
        when(userDataAccess.getUser("daniel")).thenReturn(user);
        when(user.getPassword()).thenReturn("rizz");
        
        assertThrows(InvalidCredentialsException.class, () -> userService.authenticate("daniel", "wrongpass"));
    }

    @Test
    public void LoginEmptyInputs() {
        assertThrows(UserNotFoundException.class, () -> userService.authenticate("", ""));
    }

    @Test
    public void testSuccessfulRegistration() throws ValidationException, InvalidUsernameException, InvalidEmailException, InvalidPhoneNumberException {
        Date dob = new Date(System.currentTimeMillis());
        UserRegistrationContract inputUser = new UserRegistrationContract(
                "daniel", "ruano", Gender.Male, dob, "07426690080",
                "danieus@email.com", "parkwood", "danieriu", "djr41", 0);

        when(userDataAccess.isEmailUnique("danieus@email.com")).thenReturn(true);
        when(userDataAccess.isUsernameUnique("danieriu")).thenReturn(true);
        
        userService.register(inputUser);
        
        verify(userDataAccess).addUser(inputUser);
    }

    @Test
    public void invalidPhoneNumberRegistration() {
        Date dob = new Date(System.currentTimeMillis());
        UserRegistrationContract inputUser = new UserRegistrationContract(
                "testName", "testLastName", Gender.Other, dob, "123456789", // Assuming this is an invalid phone number
                "test@example.com", "testAddress", "testUsername", "testPassword", 0);

        when(userDataAccess.isEmailUnique("test@example.com")).thenReturn(true);
        when(userDataAccess.isUsernameUnique("testUsername")).thenReturn(true);
        
        assertThrows(InvalidPhoneNumberException.class, () -> userService.register(inputUser));
    }
}
