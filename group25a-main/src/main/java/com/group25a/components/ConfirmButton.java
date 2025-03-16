package com.group25a.components;

import javax.swing.JButton;
import com.group25a.configuration.Configuration;

public class ConfirmButton extends JButton {

    public ConfirmButton(String text) {
        super(text);
        this.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        this.setBackground(Configuration.ConfirmButtonColor.CONFIRMBUTTON_COLOR);
        this.setFont(Configuration.ButtonFont.BUTTON_FONT);
        this.setFocusable(false);
        this.setSize(95, 35);

    }
}
