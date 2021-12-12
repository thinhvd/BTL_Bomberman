package uet.oop.bomberman.entities.movableEntities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movableEntities.AnimatedEntities;
import uet.oop.bomberman.entities.movableEntities.Bomb;
import uet.oop.bomberman.entities.movableEntities.Bomber;
import uet.oop.bomberman.entities.movableEntities.Flame;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Enemy extends AnimatedEntities {
    protected int direction;
    protected int _timeToVanish = 60;
    protected int animate = 0;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (!isAlive()) {
            _timeToVanish--;
            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate++, 150).getFxImage();
        } else {
            collideCheck();
            if (direction == 0) {
                goLeft();
                spriteLeft();
            }
            if (direction == 1) {
                goRight();
                spriteRight();
            }
            if (direction == 2) {
                goUp();
                spriteUp();
            }
            if (direction == 3) {
                goDown();
                spriteDown();
            }
            calculateMove();
        }
        if (_timeToVanish == 0) BombermanGame.enemies.remove(this);
    }

    public void calculateMove() {
        for (Enemy e : BombermanGame.enemies) {for (Entity o : Bomber.bombs) {
                if (e.bound().intersects(o.bound())) {
                    if (e.collide(o)) {
                        e.stay();
                    } else {
                        e.move();
                    }
                }
            }
            for (Entity o : BombermanGame.enemies) {
                if (e.equals(o)) continue;
                if (e.bound().intersects(o.bound())) {
                    if (e.collide(o)) {
                        e.move();
                    } else {
                        e.stay();
                    }
                }
            }
            //return;
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

    public abstract void spriteLeft();

    public abstract void spriteRight();

    public abstract void spriteUp();

    public abstract void spriteDown();

    public void collideCheck() {
        this.collide(BombermanGame.bomber);
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
        if (e instanceof Wall || e instanceof Brick) {
            return e.collide(this);
        }
        if (e instanceof Enemy) {
            return false;
        }
        return true;
    }

    @Override
    public Rectangle bound() {
        return new Rectangle(newX, newY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

}