package uet.oop.bomberman.entities.movableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class AnimatedEntities extends Entity {
    protected int newX;
    protected int newY;
    protected int left = 0;
    protected int right = 0;
    protected int up = 0;
    protected int down = 0;


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

}
