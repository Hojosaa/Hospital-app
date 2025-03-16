package com.group25a.services;

import com.group25a.data_access.IPrescriptionDataAccess;

import com.group25a.models.Prescription;

public class PrescriptionService implements  IPrescriptionService {
    private final IPrescriptionDataAccess authenticationDataAccess;

    public PrescriptionService(IPrescriptionDataAccess authenticationDataAccess) {
        this.authenticationDataAccess = authenticationDataAccess;
    }

    @Override
    public Prescription getPrescriptionByID(int bookingID) {
        return this.authenticationDataAccess.getPrescriptionByBookingID(bookingID);
    }
}
