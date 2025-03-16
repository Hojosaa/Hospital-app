package com.group25a.views;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import com.group25a.application.ApplicationWindow;
import com.group25a.configuration.Configuration;
import com.group25a.models.AppState;
import com.group25a.models.User;

// This class represents the main page of the application
public class MainPage extends JPanel implements ActionListener {

    private JLabel title = new JLabel();
    private JButton logOut = new JButton("Log Out");
    private JButton messages = new JButton("Messages");
    private JToolBar toolBar = new JToolBar();
    private JButton viewDoctors = new JButton("View Doctors");
    private JButton changeDoctor = new JButton("Change Doctor");
    private JButton home = new JButton("Home");
    private JButton bookings = new JButton("View/Reschedule Bookings");
    private JButton visitDetails = new JButton("Visit Details");
    private JButton bookAppointment = new JButton("Book Appointment");

    // Constructor
    public MainPage() {

        // Setting up the title label
        User user = AppState.getLoggedInUser();
        String username = user == null ? "" : user.getUsername() + " ";
        title.setText("Welcome " + username + "to our GP Booking System");
        title.setBounds(30, 50, 400, 35);
        title.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);

        // Setting panel layout
        this.setLayout(null);

        this.addItemToToolBar(home, messages, viewDoctors, changeDoctor, bookAppointment, bookings,
            
                visitDetails, logOut);

        // Toolbar Customisation
        toolBar.setBounds(30, 0, 800, 40);
        toolBar.setOpaque(false);
        toolBar.setFloatable(false);

        toolBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        toolBar.setMargin(new Insets(10, 10, 10, 10));

        // Variable colours
        Color blue = new Color(3, 111, 252);
        Color white = new Color(255, 255, 255);

        // Adding foreground and background colours to buttons in toolbar
        home.setForeground(white);
        messages.setForeground(white);
        viewDoctors.setForeground(white);
        changeDoctor.setForeground(white);
        bookAppointment.setForeground(white);
        bookings.setForeground(white);
        visitDetails.setForeground(white);
        logOut.setForeground(white);
        home.setBackground(blue);
        messages.setBackground(blue);
        viewDoctors.setBackground(blue);
        changeDoctor.setBackground(blue);
        bookAppointment.setBackground(blue);
        bookings.setBackground(blue);
        visitDetails.setBackground(blue);
        logOut.setBackground(blue);

        // Setting focusable to null
        home.setFocusable(false);
        messages.setFocusable(false);
        viewDoctors.setFocusable(false);
        changeDoctor.setFocusable(false);
        bookAppointment.setFocusable(false);
        bookings.setFocusable(false);
        visitDetails.setFocusable(false);
        logOut.setFocusable(false);

        // Setting panel colour
        this.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

        // Adding action listeners to buttons
        logOut.addActionListener(this);
        home.addActionListener(this);
        viewDoctors.addActionListener(this);
        changeDoctor.addActionListener(this);
        bookings.addActionListener(this);
        bookAppointment.addActionListener(this);
        visitDetails.addActionListener(this);
        messages.addActionListener(this);

        this.add(toolBar);
        this.add(title);
    }

    public void addItemToToolBar(JButton... buttons) {
        for (JButton button : buttons) {
            toolBar.add(button);
        }
    }

    // Action performed when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handling button clicks
        // Home button

        JPanel container = new JPanel();
        Optional<JPanel> body = Optional.empty();
        container.add(toolBar);

        container.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

        if (e.getSource() == home) {
            ApplicationWindow.getInstance().showPage(new MainPage());
        }

        // Messages button
        if (e.getSource() == messages) {
            JPanel panel = new Messages();
            body = Optional.of(panel);
        }

        // View Doctors button
        if (e.getSource() == viewDoctors) {

            JPanel panel = new ViewDoctors();
            body = Optional.of(panel);
        }

        // Change Doctor button
        if (e.getSource() == changeDoctor) {
            JPanel panel = new ChangeDoctor();
            body = Optional.of(panel);
        }

        // Book Appointment button
        if (e.getSource() == bookAppointment) {

            JPanel panel = new BookAppointment();
            body = Optional.of(panel);
        }

        // View  and Reschedule Bookings button
        if (e.getSource() == bookings) {
            JPanel panel = new Bookings();
            body = Optional.of(panel);
        }

        // Visit Details button
        if (e.getSource() == visitDetails) {
            JPanel panel = new VisitDetails();
            body = Optional.of(panel);
        }

        // Log Out button
        if (e.getSource() == logOut) {
            JPanel panel = new LoginPage();
            // body = Optional.of(panel);
            ApplicationWindow.getInstance().showPage(panel);
            return;
        }

        if (body.isPresent()) {
            container.add(body.get());
            ApplicationWindow.getInstance().showPage(container);
        }
    }

    public void refresh() {
        ApplicationWindow.getInstance().showPage(new MainPage());
    }
}
