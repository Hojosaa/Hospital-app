package com.group25a.data_access;

import com.group25a.database.IDBManager;
import com.group25a.models.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoggerDataAccess implements ILoggerDataAccess {

    private final IDBManager manager;

    public LoggerDataAccess(IDBManager manager){
        this.manager = manager;
    }


    @Override
    public List<Logger> getAllUserLogs(int userID) throws SQLException {
        List<Logger> list = null;
        String query = "Select * FROM  UserLogger WHERE UserID = ?";
        try {
            var connection = manager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet result = statement.executeQuery();

            list = new ArrayList<Logger>();

            while (result.next()) {
                list.add(new Logger(
                        result.getInt("UserID"),
                        result.getString("message")
                ));
            }
            return list;

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    @Override
    public void addUserLog(int userID, String log) {
        try {
            Connection connection = manager.getConnection();
            String query = "INSERT INTO UserLogger (UserID, Message) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setString(2, log);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
        @Override
    public void addDoctorLog() {

    }
}
