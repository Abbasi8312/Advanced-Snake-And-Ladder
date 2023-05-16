package ir.ac.kntu.gamelogic;

public class FriendlySnake extends Snake {
    public FriendlySnake(int index) {
        super(index);
    }

    @Override public void updateTail(GameObject[][] grid) {
        int row;
        int column;
        do {
            row = RandomHelper.nextInt(grid.length - head.x - 1);
            column = RandomHelper.nextInt(grid[0].length);
            row++;
        } while (grid[row + head.x][column] != null && grid[row + head.x][column].getClass() != Player.class);
        tail.x = row + head.x;
        tail.y = column;
    }
}
