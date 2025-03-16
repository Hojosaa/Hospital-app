package com.group25a.services;

import java.sql.SQLException;
import java.util.List;

import com.group25a.models.Logger;

public interface ILoggerService {
    public List<Logger> getAllUserLogs(int userID) throws SQLException;
    public void addUserLog(int userID, String log);
}
