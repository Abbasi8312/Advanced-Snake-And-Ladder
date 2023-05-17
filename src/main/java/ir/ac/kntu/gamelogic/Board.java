package ir.ac.kntu.gamelogic;

public class Board {
    private final GameObject[][] grid;

    private final RegularSnake[] regularSnakes;

    private final FriendlySnake[] friendlySnakes;

    private final WildSnake[] wildSnakes;

    private final int rowCount;

    private final int columnCount;

    private final GameStatus gameStatus;

    private Player player;

    public Board(int rowCount, int columnCount, int snakeCount) {
        if (rowCount == 0) {
            rowCount = 2 + RandomHelper.nextInt(10);
        }
        if (columnCount == 0) {
            columnCount = 2 + RandomHelper.nextInt(10);
        }
        if (snakeCount == 0) {
            snakeCount = 1 + RandomHelper.nextInt(rowCount * columnCount / 2 - 1);
        }
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        grid = new GameObject[rowCount][columnCount];
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
        gameStatus = new GameStatus(this);
    }

    public StringBuilder[][] getGrid() {
        StringBuilder[][] board = new StringBuilder[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                board[i][j] = new StringBuilder();
            }
        }
        board[player.getLocation().x][player.getLocation().y].append("P").append(player.getIndex());
        for (RegularSnake regularSnake : regularSnakes) {
            board[regularSnake.head.x][regularSnake.head.y].append("s").append(regularSnake.index);
        }
        for (WildSnake wildSnake : wildSnakes) {
            board[wildSnake.head.x][wildSnake.head.y].append("S").append(wildSnake.index);
        }
        for (FriendlySnake friendlySnake : friendlySnakes) {
            board[friendlySnake.head.x][friendlySnake.head.y].append("l").append(friendlySnake.index);
        }
        for (RegularSnake regularSnake : regularSnakes) {
            board[regularSnake.tail.x][regularSnake.tail.y].append("d").append(regularSnake.index);
        }
        for (WildSnake wildSnake : wildSnakes) {
            board[wildSnake.tail.x][wildSnake.tail.y].append("D").append(wildSnake.index);
        }
        for (FriendlySnake friendlySnake : friendlySnakes) {
            board[friendlySnake.tail.x][friendlySnake.tail.y].append("r").append(friendlySnake.index);
        }
        int maxSize = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                maxSize = Math.max(board[i][j].length(), maxSize);
            }
        }
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                board[i][j].append(" ".repeat(maxSize - board[i][j].length()));
            }
        }
        return board;
    }

    public void fillGrid() {
        player = new Player(0);
        grid[0][0] = player;
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

    public boolean nextTurn(DiceOptions diceOption) {
        return player.nextTurn(diceOption, grid);
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public Player getPlayer() {
        return player;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
