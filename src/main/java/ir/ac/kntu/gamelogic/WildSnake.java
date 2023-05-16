package ir.ac.kntu.gamelogic;

public class WildSnake extends Snake {

    public WildSnake(int index) {
        super(index);
    }

    @Override public void bite(Player player, GameObject[][] grid) {
        super.bite(player, grid);
        player.decreaseLife();
    }
}
