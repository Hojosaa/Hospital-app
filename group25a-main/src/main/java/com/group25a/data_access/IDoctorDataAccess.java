package com.group25a.data_access;

import java.util.List;

import com.group25a.models.Doctors;

public interface IDoctorDataAccess {
    List<Doctors> getAllDoctor();
    Doctors getDoctorByID(int id);
} 
