package com.group25a.components;

import java.awt.Font;
import javax.swing.JTextField;

import com.group25a.configuration.Configuration;

public class Textfield extends JTextField {
    public Textfield() {
        super();
        this.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        this.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
        this.setFont(new Font("Arial", Font.PLAIN, 16));
        this.setSize(400,25);
        this.setBorder(null);
    }
}
