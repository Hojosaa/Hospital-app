package com.group25a.data_access;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.group25a.models.Booking;
import com.group25a.models.BookingCreation;


public interface IBookingDataAccess {
    void addBooking(BookingCreation booking);
    void updateBooking(int bookingID, Date newDate, Time newtime);
    List<Booking> getAllPastBookings(int UserID);
    List<Booking> getAllCurrentBookings(int userId, int month, int year);
    boolean isBookingExists(int doctorID, String date, String time);
}
