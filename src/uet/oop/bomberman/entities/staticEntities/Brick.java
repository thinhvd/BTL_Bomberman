package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movableEntities.Bomb;
import uet.oop.bomberman.entities.movableEntities.Enemies.Ghost;
import uet.oop.bomberman.entities.movableEntities.Flame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    private int _timeToVanish = 40;
    int animate = 0;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        layer = 3;
    }

    @Override
    public void update() {
        if (!isAlive()) {
            _timeToVanish--;
            img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate++, 20).getFxImage();
            if (_timeToVanish == 0) BombermanGame.stillObjects.remove(this);
        }
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) this.alive = false;
        if (e instanceof Ghost) return true;
        return false; //fix later
    }
}