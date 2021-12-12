package uet.oop.bomberman.entities.movableEntities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Ghost extends Enemy {
    public Ghost(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
    }

    @Override
    public void chooseDirection() {
        if (BombermanGame.bomber.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE < 0) direction = 0;
        if (BombermanGame.bomber.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE > 0) direction = 1;
        if (BombermanGame.bomber.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE < 0) direction = 2;
        if (BombermanGame.bomber.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE > 0) direction = 3;
    }

    public void calculateMove() {
        if (newX / Sprite.SCALED_SIZE == 0 || newX / Sprite.SCALED_SIZE == 30
                || newY / Sprite.SCALED_SIZE == 0 || newY / Sprite.SCALED_SIZE == 12
                || newX / Sprite.SCALED_SIZE == BombermanGame.bomber.getNewX() / Sprite.SCALED_SIZE - 1
                || newY / Sprite.SCALED_SIZE == BombermanGame.bomber.getNewY() / Sprite.SCALED_SIZE - 1) {
            stay();
        }
    }

    public void spriteLeft() {
        img = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, left++, 60).getFxImage();
    }

    public void spriteRight() {
        img = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, right++, 60).getFxImage();
    }

    public void spriteUp() {
        img = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, up++, 60).getFxImage();
    }

    public void spriteDown() {
        img = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, down++, 60).getFxImage();
    }
}
