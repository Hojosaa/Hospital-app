package com.group25a.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.group25a.application.Application;
import com.group25a.components.BackButton;
import com.group25a.components.ConfirmButton;
import com.group25a.components.Label;
import com.group25a.configuration.Configuration;
import com.group25a.exceptions.custom_exceptions.DoctorChangeException;
import com.group25a.models.AppState;
import com.group25a.models.Doctors;
import com.group25a.models.User;
import com.group25a.services.IDoctorService;
import com.group25a.services.ILoggerService;
import com.group25a.services.IUserService;
import com.group25a.services.LoggerService;

// This class represents the messages page of the application
public class ChangeDoctor extends JPanel implements ActionListener {
    User user = AppState.getLoggedInUser();
    private final IUserService userService;
    private final ILoggerService loggerService = Application.resolveDependency(ILoggerService.class);

    // Initializing Swing components
    private final JButton backButton = new BackButton("Back");
    private final JButton confirmButton = new ConfirmButton("Confirm");
    private final JComboBox<Doctors> doctorComboBox = new JComboBox<>();
    JLabel changeDoctorLabel = new Label("Select a doctor");
    JLabel currentDoctorLabel = new Label("Current Doctor");
    private MainPage mainPage;

    // Constructor
    ChangeDoctor() {
        this.mainPage = new MainPage();
        this.userService = Application.resolveDependency(IUserService.class);
        IDoctorService doctorService = Application.resolveDependency(IDoctorService.class);
        User user = AppState.getLoggedInUser();
        Doctors curDoctor = doctorService.getUserCurrentDoctorByID(user.getDoctorID());
        System.out.println(curDoctor);

        // Filling doctor combo box
        var doctorList = doctorService.getDoctors();
        // System.out.println(doctorList);
        for (Doctors doctor : doctorList) {
            if (doctor.getDoctorId() != curDoctor.getDoctorId()) {
                doctorComboBox.addItem(doctor);
            }
        }

        // Setting panel layout to null
        this.setLayout(null);

        // Setting up Textfield for current doctor
        JTextField currentDoctorText = new JTextField(curDoctor.getDoctorName());

        // Setting bounds for components
        currentDoctorLabel.setBounds(250, 50, 400, 50);
        changeDoctorLabel.setBounds(250, 150, 400, 50);
        currentDoctorText.setBounds(250, 100, 400, 50);
        doctorComboBox.setBounds(250, 200, 400, 50);
        confirmButton.setLocation(750, 350);
        backButton.setLocation(50, 350);

        // Setting foreground and background colours for components
        currentDoctorText.setForeground(new Color(245, 245, 245));
        currentDoctorText.setBackground(new Color(90, 90, 90));
        doctorComboBox.setForeground(new Color(245, 245, 245));
        doctorComboBox.setBackground(new Color(90, 90, 90));

        // Setting fonts for components
        currentDoctorText.setFont(new Font("Arial", Font.PLAIN, 16));
        doctorComboBox.setFont(new Font("Arial", Font.PLAIN, 16));

        // Disabling editing textfield
        currentDoctorText.setEditable(false);

        // Adding labels and text fields to the panel
        this.add(currentDoctorText);
        this.add(changeDoctorLabel);
        this.add(currentDoctorLabel);
        this.add(doctorComboBox);
        this.add(confirmButton);
        this.add(backButton);

        // Adding action listener to buttons
        confirmButton.addActionListener(this);
        backButton.addActionListener(this);

        // Setting panel colours
        this.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

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

        var doctor = (Doctors) doctorComboBox.getSelectedItem();
        if (doctor == null) {
            return;
        }
        System.out.println(doctor.getDoctorName() + " " + doctor.getDoctorId());
        int doctorID = ((Doctors) doctorComboBox.getSelectedItem()).getDoctorId();

        // Adding dialogue message to confirm button
        if (e.getSource() == confirmButton) {
            int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure you want to change your doctor?",
                    "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogButton == JOptionPane.YES_OPTION) {
                try {
                    userService.changeUserDoctor(user.getUserId(), doctorID);
                    JOptionPane.showMessageDialog(null, "You have successfully changed your doctor!");
                    LoggerService.addGeneralLog(user.getUserId(), "User " + user.getUserId() + " has changed their doctor to " + doctorID);
                    user.setDoctorID(doctorID);

                    // Get the parent window and dispose it
                    mainPage.refresh();
                } catch (DoctorChangeException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        // Handling button clicks
        // Back button
        if (e.getSource() == backButton) {
            new MainPage();
        }
    }
}
