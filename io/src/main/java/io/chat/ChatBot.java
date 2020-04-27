package io.chat;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Simple implementation of Player.
 * Bot, that uses file with prerecorded answers and messages for chat.
 * Lines of messages are selected randomly.
 */

public class ChatBot extends Player {
    private List<String> answers;
    private boolean stopTalking = false;
    private Path answersFile;

    public ChatBot() {

    }

    public ChatBot(String path) {
        answersFile = Path.of(path).toAbsolutePath();
    }

    private void loadAnswers() {
        if (Files.exists(answersFile.toAbsolutePath())) {
            try (BufferedReader bf = new BufferedReader(new FileReader(answersFile.toString()))) {
                answers = bf.lines().collect(Collectors.toList());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String chooseAnswer() {
        String curAnswer = "";
        if (answers == null) {
            loadAnswers();
        }
        if ((answers != null) && (answers.size() != 0) && stopTalking != true) {
            int numOfString = (int) (Math.random() * answers.size());
            curAnswer = answers.get(numOfString);
        }
        return curAnswer;
    }

    public void stop() {
        stopTalking = true;
    }

    public void go() {
        stopTalking = false;
    }

    @Override
    public String makeAnswer(Output out) {
        String answer = chooseAnswer();
        out.send(answer);
        return answer;
    }
}
