package sceneStuff;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.Serializable;


public class Timer implements Serializable {
    private transient Image image = new Image("Resources/textbox.png");
    private transient ImageView imageView = new ImageView(image);
    private double x, y;
    private int second = 0, minute = 3;
    private int hour;
    private transient Text timer = new Text("Time left : " + minute + ":" + second);
    private transient Pane pane;
    private transient Text hourText = new Text("");
    private transient Text minuteText = new Text("");
    private transient Text secondText = new Text("");
    private transient KeyFrame keyFrame;
    private transient Timeline timeline = new Timeline(keyFrame);

    public Timer(double x, double y, int second, int minute, int hour) {
        this.x = x;
        this.y = y;
        this.second = second;
        this.minute = minute;
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Timer{" +
                "image=" + image +
                ", imageView=" + imageView +
                ", x=" + x +
                ", y=" + y +
                ", second=" + second +
                ", minute=" + minute +
                ", hour=" + hour +
                ", timer=" + timer +
                ", hourText=" + hourText +
                ", minuteText=" + minuteText +
                ", secondText=" + secondText +
                ", keyFrame=" + keyFrame +
                ", timeline=" + timeline +
                '}';
    }

    public int getSecond() {
        return second;
    }

    public int getTotalSecond() {
        return second + (minute * 60);
    }

    public int getMinute() {
        return minute;
    }

    public void addMinute(Pane pane) {
        pane.getChildren().add(minuteText);
        minuteText.setX(x + 70);
        minuteText.setY(y + 30);
        minuteText.setFont(Font.font("Arial", 20));
        minuteText.setFill(Color.RED);
    }

    public void addSecond(Pane pane) {
        pane.getChildren().add(secondText);
        secondText.setX(x + 100);
        secondText.setY(y + 30);
        secondText.setFont(Font.font("Arial", 20));
        secondText.setFill(Color.RED);
    }

    public void init(Pane pane) {
        image = new Image("Resources/textbox.png");
        imageView = new ImageView(image);
        timer = new Text("Time left : " + minute + ":" + second);
        keyFrame = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override

            public void handle(ActionEvent event) {
                if (minute != 0 || second != 0 || hour != 0) {
                    if (minute == 0 && second == 0) {
                        minute = 59;
                        hour -= 1;
                        second = 59;
                    } else if (second == 0) {
                        second = 59;
                        minute -= 1;
                    } else {
                        second -= 1;
                    }
                    if (hour >= 10)
                        hourText.setText(hour + ":");
                    else hourText.setText("0" + hour + ":");
                    if (minute >= 10)
                        minuteText.setText(minute + ":");
                    else minuteText.setText("0" + minute + ":");
                    if (second >= 10)
                        secondText.setText(second + "");
                    else secondText.setText("0" + second + "");
                    timer.setText("Time left : " + minuteText.getText() + secondText.getText());

                } else {
                    EndGameScene.init(pane);
                    timeline.stop();
                }
            }
        });
        timeline = new Timeline(keyFrame);
        minuteText = new Text("");
        hourText = new Text("");
        secondText = new Text("");
        this.pane = pane;
        imageView.setFitWidth(200);
        imageView.setFitHeight(50);
        imageView.setX(x);
        imageView.setY(y);
        timer.setX(x + 25);
        timer.setY(y + 30);
        timer.setFont(Font.font("Arial", 20));
        pane.getChildren().addAll(imageView, timer);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }
}
