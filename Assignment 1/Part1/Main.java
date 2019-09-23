package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.NumberFormat;

/**
 * CS3035 Assignment 1 - part 1
 * Sept. 24 / 2019
 * Avery Briggs
 * 3471065
 *
 * A javafx program to create a simple
 * slider widget and report values.
 *
 * @author Avery Briggs
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane gp = new GridPane();
        VBox vb = new VBox();

        gp.setHgap(10.0);
        gp.setHgap(10.0);

        Slider slider = new Slider();
        Label reportSlider = new Label();
        Button quitButton = new Button();


        slider.setMin(150.0);
        slider.setMax(1000.0);
        slider.setValue(275.0);
        slider.setPrefHeight(44.0);
        slider.setPrefWidth(227.0);

        reportSlider.setFont(Font.font ("Verdana", 24));
        reportSlider.setText(stringifyDouble(slider.getValue()));

        quitButton.setText("Quit");

        quitButton.setOnMouseClicked(e -> {
            System.out.println("GoodBye!");
            System.exit(0);
        });

        slider.setOnMouseDragged(e -> {
            double num = slider.getValue();
            String numDouble = stringifyDouble(num);
            reportSlider.setText(numDouble);
        });

        slider.setOnMouseClicked(e -> {
            double num = slider.getValue();
            String numDouble = stringifyDouble(num);
            reportSlider.setText(numDouble);
        });

        vb.getChildren().addAll(slider, reportSlider, quitButton);
        vb.setPrefHeight(155.0);
        vb.setPrefWidth(200.0);
        vb.setPadding(new Insets(25, 25,25,25));
        vb.setAlignment(Pos.CENTER);
        gp.getChildren().addAll(vb);

        primaryStage.setTitle("Assignment 1 - part 1");
        primaryStage.setScene(new Scene(gp, 200, 175));
        primaryStage.show();
    }

    private String stringifyDouble(double val) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(1);
        nf.setMinimumFractionDigits(1);
        return nf.format(val);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
