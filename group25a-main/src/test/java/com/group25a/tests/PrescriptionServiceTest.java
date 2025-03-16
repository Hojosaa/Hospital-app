package com.group25a.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group25a.data_access.IPrescriptionDataAccess;
import com.group25a.models.Prescription;
import com.group25a.services.PrescriptionService;

import java.util.Date;

public class PrescriptionServiceTest {

    private PrescriptionService prescriptionService;
    private IPrescriptionDataAccess prescriptionDataAccess;

    @BeforeEach
    public void setUp() {
        prescriptionDataAccess = mock(IPrescriptionDataAccess.class);
        prescriptionService = new PrescriptionService(prescriptionDataAccess);
    }

    @Test
    public void testGetPrescriptionByID_ValidID() {
        // Arrange
        int bookingID = 123;
        Prescription expectedPrescription = new Prescription(bookingID, "Summary", "Medicine A", new Date());
        when(prescriptionDataAccess.getPrescriptionByBookingID(bookingID)).thenReturn(expectedPrescription);

        // Act
        Prescription actualPrescription = prescriptionService.getPrescriptionByID(bookingID);

        // Assert
        assertNotNull(actualPrescription);
        assertEquals(expectedPrescription, actualPrescription);
    }

    @Test
    public void testGetPrescriptionByID_InvalidID() {
        // Arrange
        int invalidBookingID = -1;
        when(prescriptionDataAccess.getPrescriptionByBookingID(invalidBookingID)).thenReturn(null);

        // Act
        Prescription actualPrescription = prescriptionService.getPrescriptionByID(invalidBookingID);

        // Assert
        assertNull(actualPrescription);
    }

    @Test
    public void testGetPrescriptionByID_NullDataAccess() {
        // Arrange
        prescriptionService = new PrescriptionService(prescriptionDataAccess); // Initialize with non-null prescriptionDataAccess

        // Act
        Prescription actualPrescription = prescriptionService.getPrescriptionByID(123);

        // Assert
        assertNull(actualPrescription);
    } 
}
