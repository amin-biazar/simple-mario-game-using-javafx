package sceneStuff;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.GameLevel1;
import sample.Person;
import sample.SceneController;

import java.io.*;

public class EndGameScene {
    private static transient ImageView gameOver = new ImageView("Resources/textbox.png");
    private static transient Text text = new Text("GAME OVER");

    public static void init(Pane pane) {

        gameOver.setFitWidth(650);
        gameOver.setFitHeight(130);
        gameOver.setX(450);
        gameOver.setY(250);
        text.setFill(Color.RED);
        text.setX(500);
        text.setY(350);
        text.setFont(Font.font(100));
        pane.getChildren().addAll(gameOver, text);
        if (GameLevel1.turtlesKilled) {
            Person person = null;
            text.setText("VICTORY");
            try {
                FileInputStream fileInputStream = new FileInputStream(new File("current user.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                person = (Person) objectInputStream.readObject();


                objectInputStream.close();
                fileInputStream.close();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
            person.setLastScore(Score.getScorePoint());
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File("current user.txt"));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(person);

                objectOutputStream.close();
                fileOutputStream.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


        }
        PauseTransition pause = new PauseTransition(Duration.millis(4000));
        pause.setOnFinished(e -> {
            pane.getScene().getWindow().hide();
            SceneController sceneController = new SceneController();
            try {
                sceneController.scoreboard();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pause.play();


    }
}
