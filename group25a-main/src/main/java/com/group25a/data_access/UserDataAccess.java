package com.group25a.data_access;

import java.sql.*;

import com.group25a.database.IDBManager;
import com.group25a.models.Gender;
import com.group25a.models.User;
import com.group25a.models.UserRegistrationContract;

public class UserDataAccess implements IUserDataAccess {

    private final IDBManager manager;

    public UserDataAccess(IDBManager manager) {
        this.manager = manager;
    }

    @Override
    public User getUser(String username) {
        try {
            Connection connection = manager.getConnection();
            User user = null;
            var query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet result = connection.createStatement().executeQuery(query);
            if (result.next()) {
                Gender gender = Gender.valueOf(result.getString("Gender"));

                user = new User(
                        result.getInt("UserID"),
                        result.getString("FirstName"),
                        result.getString("LastName"),
                        gender,
                        result.getDate("DateOfBirth"),
                        result.getString("PhoneNumber"),
                        result.getString("Email"),
                        result.getString("Address"),
                        result.getString("Username"),
                        result.getString("Password"),
                        result.getInt("DoctorID"));
            }
            return user;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUser(UserRegistrationContract user) {
        try {
            Connection connection = manager.getConnection(); // establishing connection with DBmanager
            String query = "INSERT INTO users (firstname, lastname, dateOfBirth, gender, phonenumber,email, address, username, password, doctorID) VALUES ('"
                    + user.getFirstname() + "', '" + user.getLastname() + "', '" + user.getDob() + "', '"
                    + user.getGender() + "', '"
                    + user.getPhoneNumber() + "', '" + user.getEmail() + "', '" + user.getAddress() + "', '"
                    + user.getUsername() + "', '"
                    + user.getPassword() + "', '" + user.getDoctorID() + "')";
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isEmailUnique(String email) {
        try {
            Connection connection = manager.getConnection();
            var query = "SELECT * FROM users WHERE Email = '" + email + "'";
            ResultSet result = connection.createStatement().executeQuery(query);
            if (result.next()) {
                return false;
            } else {
                // Email is unique
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isUsernameUnique(String username) {
        try {
            Connection connection = manager.getConnection();
            var query = "SELECT * FROM users WHERE Username = '" + username + "'";
            ResultSet result = connection.createStatement().executeQuery(query);
            if (result.next()) {
                return false;
            } else {
                // Username is unique
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

    public void updateUserDoctor(int userID, int doctorID) {
        try {
            Connection connection = manager.getConnection();
            String query = "UPDATE Users SET Users.DoctorID = ? WHERE Users.UserID = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, doctorID);
                statement.setInt(2, userID);
                int rowsAffected = statement.executeUpdate();
                // error handling
                if (rowsAffected == 0) {
                    System.out.println("No rows updated for UserID: " + userID);
                } else {
                    System.out.println(rowsAffected + " row(s) updated successfully for UserID: " + userID);
                }
            }
        } catch (SQLException ex) {
            // Handle SQL exceptions
            ex.printStackTrace();
        }
    }

    public User getUserByID(int id) {
        try {
            Connection connection = manager.getConnection();
            User user = null;
            var query = "SELECT * FROM users WHERE username = '" + id + "'";
            ResultSet result = connection.createStatement().executeQuery(query);
            if (result.next()) {
                Gender gender = Gender.valueOf(result.getString("Gender"));

                user = new User(
                        result.getInt("UserID"),
                        result.getString("FirstName"),
                        result.getString("LastName"),
                        gender,
                        result.getDate("DateOfBirth"),
                        result.getString("PhoneNumber"),
                        result.getString("Email"),
                        result.getString("Address"),
                        result.getString("Username"),
                        result.getString("Password"),
                        result.getInt("DoctorID"));
            }
            return user;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
    }
}
