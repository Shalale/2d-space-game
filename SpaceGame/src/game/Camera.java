package game;


public class Camera {
    private Player player = Game.getPlayer();
    private float x;
    private float y;

    public Camera(int x, int y) {
//        this.id = ID.Camera;
        this.x = x;
        this.y = y;
    }

    public void tick() {
        x = (int) (-player.getX() + Game.WIDTH / 5);
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
