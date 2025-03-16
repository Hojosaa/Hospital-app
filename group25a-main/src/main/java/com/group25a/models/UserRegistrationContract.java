package com.group25a.models;

import java.sql.Date;

public class UserRegistrationContract {
    private final String firstname;
    private final String lastname;
    private Date dob;
    private final Gender gender;
    private final String phoneNumber;
    private final String email;
    private final String address;
    private final String username;
    private final String password;
    private int doctorID;

    public UserRegistrationContract(String firstname, String lastname, Gender gender,Date dob, String phoneNumber,
                                    String email, String address, String username, String password, int doctorID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = new Date(System.currentTimeMillis());
        this.gender = gender;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.doctorID = doctorID;
    }
    // Getters for UserRegistrationContact fields
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getDob() {
        return dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }
}