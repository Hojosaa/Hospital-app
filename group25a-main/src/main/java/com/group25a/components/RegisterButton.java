package com.group25a.components;

import javax.swing.JButton;
import com.group25a.configuration.Configuration;

public class RegisterButton extends JButton {

    public RegisterButton (String text) {
        super(text);
        this.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        this.setBackground(Configuration.GreenButton.GREENBUTTON_COLOR);
        this.setFont(Configuration.ButtonFont.BUTTON_FONT);
        this.setFocusable(false);
        this.setSize(110, 35);

    }

}
