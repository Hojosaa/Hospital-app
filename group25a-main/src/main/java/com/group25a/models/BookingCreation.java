package com.group25a.models;

import java.util.Date; // Make sure this matches with the type used in BookingServiceTest.


public class BookingCreation {
    private int doctorID, userID;
    private Date bookingDate; // This should match with the type used in the test.
    private String bookingTime; // java.sql.Time is typically used for time.
    
    
    public BookingCreation(int doctorID, int userID, Date bookingDate, String bookingTime) {
        this.doctorID = doctorID;
        this.userID = userID;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
    }


    public int getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }
    public int getUserID() {
        return userID;
    }
    public Date getDate() {
        return bookingDate;
    }
    public String getTime(){return bookingTime;}
    public void setDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
