package ir.ac.kntu.gamelogic;

import java.awt.*;

public class Player extends GameObject {

    private Point location;

    private int life;

    public Player(int index) {
        super(index);
        life = 3;
        location = new Point(0, 0);
    }

    public int getIndex() {
        return index;
    }

    public boolean nextTurn(DiceOptions diceOption, GameObject[][] grid) {
        int xOffset = 0;
        int yOffset = 0;
        switch (diceOption) {
            case ONE_UP -> xOffset = 1;
            case ONE_LEFT -> yOffset = -1;
            case ONE_RIGHT -> yOffset = 1;
            case ONE_DOWN -> xOffset = -1;
            case TWO_UP -> xOffset = 2;
            case TWO_LEFT -> yOffset = -2;
            case TWO_RIGHT -> yOffset = 2;
            case TWO_DOWN -> xOffset = -2;
            case EXTRA_LIFE -> {
                if (life < 3) {
                    life++;
                }
                return false;
            }
            default -> {
                return false;
            }
        }
        if (location.x + xOffset < 0 || location.x + xOffset >= grid.length || location.y + yOffset < 0 ||
                location.y + yOffset >= grid[0].length) {
            return false;
        }
        grid[location.x][location.y] = null;
        location.x += xOffset;
        location.y += yOffset;
        move(grid);
        return true;
    }

    public void move(GameObject[][] grid) {
        if (grid[location.x][location.y] == null) {
            grid[location.x][location.y] = this;
        } else if (grid[location.x][location.y].getClass() != Player.class) {
            Snake snake = (Snake) grid[location.x][location.y];
            snake.bite(this, grid);
        }
    }

    public void moveTo(Point point) {
        location = new Point(point);
    }

    public void decreaseLife() {
        life--;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getLife() {
        return life;
    }
}
