package objects;

import javafx.scene.transform.Rotate;

public class Tunnel extends SceneObject {

    public Tunnel(double x, double y, double width, double height, String imageURL) {
        super(x, y, width, height, imageURL);
    }

    public void tunnel() {
        setImageAsset();
        imageView.setRotationAxis(Rotate.Z_AXIS);
        imageView.setRotate(270);
    }


}
