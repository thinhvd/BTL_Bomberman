package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;

public class BombItem extends Item {
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }
}
