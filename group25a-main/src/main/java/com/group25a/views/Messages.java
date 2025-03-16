package com.group25a.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.group25a.application.Application;
import com.group25a.components.BackButton;
import com.group25a.components.ScrollPane;
import com.group25a.configuration.Configuration;
import com.group25a.models.AppState;
import com.group25a.models.Logger;
import com.group25a.services.ILoggerService;

// This class represents the messages page of the application
public class Messages extends JPanel implements ActionListener {
    ILoggerService loggerService = Application.resolveDependency(ILoggerService.class);
    private final BackButton backButton = new BackButton("Back");
    private final JTextArea textField = new JTextArea();
    private JScrollPane scrollPane = new ScrollPane(textField);

    // Constructor
    Messages()  {

        // Setting panel layout to null
        this.setLayout(null);

        //Set the textfield editable to false
        textField.setEditable(false);

        // Setting Bounds
        backButton.setLocation(80, 350);
        scrollPane.setLocation(80,30);
        //populate it with logs
        try {
            var userLogs = loggerService.getAllUserLogs(AppState.getLoggedInUser().getUserId());
            for (Logger userLog : userLogs) {
                textField.append(userLog.getMessage() + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Adding action listener to buttons
        backButton.addActionListener(this);

        // adding buttons to panel
        this.add(backButton);
        this.add(scrollPane);

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
        // Handling button clicks
        // Back button
        if (e.getSource() == backButton) {
            new MainPage();
        }
    }
}
