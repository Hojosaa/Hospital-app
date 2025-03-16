package com.group25a.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.group25a.database.IDBManager;
import com.group25a.models.Prescription;

public class PrescriptionDataAccess implements IPrescriptionDataAccess{

    private final IDBManager manager;

    public PrescriptionDataAccess(IDBManager manager) {
        this.manager = manager;
    }

    @Override
    public Prescription getPrescriptionByBookingID(int bookingID) {
        String query = "SELECT * FROM Prescription WHERE BookingID = ?";

        Prescription bookingPrescription = null;
        try {
            Connection connection = manager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookingID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                bookingPrescription = new Prescription(
                        result.getInt("BookingID"),
                        result.getString("VisitSummary"),
                        result.getString("Prescription"),
                        result.getDate("PrescriptionLength"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookingPrescription;
    }
}
