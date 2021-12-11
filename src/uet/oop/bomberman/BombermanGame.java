package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Layer;
import uet.oop.bomberman.entities.movableEntities.Bomb;
import uet.oop.bomberman.entities.movableEntities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movableEntities.Enemies.Balloon;
import uet.oop.bomberman.entities.movableEntities.Enemies.Enemy;
import uet.oop.bomberman.entities.movableEntities.Enemies.Oneal;
import uet.oop.bomberman.entities.movableEntities.Flame;
import uet.oop.bomberman.entities.staticEntities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public int level = 1;

    private GraphicsContext gc;
    private Canvas canvas;

    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static Bomber bomber;

    public static List<Flame> flames = new ArrayList<>();


    public char[][] mapMatrix = new char[HEIGHT][WIDTH];


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        loadLevel(level);

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();
        scene.setOnKeyPressed(event -> {
            bomber.KeyPressedEvent(event.getCode());
        });
        scene.setOnKeyReleased(event -> bomber.KeyReleasedEvent(event.getCode()));
    }

    public void createMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (mapMatrix[i][j] == '#') {
                    stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else {
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    switch (mapMatrix[i][j]) {
                        case '*':
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'x':
                            stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'p':
                            bomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                            break;
                        case '1':
                            enemies.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                            break;
                        case '2':
                            enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                            break;
                        case 'b':
                            stillObjects.add(new BombItem(j,i,Sprite.powerup_bombs.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'f':
                            stillObjects.add(new BombItem(j,i,Sprite.powerup_flames.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 's':
                            stillObjects.add(new BombItem(j,i,Sprite.powerup_speed.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                    }
                }
            }
        }
        stillObjects.sort(new Layer());
    }

    public void loadLevel(int _level) {
        String levelPath = "res/levels/Level" + _level + ".txt";
        try {
            Scanner sc = new Scanner(new FileReader(levelPath));
            sc.nextLine();

            enemies.clear();
            stillObjects.clear();

            for (int i = 0; i < HEIGHT; i++) {
                String mapRow = sc.nextLine();
                for (int j = 0; j < WIDTH; j++) {
                    mapMatrix[i][j] = mapRow.charAt(j);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).update();
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
        bomber.update();

        List<Bomb> bombs = bomber.getBombs();
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
        }
        for (int i = 0; i < flames.size(); i++) {
            flames.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = stillObjects.size() - 1; i >= 0; i--) {
            stillObjects.get(i).render(gc);
        }
        enemies.forEach(e -> e.render(gc));
        bomber.render(gc);
        List<Bomb> bombs = bomber.getBombs();
        bombs.forEach(g -> g.render(gc));
        flames.forEach(g -> g.render(gc));

    }

    public static Entity getEntity(int x, int y) {
        int i = 0;
        while (i < stillObjects.size()) {
            if (stillObjects.get(i).getX() / Sprite.SCALED_SIZE == x && stillObjects.get(i).getY() / Sprite.SCALED_SIZE == y)
                break;
            i++;
        }
        return stillObjects.get(i);
    }
}