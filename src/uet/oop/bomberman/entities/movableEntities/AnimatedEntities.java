package uet.oop.bomberman.entities.movableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class AnimatedEntities extends Entity {
    protected int newX = x;
    protected int newY = y;
    protected int left = 0;
    protected int right = 0;
    protected int up = 0;
    protected int down = 0;

    public int canvasToBomb(int a) {
        return Math.round(a + 4) / Sprite.SCALED_SIZE;
    }

    public AnimatedEntities(int x, int y, Image img) {
        super(x,y,img);
    }

    public void goLeft() {
        newX = x - 1;
    }

    public void goRight() {
        newX = x + 1;
    }
    public void goUp() {
        newY = y - 1;
    }

    public void goDown() {
        newY = y + 1;
    }
    public void move() {
        x = newX;
        y = newY;
    }
    public void stay() {
        newX = x;
        newY = y;
    }
}