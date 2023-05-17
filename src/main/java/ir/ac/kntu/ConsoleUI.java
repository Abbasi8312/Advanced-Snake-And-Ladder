package ir.ac.kntu;

import ir.ac.kntu.gamelogic.Board;
import ir.ac.kntu.gamelogic.Dice;
import ir.ac.kntu.gamelogic.GameStatus;

import java.util.Scanner;

public class ConsoleUI {
    private Board board;

    public void printBoard() {
        StringBuilder[][] grid = board.getGrid();
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
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        String row;
        String column;
        String snakeCount;
        do {
            System.out.println("Enter row count or 0 to randomize it");
            row = scanner.nextLine();
        } while (!row.matches("^[0-9]+$"));
        do {
            System.out.println("Enter column count or 0 to randomize it");
            column = scanner.nextLine();
        } while (!column.matches("^[0-9]+$"));
        do {
            System.out.println(
                    "Enter snake count or 0 to randomize it (Maximum count is: row count * column count / 2 - 1)");
            snakeCount = scanner.nextLine();
        } while (!snakeCount.matches("^[0-9]+$"));
        board = new Board(Integer.parseInt(row), Integer.parseInt(column), Integer.parseInt(snakeCount));
        String input;
        System.out.println("Enter q to exit");
        do {
            System.out.println("Life: " + board.getPlayer().getLife());
            printBoard();
            input = scanner.nextLine();
            Dice.nextTurn(board);
            if (input.equals("q") || input.equals("Q")) {
                System.out.println("Exiting...");
                break;
            }
            System.out.println("Dice: " + Dice.getLastRoll());
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
