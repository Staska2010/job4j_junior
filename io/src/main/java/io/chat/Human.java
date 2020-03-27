package io.chat;

import java.util.Scanner;

/**
 * Player described as Human.
 * Creature that can enter messages from standard input.
 */

public class Human extends Player {

    @Override
    public String makeAnswer(Output out) {
        String answer = "Error in Human";
        Scanner in = new Scanner(System.in);
        answer = in.nextLine();
        out.send(answer);
        return answer;
    }
}
