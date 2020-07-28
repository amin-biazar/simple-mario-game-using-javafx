package objects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Teleport {
    private int fromX, fromY, toX, toY;
    private transient Image tp = new Image("Resources/teleport portal.gif");
    private transient ImageView tpImage = new ImageView(tp);
    private transient ImageView tpImage2 = new ImageView(tp);

    public void teleport(Pane pane) {
        Random random = new Random();
        tpImage.setX(random.nextDouble() * 1280);
        tpImage.setY(random.nextDouble() * 720);
        tpImage2.setX(random.nextDouble() * 1280);
        tpImage2.setY(random.nextDouble() * 720);
        pane.getChildren().addAll(tpImage, tpImage2);
        tpImage.setFitWidth(150);
        tpImage.setFitHeight(150);
        tpImage2.setFitWidth(150);
        tpImage2.setFitHeight(150);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tpImage.setX(random.nextDouble() * 1280);
                tpImage.setY(random.nextDouble() * 720);
                tpImage2.setX(random.nextDouble() * 1280);
                tpImage2.setY(random.nextDouble() * 720);

            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public ImageView getTpImage() {
        return tpImage;
    }

    public ImageView getTpImage2() {
        return tpImage2;
    }
}
