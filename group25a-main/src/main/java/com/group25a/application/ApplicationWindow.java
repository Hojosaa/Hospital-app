package com.group25a.application;

import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.group25a.database.IDBManager;
import com.group25a.services.IUserService;
import com.group25a.viewhistory.History;
import com.group25a.views.LoginPage;

public final class ApplicationWindow extends JFrame {

    private final IUserService userService;

    private static ApplicationWindow instance;

    private final History viewHistory = new History();

    public static ApplicationWindow getInstance() {
        if (instance == null) {
            instance = new ApplicationWindow();
        }
        return instance;
    }

    // Constructor
    private ApplicationWindow() {
        this.userService = Application.resolveDependency(IUserService.class);

        // Title and welcome message for the Frame
        this.setTitle("Patient Booking GP System");

        this.setSize(900, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    Application.resolveDependency(IDBManager.class).getConnection().close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowIconified(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowDeiconified(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowActivated(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowDeactivated(java.awt.event.WindowEvent e) {
            }

        });
    }

    public void showDefaultPage() {
        LoginPage LoginPage = new LoginPage();
        this.showPage(LoginPage);
        // LoginPage.autoLogin();
    }

    public IUserService getUserService() {
        return userService;
    }

    public void showPage(JPanel panel) {
        showPage(panel, true);
    }

    public void showPage(JPanel panel, boolean addToHistory) {
        if (addToHistory) {
            viewHistory.addView(panel);
        }

        this.setContentPane(panel);
        this.setVisible(true);

        this.validate();
        this.revalidate();
        this.repaint();
    }

    public History getHistory() {
        return viewHistory;
    }
}
