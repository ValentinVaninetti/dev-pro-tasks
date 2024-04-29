package org.vv.firstTask.entities.logger;

import org.vv.firstTask.entities.enums.LogLevel;
import org.vv.firstTask.entities.interfaces.ILogger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;

public class Logger implements ILogger {
    public static final String LOGGER_FILENAME = "src/resources/logs/log.txt";
    private static final EnumSet<LogLevel> logLevels = EnumSet.allOf(LogLevel.class);

    @Override
    public void logMessage(String message, LogLevel level) {
        if (level == null || !isValidLogLevel(level)) {
            throw new IllegalArgumentException("Invalid log level");
        }
        String formattedMessage = String.format("[%s] [%s] %s%n", getCurrentTimestamp(), level, message);
        writeToFile(formattedMessage);
    }

    private boolean isValidLogLevel(LogLevel level) {
        return logLevels.contains(level);
    }

    @Override
    public void writeToFile(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOGGER_FILENAME, true))) {
            writer.println(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
