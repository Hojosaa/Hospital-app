package com.group25a.services;

import com.group25a.models.Prescription;

public interface IPrescriptionService {

    Prescription getPrescriptionByID(int bookingID);
}
