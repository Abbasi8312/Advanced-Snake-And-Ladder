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

    public void move(DiceOptions diceOption, GameObject[][] grid) {
        grid[location.x][location.y] = null;
        switch (diceOption) {
            case ONE_UP -> {
                if (location.x < grid.length - 1) {
                    location.x += 1;
                }
            }
            case ONE_LEFT -> {
                if (location.y > 0) {
                    location.y -= 1;
                }
            }
            case ONE_RIGHT -> {
                if (location.y < grid[0].length - 1) {
                    location.y += 1;
                }
            }
            case ONE_DOWN -> {
                if (location.x > 0) {
                    location.x -= 1;
                }
            }
            case TWO_UP -> {
                if (location.x < grid.length - 2) {
                    location.x += 2;
                }
            }
            case TWO_LEFT -> {
                if (location.y > 1) {
                    location.y -= 2;
                }
            }
            case TWO_RIGHT -> {
                if (location.y < grid[0].length - 2) {
                    location.y += 2;
                }
            }
            case TWO_DOWN -> {
                if (location.x > 1) {
                    location.x -= 2;
                }
            }
            case EXTRA_LIFE -> life++;
            default -> {
                return;
            }
        }
        checkLocation(grid);
    }

    public void checkLocation(GameObject[][] grid) {
        if (grid[location.x][location.y] == null) {
            grid[location.x][location.y] = this;
        } else {
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
}
