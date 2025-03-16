package com.group25a.components;

import javax.swing.JButton;

import com.group25a.configuration.Configuration;

public class AvailabilityButton extends JButton {
    public AvailabilityButton(String text) {
        super(text);
        this.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        this.setBackground(Configuration.BlueButton.BLUEBUTTON_COLOR);
        this.setFont(Configuration.AvailabilityFont.AVAILABILITY_FONT);
        this.setFocusable(false);
        this.setSize(110, 35);
    }
}
