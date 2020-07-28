package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.*;

public class ScoreBoard {
    @FXML
    AnchorPane pane;
    private Person person;
    private Person[] highScore = new Person[10];
    private int noNull = 0;

    public void initialize() {

        try {
            FileInputStream fileInputStream2 = new FileInputStream("highScore.txt");
            FileInputStream fileInputStream = new FileInputStream(new File("current user.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);

            person = (Person) objectInputStream.readObject();
            highScore = (Person[]) objectInputStream2.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 10; i++) {
            if (highScore[i] == null) {
                if (person.getLastScore() != 0)
                    highScore[i] = person;

                break;
            }
            noNull++;
        }
        for (int i = 0; i < (noNull - 1); i++) {
            for (int j = 0; j < noNull - i - 1; j++) {
                if (highScore[j].getLastScore() < highScore[j + 1].getLastScore()) {
                    Person temp = highScore[j];
                    highScore[j] = highScore[j + 1];
                    highScore[j + 1] = temp;
                }
            }
        }
        if (noNull == 10) {
            for (int i = 0; i < 10; i++) {
                if (highScore[i].getLastScore() < person.getLastScore() && person.getLastScore() != 0) {
                    for (int j = 9; j > i + 1; j--) {
                        highScore[j] = highScore[j - 1];
                    }
                    highScore[i] = person;
                    break;
                }
            }
        }
        Label name = new Label("Name");
        Label score = new Label("Score");
        name.setLayoutX(350);
        score.setLayoutX(590);
        name.setLayoutY(100);
        score.setLayoutY(100);
        name.setStyle("-fx-font: italic small-caps bold 36px/30px Georgia, serif;");
        score.setStyle("-fx-font: italic small-caps bold 36px/30px Georgia, serif;");
        pane.getChildren().addAll(name, score);
        for (int i = 0; i < noNull; i++) {
            Label text = new Label((i + 1) + ". " + highScore[i].getFirstName() + " " + highScore[i].getLastName());
            Label text2 = new Label(highScore[i].getLastScore() + "");
            text.setLayoutX(200);
            text2.setLayoutX(600);
            text.setLayoutY(i * 40 + 160);
            text2.setLayoutY(i * 40 + 160);
            pane.getChildren().addAll(text, text2);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("highScore.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(highScore);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
