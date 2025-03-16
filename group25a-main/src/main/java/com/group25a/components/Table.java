package com.group25a.components;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.group25a.configuration.Configuration;

public class Table extends JTable {
    public Table(TableModel text) {
        super(text);
        this.setSize(750, 300);
        this.setRowHeight(30);
        this.setFont(new Font("Arial", Font.PLAIN, 14));
        this.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        this.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
    }
}
