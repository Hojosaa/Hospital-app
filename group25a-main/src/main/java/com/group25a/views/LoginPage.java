package com.group25a.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import com.group25a.application.Application;
import com.group25a.application.ApplicationWindow;
import com.group25a.components.LoginButton;
import com.group25a.components.RegisterButton;
import com.group25a.configuration.Configuration;
import com.group25a.exceptions.custom_exceptions.InvalidCredentialsException;
import com.group25a.exceptions.custom_exceptions.UserNotFoundException;
import com.group25a.models.AppState;
import com.group25a.models.User;
import com.group25a.services.ILoggerService;
import com.group25a.services.IUserService;

// This class represents the login page of the application
public class LoginPage extends JPanel implements ActionListener {

    ILoggerService loggerService = Application.resolveDependency(ILoggerService.class);

    private final IUserService userService;

    // Initializing components
    private final JButton loginButton = new LoginButton("Login");
    private final JButton registerButton = new RegisterButton("Register");
    private final JTextField userText = new JTextField();
    private final JPasswordField passwordText = new JPasswordField();
    private final JLabel status = new JLabel("");
    private final JLabel title = new JLabel();
    private final JFrame window;
    JDialog dialog;

    // Constructor
    public LoginPage() {
        this.userService = Application.resolveDependency(IUserService.class);
        this.window = ApplicationWindow.getInstance();

        title.setText("Welcome to our GP Booking System");

        // Setting panel layout
        this.setLayout(null);
        // Setting text for User and Password
        userText.setText("Login");
        passwordText.setText("Password");
        userText.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        passwordText.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        userText.setCaretColor(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        passwordText.setCaretColor(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        userText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userText.getText().equals("Login")) {
                    userText.setText("");
                }
            }
        });

        passwordText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordText.getText().equals("Password")) {
                    passwordText.setText("");
                }
            }
        });

        // Setting bounds for Components
        title.setBounds(210, 120, 700, 35);
        userText.setBounds(280, 180, 300, 50);
        passwordText.setBounds(280, 240, 300, 50);
        status.setBounds(10, 110, 300, 25);
        loginButton.setLocation(280, 300);
        registerButton.setLocation(400, 300);

        // Adding foreground and background colours to components
        userText.setForeground(Color.GRAY);
        passwordText.setForeground(Color.GRAY);
        userText.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);
        passwordText.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

        // Setting Font size
        title.setFont(new Font("Arial", Font.BOLD, 29));
        userText.setFont(new Font("Arial", Font.ITALIC, 24));
        passwordText.setFont(new Font("Arial", Font.ITALIC, 24));

        // Panel and Title colours
        title.setForeground(new Color(245, 245, 245));
        this.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

        // Setting border to null and setting focusable to false
        registerButton.setFocusable(false);
        loginButton.setFocusable(false);

        // Adding components to the panel
        this.add(title);
        this.add(userText);
        this.add(passwordText);
        this.add(status);
        this.add(loginButton);
        this.add(registerButton);

        // Adding action listener to buttons
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    // public void autoLogin() {
    // User user;
    // try {
    // user = userService.authenticate("daniel", "rizz");
    // AppState.setLoggedInUser(user);
    // JPanel panel = new MainPage();
    // ApplicationWindow.getInstance().showPage(panel);
    // } catch (UserNotFoundException e) {
    // e.printStackTrace();
    // } catch (InvalidCredentialsException e) {
    // e.printStackTrace();
    // }
    // }

    // Action performed when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userText.getText();
        String password = passwordText.getText();

        // Handling login button click
        if (e.getSource() == loginButton) {
            try {
                var user = userService.authenticate(username, password);
                JOptionPane.showMessageDialog(null, "You have successfully logged into our GP Booking System!");
                AppState.setLoggedInUser(user);
                loggerService.addUserLog(user.getUserId(),user.getUserId() + ", has successfully logged into the GP system");
                loggerService.addUserLog(user.getUserId(), user.getUserId() + ", has logged into the GP system");
                JPanel panel = new MainPage();
                ApplicationWindow.getInstance().showPage(panel);

            } catch (UserNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
            } catch (InvalidCredentialsException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Credentials");
            }
        }

        // Handling register button click
        if (e.getSource() == registerButton) {
            JPanel panel = new PatientRegistration();
            ApplicationWindow.getInstance().setContentPane(panel);
        }
    }
}
