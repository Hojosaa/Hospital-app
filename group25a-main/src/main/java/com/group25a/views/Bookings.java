package com.group25a.views;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.group25a.application.Application;
import com.group25a.components.BackButton;
import com.group25a.components.ConfirmButton;
import com.group25a.components.Label;
import com.group25a.components.RescheduleButton;
import com.group25a.components.ScrollPane;
import com.group25a.components.Table;
import com.group25a.configuration.Configuration;
import com.group25a.models.AppState;
import com.group25a.models.Booking;
import com.group25a.services.IBookingService;

// This class represents the GUI for viewing bookings
public class Bookings extends JPanel implements ActionListener {

    IBookingService bookingService = Application.resolveDependency(IBookingService.class);

    // Initializing Swing components
    private final JButton confirmButton = new ConfirmButton("Confirm");
    private final JButton backButton = new BackButton("Back");
    private final JComboBox<String> monthComboBox = new JComboBox<>();
    private final JComboBox<String> yearComboBox = new JComboBox<>();
    private final JLabel monthLabel = new Label("Month");
    private final JLabel yearLabel = new Label("Year");
    private final DefaultTableModel model = new DefaultTableModel();
    private final JTable table = new Table(model);
    private JTextArea bookingsTextArea;

    public JPanel createPanel(JTable table2, Object value, boolean isSelected, int row, int column) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        RescheduleButton button = new RescheduleButton("Reschedule");
        button.setBounds(0, 0, 100, 50);
        button.setCursor(java.awt.Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Arial", Font.PLAIN, 12));

        // get the booking id month year from the table
        Object bookingID = table2.getModel().getValueAt(row, 2);
        Object month = monthComboBox.getSelectedItem();
        Object year = yearComboBox.getSelectedItem();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(bookingID.toString());
                Reschedule reschedule = new Reschedule(Bookings.this, bookingID, month, year);
                reschedule.show();
            }
        });
        panel.add(button);
        return panel;
    }

    // Constructor
    Bookings() {

        // Characterizing GUI objects
        this.setLayout(null);
        bookingsTextArea = new JTextArea();
        bookingsTextArea.setEditable(false);
        bookingsTextArea.setLineWrap(true);

        // Table
        model.addColumn("Booking ID");
        model.addColumn("Booking Date");
        model.addColumn("Booking Time");
        model.addColumn("Doctor");
        model.addColumn("Reschedule");

        // Colours of the header of the table
        JTableHeader header = table.getTableHeader();
        header.setBackground(Configuration.TableHeader.HEADER_COLOR);
        header.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        // Setting bounds for labels and components
        monthLabel.setBounds(180, 35, 80, 25);
        monthComboBox.setBounds(240, 30, 100, 35);
        yearLabel.setBounds(360, 35, 80, 25);
        yearComboBox.setBounds(410, 30, 100, 35);

        // Setting foreground and background colours
        monthComboBox.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        yearComboBox.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);
        monthComboBox.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);
        yearComboBox.setBackground(Configuration.BackGroundLabelColor.BACKGROUNDLABEL_COLOR);

        // Adding the table to a JScrollPane
        JScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setLocation(80, 100);
        this.add(scrollPane);
        table.getColumn("Reschedule").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus,
                    int row, int column) {
                return createPanel(table, value, isSelected, row, column);
            }

        });

        table.getColumn("Reschedule").setCellEditor(new TableCellEditor() {
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
                return createPanel(table, value, isSelected, row, column);
            }
        });

        // Disallow column reordering
        table.getTableHeader().setReorderingAllowed(false);

        // Adding components to the main panel
        this.add(monthLabel);
        this.add(monthComboBox);
        this.add(yearLabel);
        this.add(yearComboBox);
        this.add(scrollPane);
        this.add(confirmButton);
        this.add(backButton);

        List<Booking> bookings = bookingService.getAllPastBookings(AppState.getLoggedInUser().getUserId());

        // add the years and months to the combo boxes
        for (Booking booking : bookings) {
            System.out.println(booking.getDate());
            String year = booking.getDate().split("-")[0];
            String month = booking.getDate().split("-")[1];
            if (yearComboBox.getItemCount() == 0) {
                yearComboBox.addItem(year);
                monthComboBox.addItem(month);
                continue;
            }

            if (!yearComboBox.getItemAt(yearComboBox.getItemCount() - 1).equals(year)) {
                yearComboBox.addItem(year);
            }
            if (!monthComboBox.getItemAt(monthComboBox.getItemCount() - 1).equals(month)) {
                monthComboBox.addItem(month);
            }
        }

        // Setting location of buttons
        confirmButton.setLocation(530, 30);
        backButton.setLocation(635, 30);

        confirmButton.addActionListener(this);
        backButton.addActionListener(this);

        // Setting colour of panel
        this.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

        // Adding the panel components to the frame
        this.setSize(Configuration.WINDOW_DIMENSION);
        this.setPreferredSize(this.getSize());
        this.setMaximumSize(this.getSize());
        this.setMinimumSize(this.getSize());
        this.setVisible(true);

        //Method to prevent duplicate years and months in the combo boxes
        for (Booking booking : bookings) {
            System.out.println(booking.getDate());
            String year = booking.getDate().split("-")[0];
            String month = booking.getDate().split("-")[1];

            if (!isInComboBox(yearComboBox, year)) {
                yearComboBox.addItem(year);
            }
            if (!isInComboBox(monthComboBox, month)) {
                monthComboBox.addItem(month);
            }
        }
    }

    private boolean isInComboBox(JComboBox<String> comboBox, String item) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (item.equals(comboBox.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }

    // Action performed when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        // When the back button is clicked, return to the main homepage
        if (e.getSource() == backButton) {
            new MainPage();
        }

        if (e.getSource() == confirmButton) {
            int selectedMonth = Integer.parseInt((String) monthComboBox.getSelectedItem());
            int selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());

            var bookings = bookingService.getAllCurrentBookings(AppState.getLoggedInUser().getUserId(), selectedMonth,
                    selectedYear);

            JOptionPane.showMessageDialog(null, "Current Bookings Loaded!");


            // clear the models
            model.setRowCount(0);

            // populate
            for (Booking booking : bookings) {
                model.addRow(new Object[] { booking.getBookingID(), booking.getDate(), booking.getTime(), booking.getDoctorID() });
            }

        }
    }
}
