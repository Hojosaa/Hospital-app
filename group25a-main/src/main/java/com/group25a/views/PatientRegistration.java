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
import javax.swing.JTextField;

import com.group25a.application.Application;
import com.group25a.application.ApplicationWindow;
import com.group25a.components.Label;
import com.group25a.components.Textfield;
import com.group25a.configuration.Configuration;
import com.group25a.exceptions.custom_exceptions.InvalidEmailException;
import com.group25a.exceptions.custom_exceptions.InvalidUsernameException;
import com.group25a.exceptions.custom_exceptions.ValidationException;
import com.group25a.models.Doctors;
import com.group25a.models.Gender;
import com.group25a.models.UserRegistrationContract;
import com.group25a.services.IDoctorService;
import com.group25a.services.ILoggerService;
import com.group25a.services.IUserService;
import com.group25a.services.LoggerService;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

// This class represents the patient registration form
public class PatientRegistration extends JPanel implements ActionListener {

    private final IUserService userService;

    // Initialising Swing components
    private final JButton registerButton = new JButton("Register");
    private final JButton backButton = new JButton("Back");
    private final JTextField firstNameText = new Textfield();
    private final JTextField surnameText = new Textfield();
    private final JDateChooser dobText = new JDateChooser();
    private final JComboBox<String> genderComboBox = new JComboBox<>(Gender.genders());
    private final JComboBox<Doctors> doctorComboBox = new JComboBox<>();
    private final JTextField mobileNumberText = new Textfield();
    private final JTextField emailtext = new Textfield();
    private final JTextField AddressText = new Textfield();
    private final JTextField loginText = new Textfield();
    private final JTextField passwordText = new Textfield();
    private final JLabel firstNameLabel = new Label("FirstName");
    private final JLabel surnameLabel = new Label("Surname");
    private final JLabel dobLabel = new Label("Date of Birth");
    private final JLabel genderLabel = new Label("Gender ");
    private final JLabel mobileNumberLabel = new Label("Mobile Number");
    private final JLabel emailLabel = new Label("Email");
    private final JLabel AddressLabel = new Label("Address");
    private final JLabel loginLabel = new Label("Login");
    private final JLabel passwordLabel = new Label("Password");
    private final JLabel doctorsLabel = new Label("Doctors");
    private final JLabel status = new Label("");
    ILoggerService loggerService = Application.resolveDependency(ILoggerService.class);

