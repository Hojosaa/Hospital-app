package com.group25a.tests;

import com.group25a.data_access.ILoggerDataAccess;
import com.group25a.models.Logger;
import com.group25a.services.LoggerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoggerServiceTest {

    @Mock
    private ILoggerDataAccess mockLoggerDataAccess;

    private LoggerService loggerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loggerService = new LoggerService(mockLoggerDataAccess);
    }

    @Test
    public void getAllUserLogs_ValidUserID_ReturnsLogs() throws SQLException {
        int userID = 1;
        List<Logger> expectedLogs = Arrays.asList(new Logger(userID, "Test log message"));
        when(mockLoggerDataAccess.getAllUserLogs(userID)).thenReturn(expectedLogs);

        List<Logger> actualLogs = loggerService.getAllUserLogs(userID);

        assertEquals(expectedLogs, actualLogs, "The returned logs should match the expected logs.");
    }

    @Test
    public void getAllUserLogs_InvalidUserID_ThrowsIllegalArgumentException() {
        int invalidUserID = -1;
        assertThrows(IllegalArgumentException.class, () -> loggerService.getAllUserLogs(invalidUserID), "Should throw IllegalArgumentException for invalid user ID.");
    }

    @Test
    public void addUserLog_ValidLog_AddsLog() {
        int userID = 1;
        String log = "This is a valid log message.";

        assertDoesNotThrow(() -> loggerService.addUserLog(userID, log));

        verify(mockLoggerDataAccess).addUserLog(userID, log);
    }

    @Test
    public void addUserLog_EmptyLogMessage_ThrowsIllegalArgumentException() {
        int userID = 1;
        String emptyLog = "";

        assertThrows(IllegalArgumentException.class, () -> loggerService.addUserLog(userID, emptyLog), "Should throw IllegalArgumentException for empty log message.");
    }

    @Test
    public void addUserLog_LogMessageExceedsMaxLength_ThrowsIllegalArgumentException() {
        int userID = 1;
        // Use the literal value for the max log message length
        String longLog = "a".repeat(1001);

        assertThrows(IllegalArgumentException.class, () -> loggerService.addUserLog(userID, longLog), "Should throw IllegalArgumentException for log message exceeding max length.");
    }

    @Test
    public void addUserLog_InvalidUserID_ThrowsIllegalArgumentException() {
        int invalidUserID = -1;
        String log = "This is a valid log message.";

        assertThrows(IllegalArgumentException.class, () -> loggerService.addUserLog(invalidUserID, log), "Should throw IllegalArgumentException for invalid user ID.");
    }
}
