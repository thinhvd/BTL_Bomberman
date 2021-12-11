package uet.oop.bomberman.entities.movableEntities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

public class Baloon extends Enemy {

    public Baloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (direction == 0) {
            goLeft();
            img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, left++, 60).getFxImage();
        }
        if (direction == 1) {
            goRight();
            img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, right++, 60).getFxImage();
        }
        if (direction == 2) {
            goUp();
            img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, up++, 60).getFxImage();
        }
        if (direction == 3) {
            goDown();
            img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, down++, 60).getFxImage();
        }
        calculateMove();
    }

    @Override
    public void chooseDirection() {
        Random random = new Random();
        direction = random.nextInt(4);
    }

    @Override
    public boolean collide(Entity e) {
        return e.collide(this);
    }
}