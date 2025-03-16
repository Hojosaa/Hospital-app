package com.group25a.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group25a.database.IDBManager;
import com.group25a.models.Doctors;

// This class represents the data access layer for doctors
public class DoctorDataAccess implements IDoctorDataAccess {
    private final IDBManager manager;

    // Constructor
    public DoctorDataAccess(IDBManager manager) {
        this.manager = manager;
    }

    // Retrieve all doctors from the database
    @Override
    public List<Doctors> getAllDoctor() {
        ArrayList<Doctors> doctors = null;
        try {
            var connection = manager.getConnection();
            // Query to retrieve all doctors
            var query = "SELECT * FROM Doctors";

            // Execute the query
            var result = connection.createStatement().executeQuery(query);

            // List to store retrieved doctors
            doctors = new ArrayList<Doctors>();

            // Iterate over the result set and populate the list
            while (result.next()) {
                doctors.add(new Doctors(
                        result.getInt("DoctorID"),
                        result.getString("DoctorName"),
                        result.getString("PhoneNumber"),
                        result.getString("Ethnicity"),
                        result.getString("Specialty")
                ));
            }
        } catch (SQLException ex) {
            // Print stack trace if an exception occurs
            ex.printStackTrace();
        }

        // Return the list of doctors
        return doctors;
    }
    public Doctors getDoctorByID(int id) {
        try {
            var connection = manager.getConnection();
            var query = "SELECT * FROM doctors WHERE DoctorID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Doctors(
                        result.getInt("DoctorID"),
                        result.getString("DoctorName"),
                        result.getString("PhoneNumber"),
                        result.getString("Ethnicity"),
                        result.getString("Specialty")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
