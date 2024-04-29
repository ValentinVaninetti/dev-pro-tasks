package org.vv.firstTask.entities.interfaces;

import org.vv.firstTask.entities.enums.LogLevel;

public interface ILogger {
    void logMessage(String message, LogLevel level);
    void writeToFile(String message);
    String getCurrentTimestamp();
}
