package com.group25a.views;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.group25a.application.Application;
import com.group25a.components.AvailabilityButton;
import com.group25a.components.BackButton;
import com.group25a.components.ScrollPane;
import com.group25a.components.Table;
import com.group25a.configuration.Configuration;
import com.group25a.models.Doctors;
import com.group25a.services.IDoctorService;

// This class represents the GUI for viewing doctors
public class ViewDoctors extends JPanel implements ActionListener {
    private final JButton backButton = new BackButton("Back");
    private final DefaultTableModel model = new DefaultTableModel();
    private final JTable table = new Table(model);

    // Constructor
    public ViewDoctors() {
        IDoctorService doctorService = Application.resolveDependency(IDoctorService.class);

        // Setting panel layout to null
        this.setLayout(null);

        // Table
        model.addColumn("Name");
        model.addColumn("ID");
        model.addColumn("Field");
        model.addColumn("Ethnicity/Background");
        model.addColumn("PhoneNumber");
        model.addColumn("Availability");

        // Populate the table with daa from the doctor list
        var doctorList = doctorService.getDoctors();
        for (Doctors doctor : doctorList) {
            model.addRow(new Object[] { doctor.getDoctorName(), doctor.getDoctorId(), doctor.getFieldSpeciality(),
                    doctor.getBackground(), doctor.getPhoneNumber() });
        }

        JPanel panel = new JPanel();
        panel.setLayout(null);
        AvailabilityButton button = new AvailabilityButton("Availability");
        button.setBounds(0, 0, 100, 50);
        button.setCursor(java.awt.Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Availability availability = new Availability(ViewDoctors.this);
                availability.show();
            }
        });
        panel.add(button);

        table.getColumn("Availability").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus,
                    int row, int column) {
                return panel;
            }
        });
        table.getColumn("Availability").setCellEditor(new TableCellEditor() {

            @Override
            public Object getCellEditorValue() {
                return null;
            }

            @Override
            public boolean isCellEditable(EventObject anEvent) {
                return true;
            }

            @Override
            public boolean shouldSelectCell(EventObject anEvent) {
                return true;
            }

            @Override
            public boolean stopCellEditing() {
                return true;
            }

            @Override
            public void cancelCellEditing() {
                return;
            }

            @Override
            public void addCellEditorListener(CellEditorListener l) {
                return;
            }

            @Override
            public void removeCellEditorListener(CellEditorListener l) {
                return;
            }

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                    int column) {
                return panel;
            }
        });

        // Colours of the header of the table
        JTableHeader header = table.getTableHeader();
        header.setBackground(Configuration.TableHeader.HEADER_COLOR);
        header.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);

         // Disallow column reordering
        table.getTableHeader().setReorderingAllowed(false);

        // Adding the table to a JScrollPane
        JScrollPane scrollPane = new ScrollPane(table);

        // Setting Bounds
        backButton.setLocation(80, 350);
        scrollPane.setLocation(80, 30);

        // Adding action listener to buttons
        backButton.addActionListener(this);

        // Setting panel colours
        this.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

        // adding components and buttons to panel
        this.add(backButton);
        this.add(scrollPane);

        // Configurations for the panel
        this.setSize(Configuration.WINDOW_DIMENSION);
        this.setPreferredSize(this.getSize());
        this.setMaximumSize(this.getSize());
        this.setMinimumSize(this.getSize());
        this.setVisible(true);
    }

    // Action performed when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        // When the back button is clicked, return to the main homepage
        if (e.getSource() == backButton) {
            new MainPage();
        }
    }
}
