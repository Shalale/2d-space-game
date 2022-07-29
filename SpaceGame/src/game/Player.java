package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends GameObject {
    int health = 5;
    private int acceleration = 6;
    private BufferedImage image = null;
    static boolean keyPressed[] = new boolean[4];


    public Player(int x, int y) {
        super(x, y);
        width = 100;
        height = 100;
        velX = 2;
        setImage();
    }

    public void tick() {
        movement();
        x += velX;
    }

    public void setImage() {
        try {
            image = ImageIO.read(new File("src/res/rocket2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void movement() {
        if (keyPressed[1])
            velX = acceleration;
        else if (keyPressed[2])
            velX = -acceleration;
        else velX = 0;


        if (x <= 0) x = 0;
        if (x >= Game.WIDTH + 20) x = Game.WIDTH - 20;

    }

    public void addBullet() {
        Game.handler.addObject(new P_Bullet(x + width / 2, y));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void damaged(Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File("src/res/rocket2_damaged.png")), x, y, width, height, null);
            System.out.println("DONE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
