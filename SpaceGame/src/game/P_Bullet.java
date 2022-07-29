package game;

import java.awt.*;

public class P_Bullet extends Bullet {
    public P_Bullet(int x, int y) {
        super(x, y);
        color = Color.green;
        id = ID.P_Bullet;
        velY = 9;
    }

    @Override
    public void tick() {
        removeBullet();
        y += -velY;
    }

    private void removeBullet(){//remove the bullet if it hit the ground
        if (this.y == 0){
            Game.handler.removeObject(this);
        }
    }
}
