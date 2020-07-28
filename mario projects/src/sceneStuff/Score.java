package sceneStuff;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Score {
    private static int scorePoint;
    private Image image = new Image("Resources/textbox.png");
    private ImageView imageView = new ImageView(image);
    private double x, y;
    private Text score = new Text("Score : " + scorePoint);

    public Score(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static int getScorePoint() {
        return scorePoint;
    }

    public static void addScore(int score) {
        scorePoint += score;
    }

    public void init(Pane pane) {
        imageView.setFitWidth(200);
        imageView.setFitHeight(50);
        imageView.setX(x);
        imageView.setY(y);
        score.setX(x + 60);
        score.setY(y + 30);
        score.setFont(Font.font(20));
        pane.getChildren().addAll(imageView, score);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                score.setText("Score : " + scorePoint);
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
