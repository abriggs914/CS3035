package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;

/**
 * CS3035 Assignment 1 - part 2
 * Sept. 24 / 2019
 * Avery Briggs
 * 3471065
 *
 * A javafx program to create three
 * slider widgets from scratch,
 * and report their values.
 *
 * @author Avery Briggs
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Top part, part 1
        GridPane gp = new GridPane();
        VBox vb = new VBox();

        gp.setHgap(10.0);
        gp.setHgap(10.0);

        Slider slider = new Slider();
        Label reportSlider = new Label();
        Button quitButton = new Button();

        slider.setValue(275.0);
        slider.setMin(150.0);
        slider.setMax(1000.0);
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
        vb.setPrefHeight(135.0);
        vb.setPrefWidth(150.0);
        vb.setPadding(new Insets(25, 25,25,25));
        vb.setAlignment(Pos.BASELINE_CENTER);

        // 2nd part

        BarControl bar;
        HBox barPane;
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10,10,10,10));

        bar = new BarControl(100, 500, 250);
        barPane = bar.getBarControl();
        hbox.getChildren().add(0, barPane);

        bar = new BarControl(0, 1000, 300);
        barPane = bar.getBarControl();
        hbox.getChildren().add(1, barPane);

        bar = new BarControl(0, 10, 10);
        barPane = bar.getBarControl();
        hbox.getChildren().add(2, barPane);

        gp.addRow(0, hbox);
        gp.addRow(1, vb);
        primaryStage.setTitle("Assignment 1 - part 2");
        primaryStage.setScene(new Scene(gp, 450, 375));
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
