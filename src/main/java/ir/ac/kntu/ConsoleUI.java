package ir.ac.kntu;

import ir.ac.kntu.gamelogic.Board;
import ir.ac.kntu.gamelogic.Dice;
import ir.ac.kntu.gamelogic.GameStatus;

import java.util.Scanner;

public class ConsoleUI {
    private Board board;

    public void printBoard() {
        String[][] grid = board.getGrid();
        for (int i = board.getRowCount() - 1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < board.getColumnCount(); j++) {
                System.out.print(grid[i][j] + "|");
            }
            System.out.println();
        }
    }

    public void winGame() {
        System.out.println("You won");
        System.out.println();
        printBoard();
    }

    public void loseGame() {
        System.out.println("You lost");
        System.out.println();
        printBoard();
    }

    public void startGame() {
        board = new Board(3, 3, 3);
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Life: " + board.getPlayer().getLife());
            printBoard();
            input = scanner.nextLine();
            Dice.nextTurn(board);
            System.out.println("Dice: " + Dice.getLastRoll());
            System.out.println();
            System.out.println(board.getPlayer().getLocation());
            if (board.getGameStatus().getStatus() == GameStatus.Status.LOSE) {
                loseGame();
                break;
            } else if (board.getGameStatus().getStatus() == GameStatus.Status.WIN) {
                winGame();
                break;
            }
        } while (input.equals(""));
    }
}
