package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movableEntities.Enemies.Ghost;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        layer = 4;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Ghost) {
            return true;
        }
        return false; //fix later
    }
}
