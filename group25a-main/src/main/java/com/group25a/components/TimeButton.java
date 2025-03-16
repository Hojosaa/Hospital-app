package com.group25a.components;

import javax.swing.JButton;

import com.group25a.configuration.Configuration;

public class TimeButton extends JButton {
    public TimeButton(String text) {
        super(text);
        this.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        this.setBackground(Configuration.GreenButton.GREENBUTTON_COLOR);
        this.setFont(Configuration.AvailabilityFont.AVAILABILITY_FONT);
        this.setFocusable(false);
    }
}
