package com.group25a.models;

import java.sql.Date;

public class DoctorAvailibility {

    private int doctorID;
    private Date availableDate;
    private String startTime;
    private String endTime;

    public DoctorAvailibility(int doctorID, Date availableDate, String startTime, String endTime) {
        this.doctorID = doctorID;
        this.availableDate = availableDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
