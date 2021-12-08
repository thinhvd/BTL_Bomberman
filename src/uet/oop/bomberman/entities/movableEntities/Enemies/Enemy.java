package uet.oop.bomberman.entities.movableEntities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.movableEntities.AnimatedEntities;

public abstract class Enemy extends AnimatedEntities {
    public Enemy (int x, int y, Image img) {
        super(x,y,img);
    }

    public abstract void chooseDirection();

}
