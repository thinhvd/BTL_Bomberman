package uet.oop.bomberman.entities.movableEntities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.PrimitiveIterator;
import java.util.Random;

public class Bomb extends AnimatedEntities {
    int _timeToExplode = 120;
    int _timeToFlame = 40;//delete later
    boolean bombSet = false;// delete later
    private int radius;
    int animate = 0;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        layer = 2;
        this.radius = 1;
    }

    public Bomb(int x, int y, Image img, int radius) {
        super(x, y, img);
        layer = 2;
        this.radius = radius;
    }

    @Override
    public void update() {
        _timeToExplode--;
        if (_timeToExplode == 0) {
            explode();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate++, 52).getFxImage();
    }

    public void explode() {
        Flame flame = new Flame(x, y);
        flame.setRadius(radius);
        flame.flameExplode();
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
