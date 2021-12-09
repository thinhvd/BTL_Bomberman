package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        layer = 3;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}