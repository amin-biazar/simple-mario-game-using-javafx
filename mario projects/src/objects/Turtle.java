package objects;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.Serializable;

public class Turtle extends MovingSceneObject implements Serializable {
    private transient Timeline timeline;
    private int startMoving, endMoving;


    public Turtle(double x, double y, double width, double height, String imageURL, int speed, Direction direction, int startMoving, int endMoving) {
        super(x, y, width, height, imageURL, speed, direction);
        this.startMoving = startMoving;
        this.endMoving = endMoving;
    }


    @Override
    public void init() {
        boolean shooter = false;
        if (Math.random() > 0.5)
            shooter = true;
        setImageAsset();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        boolean finalShooter = shooter;
        KeyFrame kf = new KeyFrame(Duration.millis(3 * 20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int rotate;

                if (imageView.getX() >= endMoving && direction.equals(Direction.RIGHT)) {
                    speed = -speed;
                    rotate = 180;
                    imageView.setRotationAxis(Rotate.Y_AXIS);
                    imageView.setRotate(rotate);


                } else if (imageView.getX() < startMoving && direction.equals(Direction.RIGHT)) {
                    speed = -speed;
                    rotate = 0;
                    imageView.setRotationAxis(Rotate.X_AXIS);
                    imageView.setRotate(rotate);
                    imageView.setX(imageView.getX() + speed);
                }
            }
        });
        timeline.getKeyFrames().add(kf);
        timeline.play();

    }

    Timeline getTimeline() {
        return timeline;
    }
}
