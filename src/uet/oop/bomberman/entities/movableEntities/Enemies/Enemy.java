package uet.oop.bomberman.entities.movableEntities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movableEntities.AnimatedEntities;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Enemy extends AnimatedEntities {
    protected int direction;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }


    public void calculateMove() {
        for (Enemy e : BombermanGame.enemies) {
            for (Entity o : BombermanGame.stillObjects) {
                if (e.bound().intersects(o.bound())) {
                    if (e.collide(o)) {
                        e.move();
                    } else {
                        e.stay();
                    }
                }
            }
        }
    }

    @Override
    public void stay() {
        super.stay();
        chooseDirection();
    }

    public abstract void chooseDirection();

    @Override
    public Rectangle bound() {
        return new Rectangle(newX, newY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

}