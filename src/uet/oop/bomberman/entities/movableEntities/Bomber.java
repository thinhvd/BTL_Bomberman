package uet.oop.bomberman.entities.movableEntities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends AnimatedEntities {
    private KeyCode direction;
    private int bombRemain;
    private List<Bomb> bombs = new ArrayList<>();
    private int _timeBetweenPutBombs;
    private int radius;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
        bombRemain = 1;
        radius = 1;
    }

    @Override
    public void update() {
        if (direction == KeyCode.LEFT) {
            goLeft();
            img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left++, 20).getFxImage();
        }
        if (direction == KeyCode.RIGHT) {
            goRight();
            img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20).getFxImage();
        }
        if (direction == KeyCode.UP) {
            goUp();
            img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
        }
        if (direction == KeyCode.DOWN) {
            goDown();
            img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20).getFxImage();
        }

        calculateMove();

        if(_timeBetweenPutBombs < -10000) _timeBetweenPutBombs = 0;
        else _timeBetweenPutBombs--;
        detectPlaceBomb();
        checkBomb();
    }

    public void KeyPressedEvent(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN || keyCode == KeyCode.SPACE) {
            direction = keyCode;
        }
    }

    public void KeyReleasedEvent(KeyCode keyCode) {
        if (direction == keyCode) {
            if (direction == KeyCode.LEFT) {
                img = Sprite.player_left.getFxImage();
            }
            if (direction == KeyCode.RIGHT) {
                img = Sprite.player_right.getFxImage();
            }
            if (direction == KeyCode.UP) {
                img = Sprite.player_up.getFxImage();
            }
            if (direction == KeyCode.DOWN) {
                img = Sprite.player_down.getFxImage();
            }
        }
        direction = null;
    }


    public void calculateMove(){
        for (Entity e : BombermanGame.stillObjects) {
            if (BombermanGame.bomber.bound().intersects(e.bound())) {
               if (BombermanGame.bomber.collide(e)) {
                    BombermanGame.bomber.move();
                } else {
                    BombermanGame.bomber.stay();
               }
    public void detectPlaceBomb() {
        if (direction == KeyCode.SPACE && _timeBetweenPutBombs < 0) {
            placeBomb();
            _timeBetweenPutBombs = 30;
        }
    }

    public void placeBomb() {
        if (bombRemain > 0) {
            Bomb bomb = new Bomb(canvasToBomb(x), canvasToBomb(y), Sprite.bomb.getFxImage(), radius);
            for (Bomb b : bombs) {
                if (canvasToBomb(x) == b.getX() && canvasToBomb(y) == b.getY()) return;
            }
            bombRemain--;
            bombs.add(bomb);
        }
    }

    public void checkBomb() {
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                bombRemain++;
            }
        }
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    @Override
    public Rectangle bound() {
        return new Rectangle(newX + 4, newY + 5, Sprite.SCALED_SIZE - 12, Sprite.SCALED_SIZE - 6);
    }

    public boolean collide(Entity e) {
        return e.collide(this);
    }
}