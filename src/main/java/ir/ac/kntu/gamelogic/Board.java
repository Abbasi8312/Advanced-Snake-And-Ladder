package ir.ac.kntu.gamelogic;

public class Board {
    private GameObject[][] grid;

    private Snake[] snakes;

    public Board(int row, int column, int snakeCount) {
        grid = new GameObject[row][column];
        snakes = new Snake[snakeCount];
        int regularSnakeCount = RandomHelper.nextInt(snakeCount);
        int wildSnakeCount = RandomHelper.nextInt(snakeCount - regularSnakeCount);
        int friendlySnakeCount = snakeCount - regularSnakeCount - wildSnakeCount;
        for (int i = 0; i < regularSnakeCount; i++) {
            snakes[i] = new RegularSnake();
        }
        for (int i = 0; i < wildSnakeCount; i++) {
            snakes[i + regularSnakeCount] = new WildSnake();
        }
        for (int i = 0; i < friendlySnakeCount; i++) {
            snakes[i + regularSnakeCount + wildSnakeCount] = new FriendlySnake();
        }
        fillGrid();
        int x = 3;
    }

    public Board(int n, int snakeCount) {
        grid = new GameObject[n][n];
        fillGrid();
    }

    private void fillGrid() {
        grid[0][0] = new Player();
        int i = 0;
        while (i < snakes.length) {
            int index = RandomHelper.nextInt(grid.length * grid[0].length);
            if (grid[index / grid[0].length][index % grid[0].length] == null) {
                grid[index / grid[0].length][index % grid[0].length] = snakes[i];
                i++;
            }
        }
    }
}
