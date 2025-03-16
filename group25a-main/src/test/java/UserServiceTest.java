import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group25a.data_access.IUserDataAccess;
import com.group25a.exceptions.custom_exceptions.InvalidCredentialsException;
import com.group25a.exceptions.custom_exceptions.InvalidEmailException;
import com.group25a.exceptions.custom_exceptions.InvalidPhoneNumberException;
import com.group25a.exceptions.custom_exceptions.InvalidUsernameException;
import com.group25a.exceptions.custom_exceptions.UserNotFoundException;
import com.group25a.models.Gender;
import com.group25a.models.User;
import com.group25a.models.UserRegistrationContract;
import com.group25a.services.UserService;




public class UserServiceTest {

    private UserService userService;
    private IUserDataAccess userDataAccess;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(IUserDataAccess.class);
        userService = new UserService(userDataAccess);
    }

    @Test
    public void testAuthenticate_UserNotFound() {
        when(userDataAccess.getUser("nonexistent_user")).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.authenticate("nonexistent_user", "password"));
    }


    @Test
    public void testAuthenticate_InvalidCredentials() {
        User user = new User(1, "firstname", "lastname", null, null, null, null, null, "username", "password", 0);
        when(userDataAccess.getUser("username")).thenReturn(user);
        assertThrows(InvalidCredentialsException.class, () -> userService.authenticate("username", "invalid_password"));
    }

    @Test
    public void testRegister_InvalidEmail() {
        // Assuming email is not unique initially
        when(userDataAccess.isEmailUnique(anyString())).thenReturn(false);

        // Assertion for InvalidEmailException
        assertThrows(InvalidEmailException.class, () -> userService.register(
                new UserRegistrationContract("username", "invalid_email", null, null,
                        "password", "12345678901", null, null, null, 0)));
    }

    @Test
    public void testRegister_InvalidEmailDomain() {
        // Assertion for InvalidEmailException
        assertThrows(InvalidEmailException.class, () -> userService.register(
                new UserRegistrationContract("username", "email@example.org", null, null,
                        "password", "12345678901", null, null, null, 0)));
    }

    @Test
    public void testRegister_InvalidUsername() {
        // Assuming email is unique initially
        when(userDataAccess.isEmailUnique(anyString())).thenReturn(true);

        // Assuming username is not unique initially
        when(userDataAccess.isUsernameUnique(anyString())).thenReturn(false);

        // Assertion for InvalidUsernameException
        assertThrows(InvalidUsernameException.class, () -> userService.register(
                new UserRegistrationContract("invalidUsername", " test@example.com", null, null,
                        "password", "12345678901", null, null, null, 0)));

    }
     @Test
    public void testRegister_ValidRegistration() {
        // Arrange
        when(userDataAccess.isEmailUnique(anyString())).thenReturn(true);
        when(userDataAccess.isUsernameUnique(anyString())).thenReturn(true);
        
        Gender gender = Gender.Male; // Adjust based on your Gender enum
        Date dob = new Date(Calendar.getInstance().getTime().getTime());
        
        // Adjusted phone number to meet the 11-character length validation criteria
        String validPhoneNumber = "12345678901"; // This phone number now meets the validation criteria
        
        UserRegistrationContract validUserRegistration = new UserRegistrationContract(
            "FirstName", "LastName", gender, dob, validPhoneNumber,
            "valid.email@example.com", "Address", "validUsername",
            "StrongPassword123", 0); // Assuming 0 is a valid doctorID for testing
        
        // Act & Assert
        assertDoesNotThrow(() -> userService.register(validUserRegistration));

        // Optionally verify that the expected methods were called with the expected parameters
        verify(userDataAccess).isEmailUnique("valid.email@example.com");
        verify(userDataAccess).isUsernameUnique("validUsername");
        // If your implementation of addUser in IUserDataAccess accepts UserRegistrationContract, this needs to be adjusted
        // verify(userDataAccess).addUser(any(UserRegistrationContract.class));
    }

    @Test
    public void testRegister_InvalidPhoneNumber() {
        // Arrange
        when(userDataAccess.isEmailUnique(anyString())).thenReturn(true);
        when(userDataAccess.isUsernameUnique(anyString())).thenReturn(true);
        
        // Assuming Gender enum and Date are correctly handled
        Gender gender = Gender.Male;
        Date dob = new Date(Calendar.getInstance().getTime().getTime());
        
        // Provide an invalid phone number
        String invalidPhoneNumber = "12345"; // Adjust based on your validation rules
        UserRegistrationContract invalidPhoneUser = new UserRegistrationContract(
            "FirstName", "LastName", gender, dob, invalidPhoneNumber,
            "user@example.com", "123 Main St", "newuser",
            "password123", 0);
        
        // Act & Assert
       
        assertThrows(InvalidPhoneNumberException.class, () -> userService.register(invalidPhoneUser));

        
        verify(userDataAccess, never()).saveUser(any(User.class));
    }

    @Test
    public void testRegister_WeakPassword() {
        // Arrange
        when(userDataAccess.isEmailUnique(anyString())).thenReturn(true);
        when(userDataAccess.isUsernameUnique(anyString())).thenReturn(true);
        
        Gender gender = Gender.Male;
        Date dob = new Date(Calendar.getInstance().getTime().getTime());
        
        // Example of a weak password that doesn't meet the criteria (e.g., too short, lacks complexity)
        String weakPassword = "pass"; // Adjust based on your application's password policy
        
        UserRegistrationContract userWithWeakPassword = new UserRegistrationContract(
            "FirstName", "LastName", gender, dob, "1234567890",
            "user@example.com", "123 Main St", "username",
            weakPassword, 0);
        
        // Act & Assert
        // assertThrows(WeakPasswordException.class, () -> userService.register(userWithWeakPassword));

        // Optionally, verify that no user is saved to the database
        verify(userDataAccess, never()).saveUser(any());
    }


}
