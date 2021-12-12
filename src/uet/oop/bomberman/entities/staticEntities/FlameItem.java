package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }
}
