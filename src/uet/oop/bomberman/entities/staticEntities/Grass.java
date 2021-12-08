package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Grass extends Entity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
        layer = 0;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }
}
