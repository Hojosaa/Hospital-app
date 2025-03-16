package com.group25a.components;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.group25a.configuration.Configuration;

public class ScrollPane extends JScrollPane {
    public ScrollPane(JTable table) {
        super(table);
        this.setSize(750, 300);
        this.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
        this.setVisible(true);
        this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.trackColor = Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR;
                this.thumbColor = Configuration.ColorScheme.BACKGROUND_COLOR;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
                return button;
            }

            // Change size of the scroll bar
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(20, super.getPreferredSize(c).height);
            }
        });
    }

    public ScrollPane(JTextArea bookingsTextArea) {
        super(bookingsTextArea);
        this.setSize(750, 300);
        this.setVisible(true);
        this.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
        this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.trackColor = Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR;
                this.thumbColor = Configuration.ColorScheme.BACKGROUND_COLOR;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
                return button;
            }

            // Change size of the scroll bar
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(20, super.getPreferredSize(c).height);
            }
        });
    }

    public ScrollPane(JTextField textField) {
        super(textField);
        this.setSize(750, 300);
        this.setVisible(true);
        this.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
        this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.trackColor = Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR;
                this.thumbColor = Configuration.ColorScheme.BACKGROUND_COLOR;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
                return button;
            }

            // Change size of the scroll bar
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(20, super.getPreferredSize(c).height);
            }
        });
    }
}
