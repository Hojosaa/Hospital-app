package com.group25a.data_access;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.group25a.database.IDBManager;
import com.group25a.models.Booking;
import com.group25a.models.BookingCreation;

public class BookingDataAccess implements IBookingDataAccess {
    private final IDBManager manager;

    public BookingDataAccess(IDBManager manager) {
        this.manager = manager;
    }

    @Override
    public void addBooking(BookingCreation booking) {
        try {
            Connection connection = manager.getConnection(); // establishing connection with DBmanager
            String query = "INSERT INTO Booking (UserID, DoctorID, Date, BookingTime, BookingState) VALUES ('"
                    + booking.getUserID() + "', '" + booking.getDoctorID() + "', '" + booking.getDate() + "', '"
                    + booking.getTime() + "', '" + "true" + "')";
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBooking(int bookingID, Date newDate, Time newTime) {
        String query = "UPDATE Booking SET Date = ?, BookingTime = ? WHERE BookingID = ?";
        try {
            Connection connection = manager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setDate(1, newDate);
            statement.setTime(2, newTime);
            statement.setInt(3, bookingID);

            System.out.println(statement.toString());

            // Execute the update
            int res = statement.executeUpdate();
            System.out.println("Updated " + res + " rows");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // method to obtain list of all past bookings from the DB
    public List<Booking> getAllPastBookings(int userID) {
        List<Booking> bookings = new ArrayList<>();
        try {
            Connection connection = manager.getConnection();
            String query = "SELECT * FROM Booking WHERE UserId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                System.out.println("BookingID: " + result.getInt("BookingID"));
                bookings.add(new Booking(
                        result.getInt("BookingID"),
                        result.getInt("UserID"),
                        result.getInt("DoctorID"),
                        result.getString("Date"),
                        result.getTime("BookingTime"),
                        result.getBoolean("BookingState")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> getAllCurrentBookings(int userId, int month, int year) {
        List<Booking> bookings = new ArrayList<>();

        try {
            Connection connection = manager.getConnection();
            String query = "SELECT * FROM Booking WHERE UserId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String date = result.getString("Date");
                String formattedMonth = String.format("%02d", month);
                if (date.startsWith(year + "-" + formattedMonth)) {
                    bookings.add(new Booking(
                            result.getInt("BookingID"),
                            result.getInt("UserID"),
                            result.getInt("DoctorID"),
                            date,
                            result.getTime("BookingTime"),
                            result.getBoolean("BookingState")));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookings;
    }

    public boolean isBookingExists(int doctorID, String date, String time) {
        boolean exists = false;
        try {
            Connection connection = manager.getConnection();
            String query = "SELECT * FROM Booking WHERE DoctorID = ? AND Date = ? AND BookingTime = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, doctorID);
            statement.setString(2, date);
            statement.setString(3, time);

            ResultSet result = statement.executeQuery();
            exists = result.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }

}
