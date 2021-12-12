package uet.oop.bomberman.entities.movableEntities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movableEntities.Enemies.Enemy;
import uet.oop.bomberman.entities.staticEntities.*;
import uet.oop.bomberman.graphics.Sprite;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends AnimatedEntities {
    private KeyCode direction;
    private int bombRemain;
    public static List<Bomb> bombs = new ArrayList<>();
    private int _timeBetweenPutBombs;
    private int radius;
    private boolean bombSet = false;
    private int _timeToVanish = 40;
    private int animate = 0;
    private boolean soundPlayed = false;
    public int countStep = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
        speed = 2;
        bombRemain = 1;
        radius = 1;
    }

    @Override
    public void update() {
        if (!isAlive()) {
            if (_timeToVanish > 0) {
                if (!soundPlayed) playSoundDead();
                _timeToVanish--;
                img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate++, 60).getFxImage();
            } else {
                BombermanGame.bomber = new Bomber(1, 1, Sprite.player_left.getFxImage());
            }
        } else {
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
            if (direction != null) {
                countStep++;
                if (countStep % 10 == 0) {
                    Sound walking = new Sound(Sound.walking);
                    walking.play();
                    if (countStep > 1000) countStep = 0;
                }
            }

            calculateMove();

        }
        if (_timeBetweenPutBombs < -10000) _timeBetweenPutBombs = 0;
        else _timeBetweenPutBombs--;
        detectPlaceBomb();
        checkBomb();
    }

    public void KeyPressedEvent(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
            direction = keyCode;
        }
        if (keyCode == KeyCode.SPACE) bombSet = true;
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
        bombSet = false;
    }


    public void calculateMove() {
        for (Entity e : BombermanGame.stillObjects) {
            if (BombermanGame.bomber.bound().intersects(e.bound())) {
                if (BombermanGame.bomber.collide(e)) {
                    BombermanGame.bomber.move();
                } else {
                    BombermanGame.bomber.stay();
                }
            }
        }
    }

    public void detectPlaceBomb() {
        if (bombSet && _timeBetweenPutBombs < 0) {
            placeBomb();
            _timeBetweenPutBombs = 50;
        }
    }

    public int canvasToBomb(int a) {
        return Math.round(a + 10) / Sprite.SCALED_SIZE;
    }

    public void placeBomb() {
        if (bombRemain > 0) {
            Bomb bomb = new Bomb(canvasToBomb(x), canvasToBomb(y), Sprite.bomb.getFxImage(), radius);
            for (Bomb b : bombs) {
                if (canvasToBomb(x) == b.getX() && canvasToBomb(y) == b.getY()) return;
            }
            Sound placeBomb = new Sound(Sound.placeBomb);
            placeBomb.play();
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

    public void playSoundDead() {
        Sound playerDead = new Sound(Sound.bomberDead);
        playerDead.play();
        soundPlayed = true;
    }

    @Override
    public Rectangle bound() {
        return new Rectangle(newX + 4, newY + 6, Sprite.SCALED_SIZE - 12, Sprite.SCALED_SIZE - 11);
    }

    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            this.alive = false;
            return true;
        }
        if (e instanceof Enemy) {
            for (Enemy enemy : BombermanGame.enemies) {
                enemy.getAwayFromMe();
            }
            this.alive = false;
            return false;
        }
        if (e instanceof Item) {
            if (e instanceof SpeedItem) {
                e.collide(this);
                this.speed++;
            }
            if (e instanceof BombItem) {
                e.collide(this);
                this.bombRemain++;
            }
            if (e instanceof FlameItem) {
                e.collide(this);
                this.radius++;
            }
        }
        if (e instanceof Brick || e instanceof Wall || e instanceof Portal) return e.collide(this);
        return true;
    }
}