package com.group25a.services;

import com.group25a.data_access.IBookingDataAccess;
import com.group25a.models.Booking;
import com.group25a.models.BookingCreation;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookingService implements IBookingService {
    private final IBookingDataAccess authenticationDataAccess;

    public BookingService(IBookingDataAccess authenticationDataAccess) {
        this.authenticationDataAccess = authenticationDataAccess;
    }

    @Override
    public void addBooking(BookingCreation newBooking) {
        // Convert java.sql.Date to LocalDate
        LocalDate bookingDate = newBooking.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        // Assuming BookingCreation's time is a String in "HH:mm" format
        LocalTime bookingTime = LocalTime.parse(newBooking.getTime(), DateTimeFormatter.ofPattern("HH:mm"));

        // Prevent bookings in the past
        if (bookingDate.isBefore(LocalDate.now()) || 
           (bookingDate.isEqual(LocalDate.now()) && bookingTime.isBefore(LocalTime.now()))) {
            throw new IllegalArgumentException("Cannot book in the past.");
        } else {
            this.authenticationDataAccess.addBooking(newBooking);
        }
    }

    @Override
    public void updateBooking(int bookingID, Date newDate, Time newTime) {
        // Your existing update logic
        authenticationDataAccess.updateBooking(bookingID, newDate, newTime);
    }

    @Override
    public List<Booking> getAllPastBookings(int userID) {
        // Your existing logic to get all past bookings
        return this.authenticationDataAccess.getAllPastBookings(userID);
    }

    @Override
    public List<Booking> getAllCurrentBookings(int userId, int month, int year) {
        // Your existing logic to get all current bookings
        return this.authenticationDataAccess.getAllCurrentBookings(userId, month, year);
    }

    @Override
    public boolean checkForDuplicateBooking(int userID, String date, String time) {
        // Your existing logic to check for duplicate bookings
        return this.authenticationDataAccess.isBookingExists(userID, date, time);
    }
}
