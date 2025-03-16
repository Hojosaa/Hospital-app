package com.group25a.data_access;

import com.group25a.models.Prescription;

public interface IPrescriptionDataAccess {

    Prescription getPrescriptionByBookingID(int bookingID);
}
