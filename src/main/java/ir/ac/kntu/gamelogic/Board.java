package ir.ac.kntu.gamelogic;

public class Board {
    private final GameObject[][] grid;

    private final RegularSnake[] regularSnakes;

    private final FriendlySnake[] friendlySnakes;

    private final WildSnake[] wildSnakes;


    public Board(int row, int column, int snakeCount) {
        grid = new GameObject[row][column];
        regularSnakes = new RegularSnake[RandomHelper.nextInt(snakeCount)];
        wildSnakes = new WildSnake[RandomHelper.nextInt(snakeCount - regularSnakes.length)];
        friendlySnakes = new FriendlySnake[snakeCount - regularSnakes.length - wildSnakes.length];
        for (int i = 0; i < regularSnakes.length; i++) {
            regularSnakes[i] = new RegularSnake();
        }
        for (int i = 0; i < wildSnakes.length; i++) {
            wildSnakes[i] = new WildSnake();
        }
        for (int i = 0; i < friendlySnakes.length; i++) {
            friendlySnakes[i] = new FriendlySnake();
        }
        fillGrid();
    }

    public Board(int n, int snakeCount) {
        this(n, n, snakeCount);
    }

    public void fillGrid() {
        grid[0][0] = new Player();
        updateAllHeads();
        updateAllTails();
    }

    public void updateRegularHeads() {
        for (Snake snake : regularSnakes) {
            snake.updateHead(grid);
        }
    }

    public void updateWildHeads() {
        for (Snake snake : wildSnakes) {
            snake.updateHead(grid);
        }
    }

    public void updateFriendlyHeads() {
        for (Snake snake : friendlySnakes) {
            snake.updateHead(grid);
        }
    }

    public void updateRegularTails() {
        for (Snake snake : regularSnakes) {
            snake.updateTail(grid);
        }
    }

    public void updateWildTails() {
        for (Snake snake : wildSnakes) {
            snake.updateTail(grid);
        }
    }

    public void updateFriendlyTails() {
        for (Snake snake : friendlySnakes) {
            snake.updateTail(grid);
        }
    }

    public void updateAllHeads() {
        updateRegularHeads();
        updateWildHeads();
        updateFriendlyHeads();
    }

    public void updateAllTails() {
        updateRegularTails();
        updateWildTails();
        updateFriendlyTails();
    }

    public void removeWildHeads() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getClass() == WildSnake.class) {
                    grid[i][j] = null;
                }
            }
        }
    }
}
