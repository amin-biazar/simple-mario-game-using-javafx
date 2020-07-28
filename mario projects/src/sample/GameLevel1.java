package sample;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import objects.*;
import sceneStuff.EndGameScene;
import sceneStuff.Score;
import sceneStuff.Timer;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class GameLevel1 {
    public static boolean turtlesKilled = false;
    static Stage stage;
    public ArrayList<Turtle> turtles = new ArrayList<>();
    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView bi;
    private Mario mario = new Mario(700, 510, 120, 170, "Resources/mario standing.gif", "Resources/mario moving.gif", 3, Direction.RIGHT, this);
    private Mario sonic = new Mario(700, 10, 120, 170, "Resources/sonic standing.gif", "Resources/sonic moving.gif", 3, Direction.RIGHT, this);
    private Timer timer = new Timer(1200, 10, 0, 3, 0);
    private Teleport teleport = new Teleport();
    private Tunnel tunnel = new Tunnel(1450, 550, 100, 100, "Resources/tunnel.png");
    private long milis = 0;

    public void initialize() {
        anchorPane.setPrefWidth(SceneController.width);
        anchorPane.setPrefHeight(SceneController.height);
        bi.setFitHeight(Main.height);
        bi.setFitWidth(Main.width);
        Button savebutton = new Button("save");
        savebutton.setLayoutX(400);
        savebutton.setLayoutY(30);
        savebutton.setPrefWidth(100);
        anchorPane.getChildren().add(savebutton);
        Random random = new Random();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                save();
            }
        });
        turtles.add(new Turtle(114 + random.nextDouble() * 436, 566, 105, 114, "Resources/dinasour.gif", (int) random.nextDouble() * 5 + 1, Direction.RIGHT, 114, 550));
        if (random.nextDouble() >= 0.5)
            turtles.add(new Turtle(794 + random.nextDouble() * 436, 566, 105, 114, "Resources/dinasour.gif", (int) random.nextDouble() * 5 + 1, Direction.RIGHT, 794, 1280));
//        turtles.add(new Turtle(794 + random.nextDouble() * 436, 250,105,114,"Resources/dinasour.gif", (int) random.nextDouble() * 5 + 1,Direction.RIGHT, 794, 1280));
        for (Turtle turtle : turtles) {
            turtle.setPane(anchorPane);
            turtle.init();
        }
        tunnel.setPane(anchorPane);
        teleport.teleport(anchorPane);

        tunnel.tunnel();
        Score score = new Score(100, 10);
        load();
        score.init(anchorPane);
        timer.init(anchorPane);
        mario.setPane(anchorPane);
        sonic.setPane(anchorPane);
        sonic.init();
        mario.init();
        encounter();


        savebutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                save();
            }
        });
    }

    public void encounter() {
        for (Turtle turtle : turtles) {
            if (mario != null) {
                if (turtle != null)
                    turtle.getImageView().xProperty().addListener((observable, oldValue, newValue) -> {

                        if (turtle.getImageView().intersects(mario.getImageView().getBoundsInLocal())) {
                            mario.removeFromPane(anchorPane);
                            mario = null;
                            EndGameScene.init(anchorPane);
                        } else if (sonic != null)
                            if (turtle.getImageView().intersects(sonic.getImageView().getBoundsInLocal())) {
                                sonic.removeFromPane(anchorPane);
                                sonic = null;
                                EndGameScene.init(anchorPane);
                            }
                    });


                mario.getImageView().xProperty().addListener(marioXYChangeListener(mario));
                mario.getImageView().yProperty().addListener(marioXYChangeListener(mario));
                sonic.getImageView().xProperty().addListener(marioXYChangeListener(mario));
                sonic.getImageView().yProperty().addListener(marioXYChangeListener(mario));
            }
        }

    }

    private ChangeListener marioXYChangeListener(Mario mario) {
        return (observable, oldValue, newValue) -> {
            if (System.currentTimeMillis() > milis + 3000) {
                if (teleport.getTpImage().intersects(mario.getImageView().getBoundsInLocal())) {
                    mario.getImageView().setX(teleport.getTpImage2().getX());
                    mario.getImageView().setY(teleport.getTpImage2().getY());
                    PauseTransition pauseTransition = new PauseTransition();
                    pauseTransition.setDuration(Duration.millis(3000));
                    pauseTransition.play();
                    milis = System.currentTimeMillis();
                }
                if (teleport.getTpImage2().intersects(mario.getImageView().getBoundsInLocal())) {
                    mario.getImageView().setX(teleport.getTpImage().getX());
                    mario.getImageView().setY(teleport.getTpImage().getY());
                    PauseTransition pauseTransition = new PauseTransition();
                    pauseTransition.setDuration(Duration.millis(3000));
                    pauseTransition.play();
                    milis = System.currentTimeMillis();
                }
            }
            if (turtlesKilled && mario.getImageView().intersects(tunnel.getImageView().getBoundsInLocal())) {
                mario.removeFromPane(anchorPane);
                EndGameScene.init(anchorPane);
            }
        };
    }

    private void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("myObjects.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(mario);
            objectOutputStream.writeObject(sonic);
            objectOutputStream.writeObject(timer);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void load() {
        try {

            FileInputStream fileInputStream = new FileInputStream(new File("myObjects.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            mario = (Mario) objectInputStream.readObject();
            sonic = (Mario) objectInputStream.readObject();
            timer = (Timer) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public Timer getTimer() {
        return timer;
    }
}



