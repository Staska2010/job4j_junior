package io.chat;

import java.util.List;

/**
 *  A stub for Human player.
 *  Used basically for test purposes.
 *  List of prerecorded messages is transferred to a class entity.
 */

public class MockPlayer extends ChatBot {
    List<String> messages;
    int numOfMessage = 0;

    public MockPlayer(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String makeAnswer(Output out) {
        String message = messages.get(numOfMessage++);
        out.send(message);
        return message;
    }
}
