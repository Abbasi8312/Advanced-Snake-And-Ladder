package ir.ac.kntu.gamelogic;

public final class Dice extends GameObject {
    public static DiceOptions roll() {
        DiceOptions[] diceOptions = DiceOptions.values();
        return diceOptions[RandomHelper.nextInt(diceOptions.length)];
    }

    public static void nextTurn(Board board) {
        board.removeWildHeads();
        board.updateWildHeads();
        board.updateAllTails();
    }

    public enum DiceOptions {
        ONE_UP,
        TWO_UP,
        ONE_RIGHT,
        TWO_RIGHT,
        ONE_DOWN,
        TWO_DOWN,
        ONE_LEFT,
        TWO_LEFT,
        EXTRA_LIFE
    }
}
