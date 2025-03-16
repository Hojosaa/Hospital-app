package com.group25a.models;
import java.sql.Time;

public class Booking {
    private int BookingID, DoctorID, UserID;
    private String Date;
    private Time bookingTime;
    private Boolean bookingStatus;
    
    //int, int, int, Date, Time, boolean
    public Booking(int BookingID, int DoctorID, int UserID, String date, Time bookingTime, Boolean bookingStatus) {
        this.BookingID = BookingID;
        this.DoctorID = DoctorID;
        this.UserID = UserID;
        this.Date = date;
        this.bookingTime = bookingTime;
        this.bookingStatus = bookingStatus;
    }

    //do getters and getting setter
    public int getBookingID() {
        return BookingID;
    }
    public void setBookingID(int BookingID) {
        this.BookingID = BookingID;
    }
    public int getDoctorID() {
        return DoctorID;
    }
    public void setDoctorID(int DoctorID) {
        this.DoctorID = DoctorID;
    }
    public int getUserID() {
        return UserID;
    }
    public String  getDate() {
        return Date;
    }
    public Time getTime(){return bookingTime;}
    public void setDate(String Date) {
        this.Date = Date;
    }
    public Boolean getStatus() {
        return bookingStatus;
    }
    public void setStatus(Boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}

