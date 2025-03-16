package com.group25a.models;

import java.util.Date;

public class Prescription {
    private  int bookingID;
    private  String bookingSummary;
    private  String prescription;
    private  Date prescriptionLength;

    public Prescription(int bookingID, String bookingSummary, String prescription, Date prescriptionLength) {
        this.bookingID = bookingID;
        this.bookingSummary = bookingSummary;
        this.prescription = prescription;
        this.prescriptionLength = prescriptionLength;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getBookingSummary() {
        return bookingSummary;
    }

    public void setBookingSummary(String bookingSummary) {
        this.bookingSummary = bookingSummary;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Date getPrescriptionLength() {
        return prescriptionLength;
    }

    public void setPrescriptionLength(Date prescriptionLength) {
        this.prescriptionLength = prescriptionLength;
    }
}
