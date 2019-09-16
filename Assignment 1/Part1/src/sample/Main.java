package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Assignment 1 - part 1");
            Scene scene = new Scene(root, 300, 275);


            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Slider getSlider() {
        return
    }

//    public void seddtLabel(Scene) {
//        Slider slider = (Slider) scene.lookup("#slider");
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
