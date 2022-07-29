package game;

import java.awt.*;
import java.util.ArrayList;

public class Handler {
    Player player;

    ArrayList<GameObject> objects = new ArrayList<>();

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if (object != null)
                object.tick();


        }
    }

    public void render(Graphics graphics) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            if (temp != null)
                temp.render(graphics);
        }
    }

    public void addObject(GameObject obj) {
        objects.add(obj);
    }

    public void removeObject(GameObject obj) {
        objects.remove(obj);
    }
}
