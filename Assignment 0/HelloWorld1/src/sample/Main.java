package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.paint.Paint;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;

/**
 * CS3035 Assignment 0
 * Sept.12/19
 * Avery Briggs
 * 3471065
 *
 * JavaFX application to say "Hello 3035!" when
 * the "Say Hello" button is clicked.
 * Functionality written in MAIN.JAVA.
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
     * Initializes the stage with a scene including a label and a button.
     * Creates an action listener for the click me button.
     * @param primaryStage Primary stage object from Application class.
     */
    @Override
    public void start(Stage primaryStage){

        // dimensions and styling objects
        int viewHeight = 180;
        int viewWidth = 250;
        int fontSize = 14;

        Background backGround = new Background(new BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY));
        Font font = Font.font("System Bold", FontWeight.EXTRA_BOLD, fontSize);
        Color color = Color.WHITE;

        // create a label
        Label myLabel = new Label();
        myLabel.setFont(font);
        myLabel.setTextFill(color);

        // create a button
        Button myButton = new Button("Say Hello");
        myButton.setOnAction(e -> myLabel.setText("Hello 3035!"));

        // create a pane
        BorderPane pane = new BorderPane();
        BorderPane.setAlignment(myButton, Pos.TOP_LEFT);
        BorderPane.setAlignment(myLabel, Pos.CENTER_LEFT);

        // add elements to pane
        pane.setPrefSize(viewWidth, viewHeight);
        pane.setLeft(myButton);
        pane.setCenter(myLabel);
        pane.setBackground(backGround);

        Scene scene = new Scene(pane, viewWidth, viewHeight);

        // set and show scene
        primaryStage.setTitle("CS3035 Assignment 0 - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();

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
