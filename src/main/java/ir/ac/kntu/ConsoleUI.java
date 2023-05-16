package ir.ac.kntu;

import ir.ac.kntu.gamelogic.Board;
import ir.ac.kntu.gamelogic.Dice;

import java.util.Scanner;

public class ConsoleUI {
    private Board board;

    public void startGame() {
        board = new Board(10, 10, 20);
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            printBoard(board);
            input = scanner.nextLine();
            Dice.nextTurn(board);
        } while (input.equals(""));
    }

    public void printBoard(Board board) {
        String[][] grid = board.getGrid();
        for (int i = board.getRowCount() - 1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < board.getColumnCount(); j++) {
                System.out.print(grid[i][j] + "|");
            }
            System.out.println();
        }
    }
}
