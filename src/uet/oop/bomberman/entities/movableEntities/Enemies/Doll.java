package uet.oop.bomberman.entities.movableEntities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Doll extends Enemy{
    public Doll (int x, int y, Image img) {
        super(x, y, img);
        speed = 4;
    }
    @Override
    public void chooseDirection() {
        Random random = new Random();
        direction = random.nextInt(2);
    }

    public void spriteLeft() {
        img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, left++, 60).getFxImage();
    }

    public void spriteRight() {
        img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, right++, 60).getFxImage();
    }

    public void spriteUp() {
        img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, left++, 60).getFxImage();
    }

    public void spriteDown() {
        img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, right++, 60).getFxImage();
    }
}
