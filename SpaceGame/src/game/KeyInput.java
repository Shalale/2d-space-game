package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Game game;
    private Player player;

    public KeyInput(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_LEFT) player.keyPressed[2] = true;
        if (key == KeyEvent.VK_RIGHT) player.keyPressed[1] = true;

        if (key == KeyEvent.VK_P) {
            if (Game.gameRun) {
                Game.gameRun = false;
                player.keyPressed[3] = true;
            } else {
                Game.gameRun = true;
                player.keyPressed[3] = false;
            }
        }

        if (key == KeyEvent.VK_SPACE) {
            Game.getPlayer().addBullet();
            player.keyPressed[0] = true;
        }
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_LEFT) player.keyPressed[2] = false;
        if (key == KeyEvent.VK_RIGHT) player.keyPressed[1] = false;
        if (key == KeyEvent.VK_SPACE) player.keyPressed[0] = false;

    }


}
