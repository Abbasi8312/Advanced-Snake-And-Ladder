package ir.ac.kntu.gamelogic.dice;

import ir.ac.kntu.gamelogic.GameObject;
import ir.ac.kntu.gamelogic.board.Board;
import ir.ac.kntu.gamelogic.utility.RandomHelper;

public final class Dice extends GameObject {
    private static DiceOptions lastRoll;

    public Dice(int index) {
        super(index);
    }

    public static DiceOptions roll() {
        DiceOptions[] diceOptions = DiceOptions.values();
        lastRoll = diceOptions[RandomHelper.nextInt(diceOptions.length)];
        return lastRoll;
    }

    public static void nextTurn(Board board) {
        DiceOptions diceOption = roll();
        if (board.nextTurn(diceOption)) {
            board.removeWildHeads();
            board.updateWildHeads();
            board.updateAllTails();
            board.getGameStatus().updateStatus();
        }
    }

    public static DiceOptions getLastRoll() {
        return lastRoll;
    }
}
