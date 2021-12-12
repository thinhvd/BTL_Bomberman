package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Entity {
    public int level = 1;
    public Portal(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        if (e.bound().intersects(this.bound()) && BombermanGame.enemies.isEmpty()) {
            level++;
            BombermanGame.loadLevel(level);
        }
        return false;
    }
}
