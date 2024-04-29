package org.vv;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.vv.firstTask.entities.enums.LogLevel;
import org.vv.firstTask.entities.logger.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

class LoggerTest {

    @ParameterizedTest
    @EnumSource(value = LogLevel.class, names = {"INFO", "ERROR", "WARNING", "UNKNOWN"})

    void testLogger(LogLevel logLevel) {
        Logger logger = new Logger();
        String message = "Test message";
            logger.logMessage(message, logLevel);

            Assertions.assertTrue(isMessageLogged(message, logLevel));

    }
    @Test
    void testNullValue(){
        Logger logger = new Logger();
        String message = "Null value";

        assertThrows(IllegalArgumentException.class, () -> logger.logMessage(message, null));
    }
    @Test
    void testGetCurrentTimestamp() {
        Logger logger = new Logger();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String expectedTimestamp = now.format(formatter);
        assertEquals(expectedTimestamp, logger.getCurrentTimestamp(), "Timestamp format should match");
    }
    private boolean isMessageLogged(String message, LogLevel level) {
        try (Stream<String> lines = Files.lines(Paths.get(Logger.LOGGER_FILENAME))) {
            return lines.anyMatch(line -> containsLogMessage(line, message, level));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean containsLogMessage(String line, String message, LogLevel level) {
        return line.contains(message) && line.contains("[" + level + "]");
    }
}

