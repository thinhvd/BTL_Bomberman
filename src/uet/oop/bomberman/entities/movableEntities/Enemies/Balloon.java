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
    public int direction;
    private int animate;//delete later maybe?
    private int _timeToVanish = 60;

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
        chooseDirection();
    }

    @Override
    public void update() {
        if (!isAlive()) {
            _timeToVanish--;
            img = this.img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate++, 150).getFxImage();
        } else {
            collideCheck();
            if (direction == 0) {
                super.goLeft();
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, left++, 60).getFxImage();
            }
            if (direction == 1) {
                super.goRight();
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, right++, 60).getFxImage();
            }
            if (direction == 2) {
                super.goUp();
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, up++, 60).getFxImage();
            }
            if (direction == 3) {
                super.goDown();
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, down++, 60).getFxImage();
            }
            calculateMove();

        }

        if (_timeToVanish == 0) BombermanGame.enemies.remove(this);
    }

    @Override
    public void chooseDirection() {
        Random random = new Random();
        direction = random.nextInt(4);
    }

    @Override
    public void stay() {
        super.stay();
        chooseDirection();
    }

    public Rectangle bound() {
        return new Rectangle(newX, newY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            this.alive = false;
            return true;
        }
        if (e.bound().intersects(this.bound()) && e instanceof Bomber) {
            return e.collide(this);
        }
        if (e instanceof Wall || e instanceof Brick || e instanceof Bomb) return e.collide(this);
        return true;
    }

    public void collideCheck() {
        this.collide(BombermanGame.bomber);
    }
}