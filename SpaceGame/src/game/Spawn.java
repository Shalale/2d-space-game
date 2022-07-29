package game;

import java.util.Random;

public class Spawn {
    private Random random = new Random();
    //    private Handler handler;
    private int timer = 0;
    private Hud hud;
    private int basicEnemy = 0;
    public static int level = Game.level;
    private int fastEnemy = 0;
    private int followerEnemy = 0;
    private AdvantageEnemy advantageEnemy ;
    private int count = 0;
    private Game game;

    public Spawn(Hud hud, Game game) {
        this.hud = hud;
        this.game = game;
        advantageEnemy=game.advantageEnemy;
    }

//    public void tick() {
//        tick(level);
//    }

    public void tick(int level) {
        if (level == 1) {
            timer++;
            if (timer == 60) {

                for (int i = 0; i < Game.enemies.size(); i++) {

                    Game.handler.addObject(new E_Bullet(Game.enemies.get(i).x + Game.enemies.get(i).width / 2,
                            Game.enemies.get(i).y + Game.enemies.get(i).height));
                }
                timer = 0;
            }
            if (Game.countOfDead == 5) {
                Game.level = 2;
                game.loadLevel(Game.level);
                timer=0;
            }
        }
        if (Game.level == 2) {
            timer++;
            if (timer == 30 && advantageEnemy.isAlive) {

                Game.handler.addObject(new Advant_Bullet(advantageEnemy.x + 10, advantageEnemy.y + advantageEnemy.height, -7));
                Game.handler.addObject(new E_Bullet(advantageEnemy.x + advantageEnemy.width / 2, advantageEnemy.y + advantageEnemy.height));
                Game.handler.addObject(new Advant_Bullet(advantageEnemy.x + advantageEnemy.width - 10, advantageEnemy.y + advantageEnemy.height,7));
                timer = 0;
            }
        }
    }
}
