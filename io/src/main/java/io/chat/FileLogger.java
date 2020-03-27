package io.chat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Simple file logging manager.
 * Incoming messages are recorded to file.
 * The path to log file is set in advance.
 */

public class FileLogger {
    Path file;

    public FileLogger(String file) {
        this.file = Path.of(file);
    }

    public void write(String message) {
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        try {
            Files.writeString(file, message + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
