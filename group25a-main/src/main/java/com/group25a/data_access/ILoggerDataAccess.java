package com.group25a.data_access;

import com.group25a.models.Logger;

import java.sql.SQLException;
import java.util.List;

public interface ILoggerDataAccess {
    public List<Logger> getAllUserLogs(int userID) throws SQLException;
    public void addDoctorLog();

    void addUserLog(int userID, String log);
}
