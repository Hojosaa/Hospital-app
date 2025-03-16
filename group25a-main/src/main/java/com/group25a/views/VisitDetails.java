package com.group25a.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.group25a.application.Application;
import com.group25a.components.BackButton;
import com.group25a.components.ScrollPane;
import com.group25a.components.Table;
import com.group25a.configuration.Configuration;
import com.group25a.models.AppState;
import com.group25a.models.Booking;
import com.group25a.models.Prescription;
import com.group25a.services.IBookingService;
import com.group25a.services.IPrescriptionService;

// This class represents the GUI for viewing doctors
public class VisitDetails extends JPanel implements ActionListener {
    private final JButton backButton = new BackButton("Back");
    private final DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // This causes all cells to be not editable
            return false;
        }
    };
    private final JTable table = new Table(model);

    // Constructor
    public VisitDetails() {
        IBookingService bookingService = Application.resolveDependency(IBookingService.class);
        IPrescriptionService prescriptionService = Application.resolveDependency(IPrescriptionService.class);

        // Characterizing GUI objects
        this.setLayout(null);

        // Table
        model.addColumn("Date");
        model.addColumn("Time");
        model.addColumn("DoctorName");
        model.addColumn("BookingID");
        model.addColumn("Summary");
        model.addColumn("Prescription");
        model.addColumn("PrescriptionLength");

        // Populate the table with data from the doctor list
        var bookingList = bookingService.getAllPastBookings(AppState.getLoggedInUser().getUserId());
        for (Booking booking : bookingList) {
            System.out.println(booking.getBookingID());
            Prescription bookingPrescription = prescriptionService.getPrescriptionByID(booking.getBookingID());
            if (bookingPrescription != null) {
                model.addRow(new Object[] { booking.getDate(), booking.getTime(), booking.getDoctorID(),
                        booking.getBookingID(), bookingPrescription.getBookingSummary(),
                        bookingPrescription.getPrescription(), bookingPrescription.getPrescriptionLength() });
            }
        }

        // Colours of the header of the table
        JTableHeader header = table.getTableHeader();
        header.setBackground(Configuration.TableHeader.HEADER_COLOR);
        header.setForeground(Configuration.ForeGroundColor.FOREGROUND_COLOR);

        // Buttons
        backButton.setLocation(80, 350);
        backButton.addActionListener(this);
        this.add(backButton);

        // Adding the table to a JScrollPane
        JScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setLocation(80, 30);
        this.add(scrollPane);

        // Disallow column reordering
        table.getTableHeader().setReorderingAllowed(false);

        // Setting colur of the panel
        this.setBackground(Configuration.ColorScheme.BACKGROUND_COLOR);

        // Adding the panel components to the frame
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
