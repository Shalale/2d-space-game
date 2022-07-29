package game;

public class E_Bullet extends Bullet {
    public E_Bullet(int x, int y) {
        super(x, y);
        this.id = ID.E_Bullet;
        velY = 9;
        velX=0;
    }

    @Override
    public void tick() {
        super.tick();
    }


}
