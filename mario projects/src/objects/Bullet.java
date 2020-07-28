package objects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Bullet extends MovingSceneObject {
    public Bullet(double x, double y, double width, double height, String imageURL, int speed, Direction direction) {
        super(x, y, width, height, imageURL, speed, direction);
    }

    @Override
    public void init() {
        setImageAsset();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        if (direction.equals(Direction.LEFT)) {
            imageView.setRotate(180);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(1000 / speed), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (imageView.getX() >= 0)
                        imageView.setX(imageView.getX() - 1);
                    else {
                        removeFromPane(pane);
                        timeline.stop();
                    }
                }
            });
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        } else if (direction.equals(Direction.RIGHT)) {
            KeyFrame keyFrame = new KeyFrame(Duration.millis(1000 / speed), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (imageView.getX() <= pane.getWidth()) {

                        imageView.setX(imageView.getX() + 1);
                    } else {
                        removeFromPane(pane);
                        timeline.stop();
                    }

                }
            });
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        } else if (direction.equals(Direction.UP)) {
            KeyFrame keyFrame = new KeyFrame(Duration.millis(1000 / speed), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (imageView.getY() >= 0) {

                        imageView.setY(imageView.getY() - 1);
                    } else {
                        removeFromPane(pane);
                        timeline.stop();
                    }

                }
            });
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        } else if (direction.equals(Direction.DOWN)) {
            KeyFrame keyFrame = new KeyFrame(Duration.millis(1000 / speed), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (imageView.getY() <= pane.getHeight()) {

                        imageView.setY(imageView.getY() + 1);
                    } else {
                        removeFromPane(pane);
                        timeline.stop();
                    }

                }
            });
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        }

    }

}
