package ir.ac.kntu.ui;

import ir.ac.kntu.gamelogic.board.Board;
import ir.ac.kntu.gamelogic.board.GameStatus;
import ir.ac.kntu.gamelogic.dice.Dice;

import java.util.Scanner;

public class ConsoleUI {
    private Board board;

    public void printBoard() {
        StringBuilder[][] grid = board.getGrid();
        System.out.println("-".repeat((grid[0][0].length() + 2) * grid[0].length + 1));
        for (int i = board.getRowCount() - 1; i >= 0; i--) {
            System.out.print("|");
            if (i == 0) {
                System.out.print("*");
            }
            for (int j = 0; j < board.getColumnCount(); j++) {
                if (i == board.getRowCount() - 1 && j == board.getColumnCount() - 1) {
                    System.out.print("$");
                }
                System.out.print(grid[i][j]);
                if ((i != board.getRowCount() - 1 || j != board.getColumnCount() - 1) && (i != 0 || j != 0)) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
            System.out.println("-".repeat((grid[0][0].length() + 2) * grid[0].length + 1));
        }
    }

    public void winGame() {
        System.out.println("\u001B[32m" + "You won" + "\u001B[0m");
        System.out.println();
        printBoard();
    }

    public void loseGame() {
        System.out.println("\u001B[31m" + "You lost" + "\u001B[0m");
        System.out.println();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        String row;
        String column;
        String snakeCount;
        do {
            System.out.println("\u001B[33m" + "Enter row count or 0 to randomize it" + "\u001B[0m");
            row = scanner.nextLine();
        } while (!row.matches("^[0-9]+$"));
        do {
            System.out.println("\u001B[33m" + "Enter column count or 0 to randomize it" + "\u001B[0m");
            column = scanner.nextLine();
        } while (!column.matches("^[0-9]+$"));
        do {
            System.out.println("\u001B[33m" +
                    "Enter snake count or 0 to randomize it (Maximum count is: row count * column count / 2 - 1)" +
                    "\u001B[0m");
            snakeCount = scanner.nextLine();
        } while (!snakeCount.matches("^[0-9]+$"));
        board = new Board(Integer.parseInt(row), Integer.parseInt(column), Integer.parseInt(snakeCount));
        String input;
        System.out.println("\u001B[31m" + "Enter q to exit" + "\u001B[0m");
        do {
            System.out.println("\u001B[35m" + "Health: " + board.getPlayer().getLife() + "\u001B[0m");
            printBoard();
            input = scanner.nextLine();
            Dice.nextTurn(board);
            if (input.equals("q") || input.equals("Q")) {
                System.out.println("Exiting...");
                break;
            }
            System.out.println("\u001B[34m" + "Dice: " + Dice.getLastRoll() + "\u001B[0m");
            System.out.println();
            //System.out.println(board.getPlayer().getLocation());
            if (board.getGameStatus().getStatus() == GameStatus.Status.LOSE) {
                loseGame();
                break;
            } else if (board.getGameStatus().getStatus() == GameStatus.Status.WIN) {
                winGame();
                break;
            }
        } while (true);
    }
}
