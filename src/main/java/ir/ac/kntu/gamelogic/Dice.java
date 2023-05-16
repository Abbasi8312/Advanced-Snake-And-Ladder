package ir.ac.kntu.gamelogic;

public final class Dice extends GameObject {
    public Dice(int index) {
        super(index);
    }

    public static DiceOptions roll() {
        DiceOptions[] diceOptions = DiceOptions.values();
        return diceOptions[RandomHelper.nextInt(diceOptions.length)];
    }

    public static DiceOptions nextTurn(Board board) {
        DiceOptions diceOption = roll();
        board.movePlayer(diceOption);
        board.removeWildHeads();
        board.updateWildHeads();
        board.updateAllTails();
        return diceOption;
    }
}
