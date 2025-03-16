package com.group25a.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.group25a.application.Application;
import com.group25a.components.BackButton;
import com.group25a.components.ConfirmButton;
import com.group25a.components.Label;
import com.group25a.configuration.Configuration;
import com.group25a.models.AppState;
import com.group25a.models.BookingCreation;
import com.group25a.models.Doctors;
import com.group25a.models.Timings;
import com.group25a.models.User;
import com.group25a.services.IBookingService;
import com.group25a.services.IDoctorService;
import com.group25a.services.ILoggerService;
import com.group25a.services.LoggerService;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

// This class represents the GUI for viewing bookings
public class BookAppointment extends JPanel implements ActionListener {
    User user = AppState.getLoggedInUser();
    IDoctorService doctorService = Application.resolveDependency(IDoctorService.class);
    IBookingService bookingService = Application.resolveDependency(IBookingService.class);
    ILoggerService loggerService = Application.resolveDependency(ILoggerService.class);

    // Initializing Swing components
    private final JButton confirmButton = new ConfirmButton("Confirm");
    private final JButton backButton = new BackButton("Back");
    private final JDateChooser bookingDate = new JDateChooser();
    private final JComboBox<java.sql.Time> timeComboBox = new JComboBox<>(Timings.getTimingsAsArray());
    private final JComboBox<Doctors> doctorComboBox = new JComboBox<>();
    private final JLabel bookingDateLabel = new Label("Date");
    private final JLabel timeLabel = new Label("Time");
    private final JLabel doctorLabel = new Label("Doctor");

    // Constructor
    BookAppointment() {
        // Filling doctor combo box
        var doctorList = doctorService.getDoctors();
        for (Doctors doctor : doctorList) {
            doctorComboBox.addItem(doctor);
        }

        // Setting panel layout to null
        this.setLayout(null);

        // Setting the bounds for components
        doctorLabel.setBounds(250, 40, 350, 40);
        doctorComboBox.setBounds(250, 80, 350, 40);
        bookingDateLabel.setBounds(250, 130, 350, 40);
        bookingDate.setBounds(250, 170, 350, 40);
        timeLabel.setBounds(250, 220, 350, 40);
        timeComboBox.setBounds(250, 260, 350, 40);
        confirmButton.setLocation(750, 350);
        backButton.setLocation(50, 350);

        // Setting foreground and background colours for components
        doctorComboBox.setForeground(new Color(245, 245, 245));
        doctorComboBox.setBackground(new Color(90, 90, 90));
        bookingDate.setForeground(new Color(245, 245, 245));
        bookingDate.setBackground(new Color(90, 90, 90));
        ((JTextFieldDateEditor) bookingDate.getDateEditor()).setForeground(new Color(245, 245, 245));
        ((JTextFieldDateEditor) bookingDate.getDateEditor()).setBackground(new Color(90, 90, 90));
        timeComboBox.setForeground(new Color(245, 245, 245));
        timeComboBox.setBackground(new Color(90, 90, 90));

        // Setting fonts for components
        doctorComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        bookingDate.setFont(bookingDate.getFont().deriveFont(14f));
        timeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Setting editsble of date to false so users cannot type in the date
        ((JTextFieldDateEditor) bookingDate.getDateEditor()).setEditable(false);

        // Adding components to the main frame
        this.add(bookingDateLabel);
        this.add(bookingDate);
        this.add(timeComboBox);
        this.add(timeLabel);
        this.add(doctorLabel);
        this.add(doctorComboBox);
        this.add(confirmButton);
        this.add(backButton);

        // Adding action listener to buttons
        backButton.addActionListener(this);
        confirmButton.addActionListener(this);

        // Setting panel colours
        this.setBackground(new Color(22, 22, 22));

        // Configurations for the panel
        this.setSize(Configuration.WINDOW_DIMENSION);
        this.setPreferredSize(this.getSize());
        this.setMaximumSize(this.getSize());
        this.setMinimumSize(this.getSize());
        this.setVisible(true);
    }

    // Action performed when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {

        // OBTAIN params for all fields
        int userID = user.getUserId();
        int doctorID = ((Doctors) doctorComboBox.getSelectedItem()).getDoctorId();

        Date date = new Date(bookingDate.getDate().getTime());
        String time = timeComboBox.getSelectedItem().toString();

        // add all these things into a Booking Creation object
        BookingCreation newBooking = new BookingCreation(userID, doctorID, date, time);
        // when confrim is clicked add to Booking table
        if (e.getSource() == confirmButton) {
            if(!bookingService.checkForDuplicateBooking(doctorID, date.toString(), time)) {
                JOptionPane.showMessageDialog(null, "You have successfully booked an appointment at our GP system");
                bookingService.addBooking(newBooking);
                System.out.println(newBooking);
                new MainPage();
                LoggerService.addGeneralLog(userID,
                        "User has booked an appointment with doctor " + doctorID + " on " + date + " at " + time);
            } else {
                JOptionPane.showMessageDialog(null, "Timeslot with this doctor is already taken, please try another");
            }
        }

        // When the back button is clicked, return to the main homepage
        if (e.getSource() == backButton) {
            new MainPage();
        }
    }
}
