package com.group25a.views;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.group25a.components.TimeButton;
import com.group25a.configuration.Configuration;

public class Availability implements ActionListener {
    private final Component parent;
    private final JButton eightAm = new TimeButton("8:00 AM");
    private final JButton eightThirtyAm = new TimeButton("8:30 AM");
    private final JButton nineAm = new TimeButton("9:00 AM");
    private final JButton nineThirtyAm = new TimeButton("9:30 AM");
    private final JButton tenAm = new TimeButton("10:00 AM");
    private final JButton tenThirtyAm = new TimeButton("10:30 AM");
    private final JButton elevenAm = new TimeButton("11:00 AM");
    private final JButton elevenThirtyAm = new TimeButton("11:30 AM");
    private final JButton twelvePm = new TimeButton("12:00 PM");
    private final JButton twelveThirtyPm = new TimeButton("12:30 PM");
    private final JButton onePm = new TimeButton("1:00 PM");
    private final JButton oneThirtyPm = new TimeButton("1:30 PM");
    private final JButton twoPm = new TimeButton("2:00 PM");
    private final JButton twoThirtyPm = new TimeButton("2:30 PM");
    private final JButton threePm = new TimeButton("3:00 PM");
    private final JButton threeThirtyPm = new TimeButton("3:30 PM");
    private final JButton fourPm = new TimeButton("4:00 PM");
    private final JButton fourThirtyPm = new TimeButton("4:30 PM");
    private final JButton fivePm = new TimeButton("5:00 PM");
    JDialog dialog;

    public Availability(Component parent) {
        this.parent = parent;
    }

    public void show() {
        JPanel optionPanePanel = new JPanel(new GridLayout(4, 3));
        optionPanePanel.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);
        optionPanePanel.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);

        // Add the buttons to the grid layout
        optionPanePanel.add(eightAm);
        optionPanePanel.add(eightThirtyAm);
        optionPanePanel.add(nineAm);
        optionPanePanel.add(nineThirtyAm);
        optionPanePanel.add(tenAm);
        optionPanePanel.add(tenThirtyAm);
        optionPanePanel.add(elevenAm);
        optionPanePanel.add(elevenThirtyAm);
        optionPanePanel.add(twelvePm);
        optionPanePanel.add(twelveThirtyPm);
        optionPanePanel.add(onePm);
        optionPanePanel.add(oneThirtyPm);
        optionPanePanel.add(twoPm);
        optionPanePanel.add(twoThirtyPm);
        optionPanePanel.add(threePm);
        optionPanePanel.add(threeThirtyPm);
        optionPanePanel.add(fourPm);
        optionPanePanel.add(fourThirtyPm);
        optionPanePanel.add(fivePm);

        // Setting panel colours
        optionPanePanel.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

        this.dialog = new JOptionPane().createDialog(this.parent, "Doctor Availability");
        dialog.setContentPane(optionPanePanel);
        dialog.setSize(Configuration.DIALOG_DIMENSION);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
