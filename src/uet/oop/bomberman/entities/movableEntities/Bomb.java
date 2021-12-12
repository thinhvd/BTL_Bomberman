package uet.oop.bomberman.entities.movableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntities {
    int _timeToExplode = 120;
    private int radius;
    int animate = 0;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        layer = 2;
    }

    public Bomb(int x, int y, Image img, int radius) {
        super(x, y, img);
        layer = 2;
        this.radius = radius;
    }

    @Override
    public void update() {
        _timeToExplode--;
        if (_timeToExplode < 0) {
            explode();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate++, 52).getFxImage();
    }

    public void explode() {
        Flame flame = new Flame(x, y);
        Sound bombExplode = new Sound(Sound.bombExplode);
        bombExplode.play();
        flame.setRadius(radius);
        flame.flameExplode();
        alive = false;
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            this._timeToExplode = 0;
        }
        return true;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}