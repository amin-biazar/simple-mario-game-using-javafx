package objects;

import javafx.scene.image.Image;

import java.io.Serializable;

public abstract class MovingSceneObject extends SceneObject implements Serializable {
    protected Direction direction;
    protected double speed;
    protected String movingImageURL;
    protected transient Image moving;

    public MovingSceneObject(double x, double y, String imageURL) {
        super(x, y, imageURL);
    }

    public MovingSceneObject(double x, double y, double width, double height, String imageURL) {
        super(x, y, width, height, imageURL);
    }

    public MovingSceneObject(double x, double y, double width, double height, String imageURL, int speed) {
        super(x, y, width, height, imageURL);
        this.speed = speed;
    }

    public MovingSceneObject(double x, double y, double width, double height, String imageURL, int speed, Direction direction) {
        this(x, y, width, height, imageURL, speed);
        this.direction = direction;
    }

    public MovingSceneObject(double x, double y, double width, double height, String imageURL, String movingImageURL, int speed, Direction direction) {
        this(x, y, width, height, imageURL, speed);
        this.movingImageURL = imageURL;
        this.direction = direction;
        moving = new Image(movingImageURL);
    }


    public abstract void init();
}
