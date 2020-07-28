package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    static int width, height;

    public void level1() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GAME Level 1.fxml"));
        primaryStage.setTitle("AMIN BIAZAR");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        width = (int) primaryStage.getWidth();
        height = (int) primaryStage.getHeight();
//        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public void scoreboard() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("scoreboard.fxml"));
        primaryStage.setTitle("AMIN BIAZAR");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        width = (int) primaryStage.getWidth();
        height = (int) primaryStage.getHeight();
//        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
