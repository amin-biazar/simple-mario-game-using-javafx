package objects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import sample.GameLevel1;
import sceneStuff.Score;

import java.io.Serializable;


public class Mario extends MovingSceneObject implements Serializable {
    private boolean turn = true;
    private short bullets = 10;
    private short totalBullets = 140;
    private transient GameLevel1 gameLevel1;
    private boolean jumping = false;


    public Mario(double x, double y, double width, double height, String imageURL, String movingImageURL, int speed, Direction direction, GameLevel1 gameLevel1) {
        super(x, y, width, height, imageURL, movingImageURL, speed, direction);
        this.gameLevel1 = gameLevel1;
    }


    @Override
    public void init() {
//        moving=new Image(movingImageURL);
        setImageAsset();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imageView.requestFocus();
            }
        });

        imageView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case A:
                        marioMove(0);
                        direction = Direction.LEFT;

                        break;
                    case D:
                        marioMove(180);
                        direction = Direction.RIGHT;
                        break;
                    case F:
                        if (bullets > 0) {
                            bullets--;
                            Bullet bullet = new Bullet(imageView.getX(), imageView.getFitHeight() / 2 + imageView.getY() + imageView.getTranslateY(), 50, 50, "Resources/bullet.png", 100, direction);
                            bullet.setPane(pane);
                            bullet.init();
                            bullet.imageView.xProperty().addListener(new ChangeListener<Number>() {
                                                                         @Override
                                                                         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                                                             for (Turtle turtle : gameLevel1.turtles) {
                                                                                 if (turtle.getImageView().intersects(bullet.imageView.getBoundsInLocal())) {
                                                                                     bullet.removeFromPane(pane);
                                                                                     turtle.removeFromPane(pane);

                                                                                     gameLevel1.turtles.remove(turtle);
                                                                                     turtle.getTimeline().stop();
                                                                                     Score.addScore(gameLevel1.getTimer().getTotalSecond());
                                                                                     if (gameLevel1.turtles.isEmpty())
                                                                                         GameLevel1.turtlesKilled = true;
                                                                                     break;
                                                                                 }
                                                                             }
                                                                         }
                                                                     }
                            );
                        }
                        break;
                    case U:
                        if (bullets > 0) {
                            bullets--;
                            Bullet bullet = new Bullet(imageView.getX(), imageView.getFitHeight() / 2 + imageView.getY() + imageView.getTranslateY(), 50, 50, "Resources/bullet.png", 100, Direction.DOWN);
                            bullet.setPane(pane);
                            bullet.init();
                            bullet.imageView.xProperty().addListener(new ChangeListener<Number>() {
                                                                         @Override
                                                                         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                                                             for (Turtle turtle : gameLevel1.turtles) {
                                                                                 if (turtle.getImageView().intersects(bullet.imageView.getBoundsInLocal())) {
                                                                                     bullet.removeFromPane(pane);
                                                                                     turtle.removeFromPane(pane);

                                                                                     gameLevel1.turtles.remove(turtle);
                                                                                     turtle.getTimeline().stop();
                                                                                     Score.addScore(gameLevel1.getTimer().getTotalSecond());
                                                                                     if (gameLevel1.turtles.isEmpty())
                                                                                         GameLevel1.turtlesKilled = true;
                                                                                     break;
                                                                                 }
                                                                             }
                                                                         }
                                                                     }
                            );
                        }
                        break;
                    case J:
                        if (bullets > 0) {
                            bullets--;
                            Bullet bullet = new Bullet(imageView.getX(), imageView.getFitHeight() / 2 + imageView.getY() + imageView.getTranslateY(), 50, 50, "Resources/bullet.png", 100, Direction.UP);
                            bullet.setPane(pane);
                            bullet.init();
                            bullet.imageView.xProperty().addListener(new ChangeListener<Number>() {
                                                                         @Override
                                                                         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                                                             for (Turtle turtle : gameLevel1.turtles) {
                                                                                 if (turtle.getImageView().intersects(bullet.imageView.getBoundsInLocal())) {
                                                                                     bullet.removeFromPane(pane);
                                                                                     turtle.removeFromPane(pane);

                                                                                     gameLevel1.turtles.remove(turtle);
                                                                                     turtle.getTimeline().stop();
                                                                                     Score.addScore(gameLevel1.getTimer().getTotalSecond());
                                                                                     if (gameLevel1.turtles.isEmpty())
                                                                                         GameLevel1.turtlesKilled = true;
                                                                                     break;
                                                                                 }
                                                                             }
                                                                         }
                                                                     }
                            );
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        imageView.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case A:
                        marioStand(180);
                        break;
                    case D:
                        marioStand(0);
                        break;
                    case W:
                        marioJump();
                        break;
                    case R:
                        if (totalBullets > 0) {
                            totalBullets = (short) (totalBullets - (10 - bullets));
                            bullets += (10 - bullets);
                        }
                        break;
                    default:
                        break;
                }

            }
        });

    }

    private void marioMove(int rotation) {
        imageView.setImage(moving);
        imageView.setRotate(rotation);
        if (rotation == 0)
            imageView.setX(imageView.getX() + (-speed));
        if (rotation == 180)
            imageView.setX(imageView.getX() + (+speed));
        x = imageView.getX();
    }

    private void marioStand(int rotation) {
        imageView.setImage(image);
        imageView.setRotate(rotation);
    }

    private void marioJump() {
        if (!jumping) {
            jumping = true;
            double tempY = imageView.getY();
            Timeline timeline2 = new Timeline();
            timeline2.setCycleCount(Animation.INDEFINITE);
            KeyFrame keyFrame2 = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (imageView.getY() - tempY >= -150 && turn) {
                        imageView.setY(imageView.getY() - 1);
                        if (imageView.getY() - tempY == -150)
                            turn = false;
                    } else if (imageView.getY() - tempY <= 0 && !turn) {
                        imageView.setY(imageView.getY() + 1);
                        if (imageView.getY() - tempY == 0) {
                            turn = true;
                            jumping = false;
                            timeline2.stop();
                        }
                    }
                }
            });
            timeline2.getKeyFrames().add(keyFrame2);
            timeline2.play();
        }


    }

}
