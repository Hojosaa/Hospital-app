package com.group25a.models;

public class Doctors {

    private int doctorID;
    private String doctorName;
    private String phoneNumber;
    private String background;
    private String fieldSpeciality;

    public Doctors(int doctorID, String doctorName, String phoneNumber, String background, String fieldSpeciality) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.phoneNumber = phoneNumber;
        this.background = background;
        this.fieldSpeciality = fieldSpeciality;
    }
    // Getters for the Doctor's ID and Name and Last Name
    public int getDoctorId() {
        return doctorID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getFieldSpeciality() {
        return fieldSpeciality;
    }

    public void setFieldSpeciality(String fieldSpeciality) {
        this.fieldSpeciality = fieldSpeciality;
    }

    public String getDoctorName(){
        return doctorName;
    }

    public void setDoctorId(int doctorID) {
        this.doctorID = doctorID;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String toString(){
        return doctorName;
    }



}
