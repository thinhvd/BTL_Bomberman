package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }
}
