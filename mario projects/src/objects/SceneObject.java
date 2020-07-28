package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

import java.io.Serializable;

public abstract class SceneObject implements Serializable {
    protected double x, y, width, height;
    protected String imageURL;
    protected transient ImageView imageView = new ImageView();
    protected transient Image image;
    protected transient Pane pane;

    public SceneObject(double x, double y, String imageURL) {
        this(x, y);
        this.imageURL = imageURL;
    }

    public SceneObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public SceneObject(double x, double y, double width, double height, String imageURL) {
        this(x, y, imageURL);
        this.width = width;
        this.height = height;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setImageAsset() {
        try {
            image = new Image(imageURL);
            imageView = new ImageView(image);
            imageView.setRotationAxis(Rotate.Y_AXIS);
            imageView.setX(x);
            imageView.setY(y);
            imageView.setImage(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            addToPane(pane);
            imageView.setFocusTraversable(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToPane(Pane pane) {
        pane.getChildren().add(imageView);
    }

    public void removeFromPane(Pane pane) {
        pane.getChildren().remove(imageView);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
