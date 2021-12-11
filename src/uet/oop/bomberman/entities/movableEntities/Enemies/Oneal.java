package uet.oop.bomberman.entities.movableEntities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movableEntities.Flame;

public class Oneal extends Enemy {
    public Oneal (int x, int y, Image img) {
        super(x,y,img);
        layer = 1;
    }

    @Override
    public void update() {

    }

    @Override
    public void chooseDirection(){

    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            return true;
        }
        return true;
    }
}