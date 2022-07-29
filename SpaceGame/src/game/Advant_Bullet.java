package game;

import java.awt.*;

public class Advant_Bullet extends Bullet {
    public Advant_Bullet(int x, int y, int speed) {
        super(x, y);
        velX = speed;
        velY = 9;
    }

    public void tick() {
        super.tick();
        if (x<=0 || x>=Game.WIDTH-20){
            velX*=-1;
        }
        x+=velX;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
