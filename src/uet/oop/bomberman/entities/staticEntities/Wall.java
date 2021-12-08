package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

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
        return false;
    }
}
