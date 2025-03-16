

package com.group25a.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.group25a.data_access.IBookingDataAccess;
import com.group25a.models.Booking;
import com.group25a.models.BookingCreation;
import com.group25a.services.BookingService;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


public class BookingServiceTest {

    private IBookingDataAccess bookingDataAccessMock;
    private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        bookingDataAccessMock = mock(IBookingDataAccess.class);
        bookingService = new BookingService(bookingDataAccessMock);
    }

    @Test
public void testAddBooking_CapturesCorrectParameters() {
    int doctorID = 1;
    int userID = 1;
    // Setting the booking date to a future date
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 1); // Adds one day to the current date
    Date futureBookingDate = calendar.getTime();
    String bookingTime = "10:00"; // Assuming this time is valid for booking

    BookingCreation newBooking = new BookingCreation(doctorID, userID, futureBookingDate, bookingTime);

    bookingService.addBooking(newBooking);

    ArgumentCaptor<BookingCreation> bookingCaptor = ArgumentCaptor.forClass(BookingCreation.class);
    verify(bookingDataAccessMock).addBooking(bookingCaptor.capture());
    BookingCreation capturedBooking = bookingCaptor.getValue();

    assertEquals(doctorID, capturedBooking.getDoctorID());
    assertEquals(userID, capturedBooking.getUserID());
    assertEquals(futureBookingDate, capturedBooking.getDate());
    assertEquals(bookingTime, capturedBooking.getTime());
}
   @Test
public void testGetAllCurrentBookings_ReturnsBookingsList() {
    // Example parameters
    int userId = 1; // Assuming a test user ID
    int month = 4; // Assuming April
    int year = 2023; // Assuming the year 2023

    Booking booking1 = mock(Booking.class); // Using mock for simplicity, replace with actual Booking instances as needed
    Booking booking2 = mock(Booking.class);
    List<Booking> expectedBookings = Arrays.asList(booking1, booking2);

    // Configure the mock to return the expected list of bookings when the specific userId, month, and year are provided
    when(bookingDataAccessMock.getAllCurrentBookings(userId, month, year)).thenReturn(expectedBookings);

    // Execute the method to test
    List<Booking> actualBookings = bookingService.getAllCurrentBookings(userId, month, year);

    // Verify the interaction with the mock and correctness of the result
    verify(bookingDataAccessMock).getAllCurrentBookings(userId, month, year);
    assertEquals(expectedBookings, actualBookings, "The returned list of bookings should match the expected list.");
}

    @Test
    public void testGetAllPastBookingsForUser_ReturnsBookingsList() {
        int userId = 1;
        Booking pastBooking1 = mock(Booking.class);
        Booking pastBooking2 = mock(Booking.class);
        List<Booking> expectedPastBookings = Arrays.asList(pastBooking1, pastBooking2);

        when(bookingDataAccessMock.getAllPastBookings(userId)).thenReturn(expectedPastBookings);

        List<Booking> actualPastBookings = bookingService.getAllPastBookings(userId);

        verify(bookingDataAccessMock).getAllPastBookings(userId);
        assertEquals(expectedPastBookings, actualPastBookings, "The returned list of past bookings should match the expected list.");
    }
@Test
    public void testAddBookingInPast_ThrowsException() {
        int doctorID = 1;
        int userID = 1;
        Date pastBookingDate = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pastBookingDate = sdf.parse("2000-01-01"); // Use an obviously past date
        } catch (ParseException e) {
            fail("Failed to parse date for testing.");
        }

        String pastBookingTime = "10:00";
        BookingCreation pastBooking = new BookingCreation(doctorID, userID, pastBookingDate, pastBookingTime);

        assertThrows(IllegalArgumentException.class, () -> bookingService.addBooking(pastBooking), "Booking in the past should throw IllegalArgumentException.");
    }
}


