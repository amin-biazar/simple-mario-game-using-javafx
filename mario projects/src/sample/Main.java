package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static double width, height;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("AMIN BIAZAR");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        width = primaryStage.getWidth();
        height = primaryStage.getHeight();
        GameLevel1.stage = primaryStage;
        primaryStage.show();
    }
}
