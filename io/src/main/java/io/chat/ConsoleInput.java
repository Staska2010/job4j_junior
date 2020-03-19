package io.chat;

import java.io.Console;

/**
 *  Get messages from standard input
 */

public class ConsoleInput implements Input{
    @Override
    public String get() {
        String input = "";
        Console console = System.console();
        if(console != null) {
            input = console.readLine();
        }
        return input;
    }
}
