package com.group25a.tests;

import com.group25a.data_access.IDoctorDataAccess;
import com.group25a.models.Doctors;
import com.group25a.services.DoctorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class DoctorServiceTest {

    @Mock
    private IDoctorDataAccess mockDoctorDataAccess;

    private DoctorService doctorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorService = new DoctorService(mockDoctorDataAccess);
    }

    @Test
    public void testGetDoctors() {
        List<Doctors> expectedDoctors = Arrays.asList(
            new Doctors(1, "Doctor One", "Specialization One", null, null),
            new Doctors(2, "Doctor Two", "Specialization Two", null, null)
        );
        when(mockDoctorDataAccess.getAllDoctor()).thenReturn(expectedDoctors);

        List<Doctors> actualDoctors = doctorService.getDoctors();

        assertEquals(expectedDoctors, actualDoctors, "The returned doctors list should match the expected list.");
    }

    @Test
    public void testGetUserCurrentDoctorByID() {
        int doctorId = 1;
        Doctors expectedDoctor = new Doctors(doctorId, "Doctor One", "Specialization One", null, null);

        when(mockDoctorDataAccess.getDoctorByID(doctorId)).thenReturn(expectedDoctor);

        Doctors actualDoctor = doctorService.getUserCurrentDoctorByID(doctorId);

        assertEquals(expectedDoctor, actualDoctor, "The returned doctor should match the expected doctor.");
    }

    @Test
    public void testGetDoctors_EmptyList() {
        when(mockDoctorDataAccess.getAllDoctor()).thenReturn(Arrays.asList());

        List<Doctors> actualDoctors = doctorService.getDoctors();

        assertTrue(actualDoctors.isEmpty(), "The doctors list should be empty.");
    }

    @Test
    public void testGetDoctorByID_NotFound() {
        int nonExistingId = 999;
        when(mockDoctorDataAccess.getDoctorByID(nonExistingId)).thenReturn(null);

        Doctors doctor = doctorService.getUserCurrentDoctorByID(nonExistingId);

        assertNull(doctor, "The returned doctor should be null for a non-existing ID.");
    }
}
