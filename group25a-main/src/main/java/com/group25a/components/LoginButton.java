package com.group25a.components;

import javax.swing.JButton;
import com.group25a.configuration.Configuration;

public class LoginButton extends JButton {

    public LoginButton (String text) {
        super(text);
        this.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        this.setBackground(Configuration.BlueButton.BLUEBUTTON_COLOR);
        this.setFont(Configuration.ButtonFont.BUTTON_FONT);
        this.setFocusable(false);
        this.setSize(110, 35);

    }

}
