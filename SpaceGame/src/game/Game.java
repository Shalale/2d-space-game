package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 900, HEIGHT = WIDTH * 7 / 8;
    public static boolean gameRun = true;
    private Window gameWindow;
    private Thread thread;
    public static Handler handler = new Handler();
    private boolean running;
    private static Player player;
    public AdvantageEnemy advantageEnemy = new AdvantageEnemy(WIDTH / 2, 10);
    private Hud hud;
    private Spawn spawn;
    public static ArrayList<BasicEnemy> enemies = new ArrayList<>();
    private Image image;
    private Camera camera;
    public static int level = 1;
    public static int countOfDead = 0;

    public Game() {
        initGame();
        loadLevel(level);
    }

    public static void main(String[] args) {
        new Game();
    }

    public void loadLevel(int level) {
        if (level == 1) {
            BasicEnemy basicEnemy1 = new BasicEnemy(Game.WIDTH / 4, 190 - 50, 5);
            BasicEnemy basicEnemy2 = new BasicEnemy(Game.WIDTH * 3 / 4, 190 - 50, 5);
            BasicEnemy basicEnemy3 = new BasicEnemy(Game.WIDTH / 5, 100 - 50, 5);
            BasicEnemy basicEnemy4 = new BasicEnemy(Game.WIDTH * 3 / 5, 100 - 50, 5);
            BasicEnemy basicEnemy5 = new BasicEnemy(Game.WIDTH - 50, 100 - 50, 5);

            enemies.add(basicEnemy1);
            enemies.add(basicEnemy2);
            enemies.add(basicEnemy3);
            enemies.add(basicEnemy4);
            enemies.add(basicEnemy5);

            handler.addObject(basicEnemy1);
            handler.addObject(basicEnemy2);
            handler.addObject(basicEnemy3);
            handler.addObject(basicEnemy4);
            handler.addObject(basicEnemy5);
        }
        if (level == 2) {
            player.health = 5;
            handler.addObject(advantageEnemy);
            countOfDead = 0;
        }
    }

    public void initGame() {
        player = new Player(WIDTH / 2, HEIGHT - 130);
        handler.addObject(player);
        camera = new Camera(0, 0);
        hud = new Hud(player);
        spawn = new Spawn(hud, this);
        setImage();
        addKeyListener(new KeyInput(this, player));
        gameWindow = new Window(WIDTH, HEIGHT, "Our First Game", this);
    }

    public static int clamp(int number, int min, int max) {
        if (number >= max) return number = max;
        else if (number <= min) return number = min;
        return number;
    }

    private void setImage() {
        try {
            image = ImageIO.read(new File("src/res/background_stars.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;
            }
            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
//                System.out.println("FPS :" + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        if (gameRun) {
            handler.tick();
            spawn.tick(level);
            camera.tick();
        }


    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        render(g, level);

        if (advantageEnemy.health == 0){
            hud.winner(g);
        }

        g.dispose();
        bs.show();
    }

    private void render(Graphics g, int level) {
        if (level != 3) {
            g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
            handler.render(g);
        } else {
            g.translate((int) camera.getX(), (int) camera.getY());
            g.drawImage(image, 0, 0, 3 * WIDTH, HEIGHT, null);
            handler.render(g);
            g.translate((int) -camera.getX(), (int) -camera.getY());
        }

        hud.render(g);

        if (player.health == 0) {
            hud.gameOver(g);
        }
        if (player.keyPressed[3]) {
            hud.pause(g);
        }
    }


    public static Player getPlayer() {
        return player;
    }

}