    // Constructor
    public PatientRegistration() {

        IDoctorService doctorService = Application.resolveDependency(IDoctorService.class);
        this.userService = Application.resolveDependency(IUserService.class);

        this.setLayout(null);

        // Filling doctor combo box
        var doctorList = doctorService.getDoctors();
        for (Doctors doctor : doctorList) {
            doctorComboBox.addItem(doctor);
        }

        // Setting bounds for labels
        firstNameLabel.setLocation(150, 20);
        surnameLabel.setLocation(150, 60);
        dobLabel.setLocation(150, 100);
        genderLabel.setLocation(150, 140);
        mobileNumberLabel.setLocation(150, 180);
        emailLabel.setLocation(150, 220);
        AddressLabel.setLocation(150, 260);
        loginLabel.setLocation(150, 300);
        passwordLabel.setLocation(150, 340);
        doctorsLabel.setLocation(150, 380);

        // Adjusting text fields and combo boxes
        firstNameText.setLocation(270, 20);
        surnameText.setLocation(270, 60);
        dobText.setBounds(270, 100, 400, 25);
        genderComboBox.setBounds(270, 140, 400, 25);
        mobileNumberText.setLocation(270, 180);
        emailtext.setLocation(270, 220);
        AddressText.setLocation(270, 260);
        loginText.setLocation(270, 300);
        passwordText.setLocation(270, 340);
        doctorComboBox.setBounds(270, 380, 400, 25);

        // Adjusting buttons
        registerButton.setBounds(270, 420, 150, 25);
        backButton.setBounds(520, 420, 150, 25);

        // Adding foreground and background colours to the components
        ((JTextFieldDateEditor) dobText.getDateEditor()).setForeground(new Color(245, 245, 245));
        genderComboBox.setForeground(new Color(245, 245, 245));
        doctorComboBox.setForeground(new Color(245, 245, 245));
        registerButton.setForeground(new Color(245, 245, 245));
        backButton.setForeground(new Color(245, 245, 245));
        ((JTextFieldDateEditor) dobText.getDateEditor()).setBackground(new Color(105, 105, 105));
        genderComboBox.setBackground(new Color(105, 105, 105));
        doctorComboBox.setBackground(new Color(105, 105, 105));
        registerButton.setBackground(new Color(11, 132, 35));
        backButton.setBackground(new Color(178, 34, 34));

        // Setting the fonts for components
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Setting border to null and setting focusable to null
        dobText.setBorder(null);
        genderComboBox.setBorder(null);
        doctorComboBox.setBorder(null);
        registerButton.setFocusable(false);
        backButton.setFocusable(false);

        // Configurations for the panel
        this.setSize(Configuration.WINDOW_DIMENSION);
        this.setPreferredSize(this.getSize());
        this.setVisible(true);

        // Setting panel colours
        this.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

        // Adding labels to the panel
        this.add(firstNameLabel);
        this.add(surnameLabel);
        this.add(dobLabel);
        this.add(genderLabel);
        this.add(mobileNumberLabel);
        this.add(emailLabel);
        this.add(AddressLabel);
        this.add(loginLabel);
        this.add(passwordLabel);
        this.add(doctorsLabel);
        this.add(status);

        // Adding text fields to the panel
        this.add(firstNameText);
        this.add(surnameText);
        this.add(dobText);
        this.add(genderComboBox);
        this.add(mobileNumberText);
        this.add(emailtext);
        this.add(AddressText);
        this.add(loginText);
        this.add(passwordText);
        this.add(doctorComboBox);

        // Adding buttons to panel
        this.add(registerButton);
        this.add(backButton);

        // Adding Action Listeners
        registerButton.addActionListener(this);
        backButton.addActionListener(this);

        // TODO testing purposes rmemove when finished
        // firstNameText.setText("John");
        // surnameText.setText("Doe");
        // dobText.setDate(new Date(System.currentTimeMillis()));
        // genderComboBox.setSelectedIndex(0);
        // mobileNumberText.setText("07123456789");
        // emailtext.setText("asd@gmail.com");
        // AddressText.setText("123 Fake Street");
        // loginText.setText("johnDoe");
        // passwordText.setText("password");
    }

    // Action performed when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        // Extracting values from the GUI components
        String firstName = firstNameText.getText();
        String surname = surnameText.getText();
        Date dob = null;
        if (dobText.getDate() != null) {
            dob = new Date(dobText.getDate().getTime());
        }
        Gender gender = Gender.valueOf(genderComboBox.getSelectedItem().toString());
        String mobileNumber = mobileNumberText.getText();
        String email = emailtext.getText();
        String address = AddressText.getText();
        String username = loginText.getText();
        String password = passwordText.getText();
        int doctorID = doctorComboBox.getSelectedIndex() + 1;

        UserRegistrationContract user = new UserRegistrationContract(firstName, surname, gender, dob, mobileNumber,
                email, address, username, password, doctorID);

        // Handling button actions
        if (e.getSource() == registerButton) {
            // Registration button
            if (!firstName.isEmpty() && !surname.isEmpty() && !mobileNumber.isEmpty() && !email.isEmpty()
                    && !address.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                if (mobileNumber.length() == 11) {
                    if (email.contains("@") && (email.endsWith(".com") || email.endsWith(".co.uk"))) {
                        try {
                            userService.register(user);
                            JOptionPane.showMessageDialog(null, "You have successfully registered at our GP system");
                            JPanel panel = new LoginPage();
                            ApplicationWindow.getInstance().showPage(panel);
                        } catch (InvalidUsernameException ex) {
                            JOptionPane.showMessageDialog(null, "Username taken, please choose another one");
                        } catch (InvalidEmailException ex) {
                            JOptionPane.showMessageDialog(null, "Email in use, please use another one");
                        } catch (ValidationException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a valid email address. Make sure there is an @ or .com/.co.uk");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid phone number (11 digits)");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter all fields");
            }
        }

        // Handling back button action to return to the login page
        if (e.getSource() == backButton) {
            JPanel panel = new LoginPage();
            ApplicationWindow.getInstance().showPage(panel);
        }
    }
}
