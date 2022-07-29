package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    protected int x;
    protected int y;
    protected int width = 80;
    protected int height = 80;
    protected double velX, velY;
    protected ID id;
    protected BufferedImage image;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract Rectangle getBounds();

    public abstract void render(Graphics g);


    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
