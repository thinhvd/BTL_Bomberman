package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movableEntities.Bomber;

public abstract class Item extends Entity {
    public Item(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }

    @Override
    public void update() {
        if (!isAlive()) {
            Sound playerDead = new Sound(Sound.pickItem);
            playerDead.play();
            BombermanGame.stillObjects.remove(this);
        }
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) this.alive = false;
        return true;
    }
}
