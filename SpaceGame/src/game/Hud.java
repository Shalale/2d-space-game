package game;

import java.awt.*;

public class Hud {
    private Player player;
    private BasicEnemy enemy;

    public Hud(Player player) {
        this.player = (Player) player;
    }

    public Hud(BasicEnemy enemy) {
        this.enemy = enemy;
    }

    public void winner(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.setFont(new Font("default", Font.BOLD, 36));
        graphics.drawString("YOU WIN THE GAME", 250, 370);
        Game.gameRun = false;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fill3DRect(10, 10, 200 - 2 * (player.health + 1) * 10, 25, true);

        graphics.setColor(Color.GREEN);
        graphics.fill3DRect(10, 10, 2 * (player.health) * 10, 25, true);
    }

    void pause(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("default", Font.BOLD, 36));
        graphics.drawString("PAUSE", 400, 370);
    }

    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("default", Font.BOLD, 36));
        graphics.drawString("GAME OVER", 350, 370);
        Game.gameRun = false;
    }
}
