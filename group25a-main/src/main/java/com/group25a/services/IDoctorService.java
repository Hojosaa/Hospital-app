package com.group25a.services;

import java.util.List;

import com.group25a.models.Doctors;

public interface IDoctorService {
    List<Doctors> getDoctors();
    Doctors getUserCurrentDoctorByID(int id);

}
