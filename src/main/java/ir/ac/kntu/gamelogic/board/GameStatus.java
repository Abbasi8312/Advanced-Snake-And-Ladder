package ir.ac.kntu.gamelogic.board;

import java.awt.*;

public class GameStatus {
    private final Board board;

    private Status status;

    GameStatus(Board board) {
        this.board = board;
    }

    public void updateStatus() {
        if (board.getPlayer().getLife() < 0) {
            status = Status.LOSE;
        } else if (board.getPlayer().getLocation()
                .equals(new Point(board.getRowCount() - 1, board.getColumnCount() - 1))) {
            status = Status.WIN;
        }
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        WIN,
        LOSE
    }
}
