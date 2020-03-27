package io;

import io.chat.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ChatTest {
    Path botMessagesFile;
    Path logFile;
    List<String> mockMessages;
    List<String> result;
    List<String> logLines;
    FileLogger fileLogger;
    Chat chat;

    @Before
    public void init() {
        createBotMessagesFile();
        mockMessages = new LinkedList<>();
        Collections.addAll(mockMessages, "Привет", "Пока", "Стоп", "Продолжить", "Завершить");
        chat = new Chat();
        result = new LinkedList<>();
        logFile = Path.of(System.getProperty("java.io.tmpdir") + File.separator + "log.txt");
        fileLogger = new FileLogger(logFile.toString());
    }

    @After
    public void onExit() {
        try {
            Files.deleteIfExists(botMessagesFile);
            Files.deleteIfExists(logFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void whenChatWithSpecialWordsThenBotAnswersCorrectly() {
        chat.start(result::add, fileLogger, new MockPlayer(mockMessages), new ChatBot(botMessagesFile.toString()));
        assertThat(result.get(0), is("Привет"));
        assertThat(result.get(1), is("I'm Bot"));
        assertThat(result.get(2), is("Пока"));
        assertThat(result.get(3), is("I'm Bot"));
        assertThat(result.get(4), is("Стоп"));
        assertThat(result.get(5), is(not("I'm Bot")));
        assertThat(result.get(6), is("Продолжить"));
        assertThat(result.get(7), is("I'm Bot"));
        assertThat(result.get(8), is("Завершить"));
    }

    @Test
    public void whenChatWriteLogsToFileItContainsRightLines() {
        chat.start(result::add, fileLogger, new MockPlayer(mockMessages), new ChatBot(botMessagesFile.toString()));
        readLogFile();
        assertThat(logLines.get(0), is("Привет"));
        assertThat(logLines.get(1), is("I'm Bot"));
        assertThat(logLines.get(2), is("Пока"));
        assertThat(logLines.get(3), is("I'm Bot"));
        assertThat(logLines.get(4), is("Стоп"));
        assertThat(logLines.get(5), is(not("I'm Bot")));
        assertThat(logLines.get(6), is("Продолжить"));
        assertThat(logLines.get(7), is("I'm Bot"));
        assertThat(logLines.get(8), is("Завершить"));
    }

    public void createBotMessagesFile() {
        try {
            botMessagesFile = Files.createTempFile(Path.of(System.getProperty("java.io.tmpdir")), "mes", ".txt");
            if (Files.exists(botMessagesFile)) {
                Stream.of("I'm Bot").forEach(x -> {
                    try {
                        Files.writeString(botMessagesFile, x);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readLogFile() {
        try {
            logLines = Files.readAllLines(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
