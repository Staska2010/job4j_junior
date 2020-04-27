package io.chat;

/**
 * Simple chat with further possibilities to enhance.
 * It was supposed to be used by an unlimited number of Players.
 * Players: Bots and Humans.
 * Use "stop" message to silence all bots.
 * Use "continue" to let bots talk.
 * Use "Terminate" to end chat.
 *
 * @author staska
 */


public class Chat {
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String END = "завершить";

    public void start(Output output, FileLogger logger, Player... players) {
        String out = "";
        while (!END.equalsIgnoreCase(out)) {
            for (Player next : players) {
                if (STOP.equalsIgnoreCase(out) && (next instanceof ChatBot)) {
                    ((ChatBot) next).stop();
                }
                if (CONTINUE.equalsIgnoreCase(out) && (next instanceof ChatBot)) {
                    ((ChatBot) next).go();
                }
                out = next.makeAnswer(output);
                logger.write(out);
                if (END.equalsIgnoreCase(out)) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Chat chat = new Chat();
        chat.start(System.out::println, new FileLogger("log.txt"), new Human(), new ChatBot("../botanswers/answer.txt"));
    }
}
