package ir.ac.kntu.gamelogic;

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
        board.movePlayer(diceOption);
        board.removeWildHeads();
        board.updateWildHeads();
        board.updateAllTails();
        board.getGameStatus().updateStatus();
    }

    public static DiceOptions getLastRoll() {
        return lastRoll;
    }
}
