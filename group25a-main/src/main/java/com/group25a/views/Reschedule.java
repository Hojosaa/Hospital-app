package com.group25a.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.group25a.application.Application;
import com.group25a.components.BackButton;
import com.group25a.components.ConfirmButton;
import com.group25a.components.Label;
import com.group25a.configuration.Configuration;
import com.group25a.models.AppState;
import com.group25a.models.Timings;
import com.group25a.models.User;
import com.group25a.services.IBookingService;
import com.group25a.services.IUserService;
import com.group25a.services.LoggerService;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class Reschedule implements ActionListener {
    private final Component parent;
    private final JButton confirmButton = new ConfirmButton("Confirm");
    private final JButton backButton = new BackButton("Back");
    private final JDateChooser bookingDate = new JDateChooser();
    private final JComboBox<java.sql.Time> timeComboBox = new JComboBox<java.sql.Time>(Timings.getTimingsAsArray());
    private final JLabel bookingDateLabel = new Label("Date");
    private final JLabel timeLabel = new Label("Time");
    JDialog dialog;

    private final Object bookingId;
    private final Object month;
    private final Object year;

    User user = AppState.getLoggedInUser();
    LoggerService loggerService = Application.resolveDependency(LoggerService.class);

    public Reschedule(Component parent, Object bookingID, Object month, Object year) {
        this.parent = parent;
        this.bookingId = bookingID;
        this.month = month;
        this.year = year;
    }

    public void show() {
        JPanel optionPanePanel = new JPanel();
        optionPanePanel.setLayout(null);
        optionPanePanel.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);
        optionPanePanel.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);

        // Setting the bounds for components
        bookingDateLabel.setBounds(150, 70, 300, 40);
        bookingDate.setBounds(150, 110, 300, 40);
        timeLabel.setBounds(150, 160, 300, 40);
        timeComboBox.setBounds(150, 200, 300, 40);
        confirmButton.setLocation(450, 300);
        backButton.setLocation(450, 350);

        // Setting foreground and background colours for components
        bookingDate.setForeground(new Color(245, 245, 245));
        bookingDate.setBackground(new Color(90, 90, 90));
        ((JTextFieldDateEditor) bookingDate.getDateEditor()).setForeground(new Color(245, 245, 245));
        ((JTextFieldDateEditor) bookingDate.getDateEditor()).setBackground(new Color(90, 90, 90));
        timeComboBox.setForeground(new Color(245, 245, 245));
        timeComboBox.setBackground(new Color(90, 90, 90));

        // Setting fonts for components
        bookingDate.setFont(bookingDate.getFont().deriveFont(14f));
        timeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Adding components to the main frame
        optionPanePanel.add(bookingDateLabel);
        optionPanePanel.add(bookingDate);
        optionPanePanel.add(timeComboBox);
        optionPanePanel.add(timeLabel);
        optionPanePanel.add(confirmButton);

        // Adding action listener to buttons
        backButton.addActionListener(this);
        confirmButton.addActionListener(this);

        // Setting panel colours
        optionPanePanel.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

        this.dialog = new JOptionPane().createDialog(this.parent, "Reschedule Bookings");
        dialog.setContentPane(optionPanePanel);
        dialog.setSize(Configuration.DIALOG_DIMENSION);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            JOptionPane.showMessageDialog(null, "You have successfully Booked an Appointment at our GP system");
            dialog.dispose();
            LoggerService.addGeneralLog(user.getUserId(), "Booking has been rescheduled");
            IBookingService service = Application.resolveDependency(IBookingService.class);
            // get the time from the time combo box, which is a string and convert it to a
            // Time object
            java.sql.Time time = Time.valueOf(timeComboBox.getSelectedItem().toString());
            java.sql.Date date = new java.sql.Date(bookingDate.getDate().getTime());
            service.updateBooking(Integer.parseInt(this.bookingId.toString()), date, time);
        }
        // When the back button is clicked, return to the main homepage
        if (e.getSource() == backButton) {
            dialog.dispose();
        }
    }
}
