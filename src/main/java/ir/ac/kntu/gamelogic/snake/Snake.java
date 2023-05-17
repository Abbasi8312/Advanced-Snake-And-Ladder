package ir.ac.kntu.gamelogic.snake;

import ir.ac.kntu.gamelogic.GameObject;
import ir.ac.kntu.gamelogic.player.Player;
import ir.ac.kntu.gamelogic.utility.RandomHelper;

import java.awt.*;

public class Snake extends GameObject {
    protected Point tail;

    protected Point head;

    public Snake(int index) {
        super(index);
        tail = new Point();
        head = new Point();
    }

    public Point getTail() {
        return tail;
    }

    public Point getHead() {
        return head;
    }

    public void updateHead(GameObject[][] grid) {
        int row;
        int column;
        do {
            row = RandomHelper.nextInt(grid.length - 1);
            column = RandomHelper.nextInt(grid[0].length);
            if (getClass() != FriendlySnake.class) {
                row++;
            }
        } while (grid[row][column] != null || row == grid.length - 1 && column == grid[0].length - 1 ||
                !checkTailAvailability(row, grid));
        grid[row][column] = this;
        head.x = row;
        head.y = column;
    }

    public boolean checkTailAvailability(int row, GameObject[][] grid) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null || grid[i][j].getClass() == Player.class) {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateTail(GameObject[][] grid) {
        int row;
        int column;
        do {
            row = RandomHelper.nextInt(head.x);
            column = RandomHelper.nextInt(grid[0].length);
        } while (grid[row][column] != null && grid[row][column].getClass() != Player.class);
        tail.x = row;
        tail.y = column;
    }

    public void bite(Player player, GameObject[][] grid) {
        player.moveTo(tail);
        grid[tail.x][tail.y] = player;
    }

    @Override public String toString() {
        return this.getClass().toString() + "///" + head.x + ":" + head.y + " - " + tail.x + ":" + tail.y;
    }
}