package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * CS3035 Assignment 0
 * Sept.12/19
 * Avery Briggs
 * 3471065
 *
 * JavaFXML application to say "Hello 3035!" when
 * the "Say Hello!" button is clicked.
 * Functionality written in sample.fxml.
 *
 * @author (Avery Briggs)
 */

/**
 * Class Main is created on application start.
 * Extends the Application class in order to set
 * the primary stage.
 */
public class Main extends Application {

    /**
     * Method start() overrides the Application class's definition.
     * Initializes the stage with a scene.
     * @param primaryStage Primary stage object from Application class.
     */
    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("CS3053 Assignment 0 - part 2");
            primaryStage.setScene(new Scene(root, 250, 180));
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method of class Main.
     * Calls Application's launch method.
     * @param args String array of arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
