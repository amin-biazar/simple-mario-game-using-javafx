package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;

public class Controller {
    @FXML
    Label ageLabel;
    @FXML
    Button regBTN, regBTN1;
    @FXML
    TextField nameFLD, lastNameFLD, userFLD, userFLD1;
    @FXML
    PasswordField passFLD, passFLD1;
    @FXML
    Slider ageSlider;
    @FXML
    AnchorPane anchor, anchor1;
    private Person[] person = new Person[100];

    public void initialize() {

        final String[] firstName = new String[1];
        final String[] lastName = new String[1];
        final String[] userName = new String[1];
        final String[] password = new String[1];
        final int[] age = new int[1];
        regBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                firstName[0] = nameFLD.getText();
                lastName[0] = lastNameFLD.getText();
                userName[0] = userFLD.getText();
                password[0] = passFLD.getText();
                age[0] = (int) ageSlider.getValue();
                try {

                    FileInputStream fileInputStream = new FileInputStream("users.txt");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    try {
                        person = (Person[]) objectInputStream.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < person.length; i++) {
                    if (person[i] != null) {
                        if (person[i].getUserName().equals(userName[0])) {

                            Text text = new Text("username already exist");
                            text.setFill(Color.RED);
                            text.setX(150);
                            text.setY(200);
                            anchor.getChildren().add(text);
                            break;
                        }
                    } else if (person[i] == null) {

                        person[i] = new Person(firstName[0], lastName[0], userName[0], password[0], age[0]);
                        Text text = new Text("registered successfully");
                        text.setFill(Color.RED);
                        text.setX(150);
                        text.setY(200);
                        anchor.getChildren().add(text);
                        break;
                    }
                }

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("users.txt");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(person);
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        regBTN1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {

                    FileInputStream fileInputStream = new FileInputStream("users.txt");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    try {
                        person = (Person[]) objectInputStream.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String userent = userFLD1.getText();
                String passent = passFLD1.getText();
                for (Person value : person) {
                    if (value != null) {
                        if (userent.equals(value.getUserName()) && passent.equals(value.getPassword())) {
                            Text text = new Text("login successfully");
                            text.setFill(Color.RED);
                            text.setX(150);
                            text.setY(200);
                            anchor1.getChildren().add(text);
                            Text text1 = new Text(value.toString());
                            text1.setFill(Color.RED);
                            text1.setX(20);
                            text1.setY(220);
                            anchor1.getChildren().add(text1);
                            anchor.getScene().getWindow().hide();
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream("current user.txt");
                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                                objectOutputStream.writeObject(value);
                                fileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            SceneController sceneController = new SceneController();
                            try {
                                sceneController.level1();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        }
                    }
                }

            }
        });
    }
}
