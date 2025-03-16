package test_helpers;

import java.util.ArrayList;

import com.group25a.data_access.IUserDataAccess;
import com.group25a.models.Gender;
import com.group25a.models.User;
import com.group25a.models.UserRegistrationContract;

public class FakeUserDataAccess implements IUserDataAccess {

    private final ArrayList<User> users = new ArrayList<>();

    @Override
    public User getUser(String username) {
        for (var user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void addUser(UserRegistrationContract user) {
        users.add(new User(
                users.size(),
                user.getFirstname(),
                user.getLastname(),
                user.getGender(),
                user.getDob(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getAddress(),
                user.getUsername(),
                user.getPassword(),
                user.getDoctorID()));
    }

    @Override
    public boolean isEmailUnique(String email) {
        for (var user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isUsernameUnique(String username) {
        for (var user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public User addTestUser(String username, String password) {
        var id = users.size();
        var dob = new java.sql.Date(System.currentTimeMillis());
        var user = new User(
                id,
                "FirstName_" + id,
                "LastName_" + id,
                Gender.Male,
                dob,
                "07777777777",
                "test" + id + "@mail.com",
                "Address_" + id,
                username,
                password,
                0);

        users.add(user);
        return user;
    }

    @Override
    public void updateUserDoctor(int userID, int doctorID) {
        for (var user : users) {
            if (user.getUserId() == userID) {
                user.setDoctorID(doctorID);
                return;
            }
        }
    }

    @Override
    public User getUserByID(int id) {
        for (var user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
    }
}
