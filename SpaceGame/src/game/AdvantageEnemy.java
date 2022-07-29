package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AdvantageEnemy extends GameObject {
     public int health = 5;
    public boolean isAlive=true;

    public AdvantageEnemy(int x, int y) {
        super(x, y);
        width = 120;
        height = 80;
        velY = 0;
        velX = 3;
        setImage();
    }

    @Override
    public void tick() {
        makeClamp();
        collision();

        x += velX;
        y += velY;
    }
    private void makeClamp() {
        x = Game.clamp(x, 0, Game.WIDTH - (width + 10));
        y = Game.clamp(y, 0, 200);

        if (x == Game.WIDTH - (width + 10) || x == 0)
            velX *= -1;
        if (y == 0 || y == 200) velY *= -1;
    }
    private void collision() {//p_bullet hits the enemy
        for (int i = 0; i < Game.handler.objects.size(); i++) {
            GameObject obj = Game.handler.objects.get(i);
            if (obj.id == ID.P_Bullet && this.getBounds().intersects(obj.getBounds())) {
                this.health--;
                Game.handler.removeObject(obj);

            }
        }

        if (health == 0) {
            Game.handler.removeObject(this);
            isAlive=false;
        }
    }
    private void setImage() {
        try {
            image = ImageIO.read(new File("src/res/advantage_enemy_.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
        showHealth(g);
    }
    public void showHealth( Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, 5);

        g.setColor(Color.green);
        g.fillRect(x, y, 24*health, 5);
    }

}
