package com.group25a.services;

import com.group25a.application.Application;
import com.group25a.data_access.ILoggerDataAccess;
import com.group25a.models.Logger;

import java.sql.SQLException;
import java.util.List;

public class LoggerService implements ILoggerService {

    private final ILoggerDataAccess authenticationDataAccess;
    private static final int MAX_LOG_MESSAGE_LENGTH = 1000; // Example maximum log message length

    public LoggerService(ILoggerDataAccess authenticationDataAccess) {
        this.authenticationDataAccess = authenticationDataAccess;
    }

    @Override
    public List<Logger> getAllUserLogs(int userID) throws SQLException {
        if (userID <= 0) {
            throw new IllegalArgumentException("User ID must be positive.");
        }
        return authenticationDataAccess.getAllUserLogs(userID);
    }

    public void addUserLog(int userID, String log) {
        if (userID <= 0) {
            throw new IllegalArgumentException("User ID must be positive.");
        }
        if (log == null || log.trim().isEmpty()) {
            throw new IllegalArgumentException("Log message cannot be empty.");
        }
        if (log.length() > MAX_LOG_MESSAGE_LENGTH) {
            throw new IllegalArgumentException("Log message exceeds maximum allowed length of " + MAX_LOG_MESSAGE_LENGTH + " characters.");
        }
        authenticationDataAccess.addUserLog(userID, log);
    }

    public static void addGeneralLog(int userID, String log) {
        if (userID <= 0) {
            throw new IllegalArgumentException("User ID must be positive.");
        }
        if (log == null || log.trim().isEmpty()) {
            throw new IllegalArgumentException("Log message cannot be empty.");
        }
        if (log.length() > MAX_LOG_MESSAGE_LENGTH) {
            throw new IllegalArgumentException("Log message exceeds maximum allowed length of " + MAX_LOG_MESSAGE_LENGTH + " characters.");
        }
        
        ILoggerService service = Application.resolveDependency(ILoggerService.class);
        service.addUserLog(userID, log);
    }
}
