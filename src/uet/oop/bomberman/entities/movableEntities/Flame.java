package uet.oop.bomberman.entities.movableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movableEntities.Enemies.Balloon;
import uet.oop.bomberman.entities.movableEntities.Enemies.Enemy;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Flame extends Entity {
    private String direction;
    private int radius;
    private int right;
    private int left;
    private int top;
    private int down;
    private int _timeToVanish = 20;
    private int animate = 0;

    public Flame(int x, int y, Image img, String direction) {
        super(x, y, img);
        this.radius = 1;
        this.direction = direction;
    }

    public Flame(int x, int y) {
        super(x, y);
        this.radius = 1;
    }

    @Override
    public void update() {
        collideCheck();
        if (_timeToVanish > 0) {
            _timeToVanish--;
            if (direction.equals("middle"))
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate++, 20).getFxImage();
            if (direction.equals("topLast"))
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animate++, 20).getFxImage();
            if (direction.equals("downLast"))
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate++, 20).getFxImage();
            if (direction.equals("rightLast"))
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, animate++, 20).getFxImage();
            if (direction.equals("leftLast"))
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animate++, 20).getFxImage();
            if (direction.equals("vertical"))
                img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate++, 20).getFxImage();
            if (direction.equals("horizontal"))
                img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate++, 20).getFxImage();
        } else {
            BombermanGame.flames.remove(this);
        }
    }

    public void flameExplode() {
        Right();
        Left();
        Top();
        Down();
        createFlame();
    }

    private void Right() {
        for (int i = 0; i < radius; i++) {
            // Flame flame = new Flame(x + Sprite.SCALED_SIZE * (i + 1), y);
            //for (Entity o : BombermanGame.stillObjects) {
            //     flame.collide(o);
            //}
            //Rectangle temp = this.bound(x, y - Sprite.SCALED_SIZE * (i + 1));
            right = i + 1;
        }
    }

    private void Left() {
        for (int i = 0; i < radius; i++) {
            left = i + 1;
        }
    }

    private void Top() {
        for (int i = 0; i < radius; i++) {
            top = i + 1;
        }
    }

    private void Down() {
        for (int i = 0; i < radius; i++) {
            down = i + 1;
        }
    }

    public void createFlame() {
        //BombermanGame.flames.add(new Flame(x, y, Sprite.bomb_exploded.getFxImage(), "middle"));
        for (int i = 0; i < right; i++) {
            Flame flame = new Flame(x + Sprite.SCALED_SIZE * (i + 1), y);
            Flame flame1 = new Flame(x, y);
            if (i == right - 1) {
                //flame.img = Sprite.explosion_horizontal_right_last.getFxImage();
                flame1.direction = "middle";
                flame.direction = "rightLast";
            } else {
                //flame.img = Sprite.explosion_horizontal.getFxImage();
                flame.direction = "horizontal";
            }
            BombermanGame.flames.add(flame);
            BombermanGame.flames.add(flame1);
        }
        for (int i = 0; i < left; i++) {
            Flame flame = new Flame(x - Sprite.SCALED_SIZE * (i + 1), y);
            if (i == left - 1) {
                //flame.img = Sprite.explosion_horizontal_left_last.getFxImage();
                flame.direction = "leftLast";
            } else {
                //flame.img = Sprite.explosion_horizontal.getFxImage();
                flame.direction = "horizontal";
            }
            BombermanGame.flames.add(flame);
        }
        for (int i = 0; i < top; i++) {
            Flame flame = new Flame(x, y - Sprite.SCALED_SIZE * (i + 1));
            if (i == top - 1) {
                //flame.img = Sprite.explosion_vertical_top_last.getFxImage();
                flame.direction = "topLast";
            } else {
                //flame.img = Sprite.explosion_vertical.getFxImage();
                flame.direction = "vertical";
            }
            BombermanGame.flames.add(flame);
        }
        for (int i = 0; i < down; i++) {
            Flame flame = new Flame(x, y + Sprite.SCALED_SIZE * (i + 1));
            if (i == down - 1) {
                //flame.img = Sprite.explosion_vertical_down_last.getFxImage();
                flame.direction = "downLast";
            } else {
                //flame.img = Sprite.explosion_vertical.getFxImage();
                flame.direction = "vertical";
            }
            BombermanGame.flames.add(flame);
        }
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean collide(Entity e) {
        if (e.bound().intersects(this.bound())) {
            if (e instanceof Brick) {
                return e.collide(this);
            }
            if (e instanceof  Wall) {
                return e.collide(this);
            }
            if (e instanceof AnimatedEntities) {
                return e.collide(this);
            }
        }
        return true;
    }

    public void collideCheck() {
        for (int i = 0; i < BombermanGame.stillObjects.size(); i++) {
            this.collide(BombermanGame.stillObjects.get(i));
        }
        for (int i = 0; i < BombermanGame.enemies.size(); i++) {
            this.collide(BombermanGame.enemies.get(i));
        }
        this.collide(BombermanGame.bomber);
    }
}
