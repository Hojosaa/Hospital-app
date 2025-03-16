package com.group25a.components;

import java.awt.Font;
import javax.swing.JLabel;

import com.group25a.configuration.Configuration;

public class Label extends JLabel {
    public Label(String text) {
        super(text);
        this.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setSize(150,25);
    }
}
