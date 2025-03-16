package com.group25a.services;


import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.group25a.models.Booking;
import com.group25a.models.BookingCreation;


public interface IBookingService {

    void updateBooking(int bookingID, Date newDate, Time newTime);
    void addBooking(BookingCreation newBooking);
    List<Booking> getAllPastBookings(int UserID);
    List<Booking> getAllCurrentBookings(int userId, int month, int year);
    boolean checkForDuplicateBooking(int userID, String date, String time);
}
