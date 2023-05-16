package ir.ac.kntu.gamelogic;

public class Board {
    private final GameObject[][] grid;

    private final RegularSnake[] regularSnakes;

    private final FriendlySnake[] friendlySnakes;

    private final WildSnake[] wildSnakes;

    private final int rowCount;

    private final int columnCount;

    public Board(int row, int column, int snakeCount) {
        rowCount = row;
        columnCount = column;
        grid = new GameObject[row][column];
        regularSnakes = new RegularSnake[RandomHelper.nextInt(snakeCount)];
        wildSnakes = new WildSnake[RandomHelper.nextInt(snakeCount - regularSnakes.length)];
        friendlySnakes = new FriendlySnake[snakeCount - regularSnakes.length - wildSnakes.length];
        for (int i = 0; i < regularSnakes.length; i++) {
            regularSnakes[i] = new RegularSnake(i);
        }
        for (int i = 0; i < wildSnakes.length; i++) {
            wildSnakes[i] = new WildSnake(i);
        }
        for (int i = 0; i < friendlySnakes.length; i++) {
            friendlySnakes[i] = new FriendlySnake(i);
        }
        fillGrid();
    }


    public Board(int n, int snakeCount) {
        this(n, n, snakeCount);
    }

    public String[][] getGrid() {
        String[][] board = new String[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (grid[i][j] == null) {
                    board[i][j] = "  ";
                } else if (grid[i][j].getClass() == Player.class) {
                    board[i][j] = "P" + grid[i][j].getIndex();
                } else if (grid[i][j].getClass() == RegularSnake.class) {
                    board[i][j] = "s" + grid[i][j].getIndex();
                } else if (grid[i][j].getClass() == WildSnake.class) {
                    board[i][j] = "S" + grid[i][j].getIndex();
                } else if (grid[i][j].getClass() == FriendlySnake.class) {
                    board[i][j] = "l" + grid[i][j].getIndex();
                }
            }
        }
        return board;
    }

    public void fillGrid() {
        grid[0][0] = new Player(0);
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
        for (WildSnake wildSnake : wildSnakes) {
            grid[wildSnake.getHead().x][wildSnake.getHead().y] = null;
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
