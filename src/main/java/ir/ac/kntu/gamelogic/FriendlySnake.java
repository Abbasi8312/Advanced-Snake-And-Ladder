package ir.ac.kntu.gamelogic;

public class FriendlySnake extends Snake {
    public FriendlySnake(int index) {
        super(index);
    }

    @Override public boolean checkTailAvailability(int row, GameObject[][] grid) {
        for (int i = row + 1; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null || grid[i][j].getClass() == Player.class) {
                    return true;
                }
            }
        }
        return false;
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
