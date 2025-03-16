package com.group25a.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.group25a.data_access.UserDataAccess;

public class DBManager implements IDBManager {
	private Connection connection;
	private Statement statement;
	private UserDataAccess userDataAccess;

	public DBManager() {
		// lets us do queries with db (execute, connection, etc)
		this.startConnection();
	}

	private void startConnection() {
		try {
			this.connection = DriverManager
					.getConnection("jdbc:h2:./danielwasherelol");
			// !TO RESET DB UNCOMMENT
//			this.resetDataBase();
//			this.createTable();
//			this.insertPresetValues();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.userDataAccess = new UserDataAccess(this);
	}

	public UserDataAccess getUserDataAccess() {
		return userDataAccess;
	}

	public Connection getConnection() {
		return this.connection;
	}

	// if needed add this to line 23 to reset the db and rerun main
	public void resetDataBase() {
		try {
			statement = connection.createStatement();

			statement.execute("DROP TABLE IF EXISTS Users");
			statement.execute("DROP TABLE IF EXISTS Doctors");
			statement.execute("DROP TABLE IF EXISTS Bookings");
			statement.execute("DROP TABLE IF EXISTS Prescription");
			System.out.println("Tables deleted successfully");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public String getDBSchema() throws SQLException {
		try {
			return this.connection.getSchema();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "";
	}

	public void createTable() {
		try {
			statement = connection.createStatement();

			statement.execute(
					"CREATE TABLE IF NOT EXISTS Doctors (DoctorID INT PRIMARY KEY AUTO_INCREMENT, DoctorName VARCHAR(255), PhoneNumber VARCHAR(255), Ethnicity VARCHAR(255), Specialty VARCHAR(255))");

			statement.execute(
					"CREATE TABLE IF NOT EXISTS Users (UserID INT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(255), LastName VARCHAR(255), DateOfBirth VARCHAR(255), Gender VARCHAR(255), PhoneNumber VARCHAR(255), Email VARCHAR(255), Address VARCHAR(255), Username VARCHAR(255), Password VARCHAR(255), DoctorID INT)");

			statement.execute(
					"CREATE TABLE IF NOT EXISTS Booking(BookingID INT PRIMARY KEY AUTO_INCREMENT, UserID INT, DoctorID INT, Date VARCHAR(255), BookingTime VARCHAR(255), BookingState BOOL)");

			statement.execute(
					"CREATE TABLE IF NOT EXISTS Prescription(BookingID INT PRIMARY KEY, VisitSummary VARCHAR(255), Prescription VARCHAR(255), PrescriptionLength DATE)");

			statement.execute(
					"CREATE TABLE IF NOT EXISTS UserLogger(LogID INT PRIMARY KEY AUTO_INCREMENT, UserID INT, Message VARCHAR(255))");

			statement.execute(
					"CREATE TABLE IF NOT EXISTS DoctorLogger(LogID INT PRIMARY KEY AUTO_INCREMENT, DoctorID INT, Message VARCHAR(255))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertPresetValues() {
		try {
			statement = connection.createStatement();

			// Insert test values into the Doctors table
			statement.execute(
					"INSERT INTO Doctors (DoctorName, PhoneNumber, Ethnicity, Specialty) VALUES ('Dr.BJ Penn', '07896549932', 'Caucasian', 'Cardiology')");
			statement.execute(
					"INSERT INTO Doctors (DoctorName, PhoneNumber, Ethnicity, Specialty) VALUES ('Dr.DJ Rizz', '07426690070', 'Spanish', 'Rizzology')");
			statement.execute(
					"INSERT INTO Doctors (DoctorName, PhoneNumber, Ethnicity, Specialty) VALUES ('Dr.R Ahmed', '07943249444', 'Bengali', 'Dentist')");
			statement.execute(
					"INSERT INTO Doctors (DoctorName, PhoneNumber, Ethnicity, Specialty) VALUES ('Dr.Ney Jhesy', '07357890214', 'African', 'Dermatology')");

			// if values dont already exist Insert test values into the Users table
			statement.execute(
					"INSERT INTO Users (FirstName, LastName, Gender, PhoneNumber, Email, Address, Username, Password, DoctorID) VALUES ('Test1', 'User1', 'Male', '1234567890', 'test1@user.com', 'Address 1', 'daniel', 'rizz',2)");
			statement.execute(
					"INSERT INTO Users (FirstName, LastName, Gender, PhoneNumber, Email, Address, Username, Password, DoctorID) VALUES ('Test2', 'User2', 'Female', '0987654321', 'test2@user.com', 'Address 2', 'testuser2', 'password2',1)");
			statement.execute(
					"INSERT INTO Users (FirstName, LastName, Gender, PhoneNumber, Email, Address, Username, Password, DoctorID) VALUES ('Test3', 'User3', 'Male', '1234567890', 'test3@user.com', 'Address 3', 'testuser3', 'password3',3)");
			statement.execute(
					"INSERT INTO Users (FirstName, LastName, Gender, PhoneNumber, Email, Address, Username, Password, DoctorID) VALUES ('Test4', 'User4', 'Female', '0987654321', 'test4@user.com', 'Address 4', 'testuser4', 'password4',4)");

			// TEST inserts for booking relation
			statement.execute(
					"INSERT INTO Booking (UserID, DoctorID, Date, BookingTime, BookingState) VALUES (1, 1, '2024-03-21', '10:00:00', FALSE)");
			statement.execute(
					"INSERT INTO Booking (UserID, DoctorID, Date, BookingTime, BookingState) VALUES (1, 2, '2024-03-22', '09:00:00', TRUE)");
			statement.execute(
					"INSERT INTO Booking (UserID, DoctorID, Date, BookingTime, BookingState) VALUES (3, 3, '2024-03-23', '11:00:00', TRUE)");
			statement.execute(
					"INSERT INTO Booking (UserID, DoctorID, Date, BookingTime, BookingState) VALUES (1, 4, '2024-03-26', '09:30:00', TRUE)");

			// Inserting test data into the Prescription table
			statement.execute(
					"INSERT INTO Prescription (BookingID, VisitSummary, Prescription, PrescriptionLength) VALUES (1, 'Annual health checkup - Follow-up', 'Vitamin D supplements', '2024-04-21')");
			statement.execute(
					"INSERT INTO Prescription (BookingID, VisitSummary, Prescription, PrescriptionLength) VALUES (2, 'Routine diabetes management', 'Metformin', '2024-05-22')");
			statement.execute(
					"INSERT INTO Prescription (BookingID, VisitSummary, Prescription, PrescriptionLength) VALUES (3, 'Seasonal allergies', 'Cetirizine 10mg', '2024-06-23')");
			statement.execute(
					"INSERT INTO Prescription (BookingID, VisitSummary, Prescription, PrescriptionLength) VALUES (4, 'Post-surgical antibiotic course', 'Amoxicillin 500mg', '2024-07-24')");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}