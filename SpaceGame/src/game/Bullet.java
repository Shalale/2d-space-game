package game;

import java.awt.*;

public class Bullet extends GameObject {
    Color color = Color.red;

    public Bullet(int x, int y) {
        super(x, y);
        width = 20;
        height = 10;
    }

    @Override
    public void tick() {
        collision();
        y += velY;
    }

    protected void collision() {//bullet hits the player
        if (this.getBounds().intersects(Game.getPlayer().getBounds())) {
            Game.handler.removeObject(this);
            Game.getPlayer().health--;
        }

        if (this.y == Game.HEIGHT) {//bullet hit the ground
            Game.handler.removeObject(this);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void render(Graphics g) {
         {
            g.setColor(color);
            g.fillOval(x, y, width, height);
        }
    }
}
