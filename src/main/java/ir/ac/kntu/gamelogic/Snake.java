package ir.ac.kntu.gamelogic;

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

    public void setTail(Point tail) {
        this.tail = tail;
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
        } while (grid[row][column] != null);
        grid[row][column] = this;
        head.x = row;
        head.y = column;
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