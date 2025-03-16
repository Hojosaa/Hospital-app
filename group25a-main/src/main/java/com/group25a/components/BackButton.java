package com.group25a.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.group25a.application.ApplicationWindow;
import com.group25a.configuration.Configuration;
import com.group25a.views.MainPage;

public class BackButton extends JButton implements ActionListener {

    public BackButton(String text) {
        super(text);
        this.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        this.setBackground(new Color(178, 34, 34));
        this.setFont(Configuration.ButtonFont.BUTTON_FONT);
        this.setSize(95, 35);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationWindow.getInstance().showPage(new MainPage(), false);
    }

}
