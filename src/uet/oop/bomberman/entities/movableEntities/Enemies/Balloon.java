package uet.oop.bomberman.entities.movableEntities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movableEntities.Bomb;
import uet.oop.bomberman.entities.movableEntities.Bomber;
import uet.oop.bomberman.entities.movableEntities.Flame;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

public class Balloon extends Enemy {

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
    }

    @Override
    public void chooseDirection() {
        Random random = new Random();
        direction = random.nextInt(4);
    }

    public void spriteLeft() {
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, left++, 60).getFxImage();
    }

    public void spriteRight() {
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, right++, 60).getFxImage();
    }

    public void spriteUp() {
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, up++, 60).getFxImage();
    }

    public void spriteDown() {
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, down++, 60).getFxImage();
    }

    public Rectangle bound() {
        return new Rectangle(newX, newY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }


}