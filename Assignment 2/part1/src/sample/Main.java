package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * CS3503 Assignment 2 - part 1
 * Oct. 7 / 2019
 * Avery Briggs
 * 3471065
 *
 * JavaFX GUI with five widgets grouped on a BorderPane.
 * The central widget is a resizable Canvas on a StackPane.
 * It draws a white oval on a blue background in the entire
 * central space between all other widgets.
 *
 * @author Avery Briggs
 * */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Main background.
        BorderPane borderPane = new BorderPane();
        double windowWidth = 890;
        double windowHeight = 550;

        // Menu bar with 3 buttons and 10 items each.
        HashMap<String, ArrayList<String>> menus = genMenus();
        GenMenuBar genMenuBar = new GenMenuBar(menus);
        MenuBar top = genMenuBar.getMenuBar();
        top.setPrefSize(windowWidth, 25);
        top.setMinSize(200, 15);
        top.setMaxSize(Double.MAX_VALUE, 35);
        borderPane.setTop(top);

        // FlowPane of 40 circles of radius 25.
        FlowPaneCircles flowPaneCircles = new FlowPaneCircles();
        FlowPane left = flowPaneCircles.getFlowPane();
        left.setPrefSize(200, 500);
        left.setMinSize(200, 200);
        left.setMaxSize(250, 600);
        borderPane.setLeft(left);

        // ListView of 26 colours.
        ListViewColours listViewColours = new ListViewColours();
        ListView<String> right = listViewColours.getListView();
        right.setPrefSize(150, 500);
        right.setMinSize(75, 100);
        right.setMaxSize(300, 600);
        borderPane.setRight(right);

        // Label to report the selected colour from ListView.
        Label bottom = new Label();
        bottom.setPrefSize(windowWidth, 50);
        bottom.setMinSize(100, 50);
        bottom.setMaxSize(Double.MAX_VALUE, 50);
        bottom.setAlignment(Pos.BASELINE_CENTER);
        borderPane.setBottom(bottom);

        // Resizable blue canvas with a white oval drawn inside.
        double centerWidth = windowWidth - left.getPrefWidth() - right.getPrefWidth();
        CenterCanvas centerCanvas = new CenterCanvas(((windowHeight - top.getPrefHeight()) - bottom.getPrefHeight()), centerWidth);
        Canvas[] center = {centerCanvas.getCenterCanvas()};
        StackPane pane = new StackPane();
        pane.getChildren().add(center[0]);
        borderPane.setCenter(pane);

        // Listener for colour list selection.
        right.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> bottom.setText(newValue));

        primaryStage.setTitle("CS3035 Assignment 2 - part 1");
        Scene sc = new Scene(borderPane, windowWidth, windowHeight);
        primaryStage.setScene(sc);
        primaryStage.show();

        borderPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            borderPane.getChildren().remove(pane);
            pane.getChildren().remove(center[0]);
            double oldValue = (double) oldVal - (left.getWidth() + right.getWidth());
            double newValue = (double) newVal - (left.getWidth() + right.getWidth());
            double ratio = (double) newVal / (double) oldVal;
            if (ratio == Infinity || ratio == 0) {
                ratio = 1;
                if ((double) oldVal == 0) {
                    oldValue = newValue - (left.getWidth() + right.getWidth());
                }
            }
            double shrinkRatio = 1;
            if (newValue < oldValue) {
                ratio = 1;
                shrinkRatio = newValue / oldValue;
            }
            double width = ((oldValue * ratio) * shrinkRatio);
            center[0] = new CenterCanvas(center[0].getHeight(), width).getCenterCanvas();
            bottom.setPrefSize(width, bottom.getPrefHeight());
            pane.getChildren().add(center[0]);
            pane.setPrefSize(width, pane.getHeight());
            borderPane.setCenter(pane);
            borderPane.setPrefWidth((double) newVal);
        });

        borderPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            borderPane.getChildren().remove(pane);
            pane.getChildren().remove(center[0]);
            double oldValue = (double) oldVal - (top.getHeight() + bottom.getHeight());
            double newValue = (double) newVal - (top.getHeight() + bottom.getHeight());
            double ratio = (double) newVal / (double) oldVal;
            if (ratio == Infinity || ratio == 0) {
                ratio = 1;
                if ((double) oldVal == 0) {
                    oldValue = newValue - (top.getHeight() + bottom.getHeight());
                }
            }
            double shrinkRatio = 1;
            if (newValue < oldValue) {
                ratio = 1;
                shrinkRatio = newValue / oldValue;
            }
            double height = ((oldValue * ratio) * shrinkRatio);
            center[0] = new CenterCanvas(height, center[0].getWidth()).getCenterCanvas();
            pane.getChildren().add(center[0]);
            borderPane.setCenter(pane);
            pane.setPrefSize(pane.getWidth(), height);
            borderPane.setPrefHeight((double) newVal);
        });
    }

    private HashMap<String, ArrayList<String>> genMenus() {
        String[] fileLabels =  {"New",
                "Open",
                "Open Recent",
                "Open Project",
                "Settings",
                "Project Structure",
                "Other Settings",
                "Import Settings",
                "Export Settings",
                "Settings Repository"};
        String[] editLabels =  {"Undo Typing",
                "Redo",
                "Cut",
                "Copy",
                "Copy Path",
                "Copy Reference",
                "Paste",
                "Paste from History",
                "Paste without Formatting",
                "Delete"};
        String[] viewLabels =  {"Tool Windows",
                "Quick Definition",
                "Show Siblings",
                "Quick Documentation",
                "Show Bytecode",
                "Parameter Info",
                "Type Info",
                "Context Info",
                "Recent File",
                "Recently Changed Files"};
        ArrayList<String> fileItemLabels = new ArrayList<>(Arrays.asList(fileLabels));
        ArrayList<String> editItemLabels = new ArrayList<>(Arrays.asList(editLabels));
        ArrayList<String> viewItemLabels = new ArrayList<>(Arrays.asList(viewLabels));
        HashMap<String, ArrayList<String>> menus = new HashMap<>();
        menus.put("File", fileItemLabels);
        menus.put("Edit", editItemLabels);
        menus.put("View", viewItemLabels);
        return menus;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
