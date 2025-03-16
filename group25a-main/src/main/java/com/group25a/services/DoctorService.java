package com.group25a.services;


import java.util.List;

import com.group25a.data_access.IDoctorDataAccess;
import com.group25a.models.Doctors;

public class DoctorService implements IDoctorService {
    private final IDoctorDataAccess authenticationDataAccess;

    public DoctorService(IDoctorDataAccess authenticationDataAccess) {
        this.authenticationDataAccess = authenticationDataAccess;
    }

    @Override
    public List<Doctors> getDoctors() {
        return this.authenticationDataAccess.getAllDoctor();

    }
    @Override
    public Doctors getUserCurrentDoctorByID(int id){
        return  this.authenticationDataAccess.getDoctorByID(id);
    }


}



