package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main background.
        BorderPane borderPane = new BorderPane();
        double windowWidth = 1100;
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

        RowLayoutPane rowLayoutPane = new RowLayoutPane(windowWidth - left.getPrefWidth() - right.getPrefWidth(),
                ((windowHeight - top.getPrefHeight()) - bottom.getPrefHeight()));
        Widget leftWidget = new Widget();
        Widget centerWidget = new Widget();
        Widget rightWidget = new Widget();
        leftWidget.setPrefSize(250,200);
        leftWidget.setMinSize(200,200);
        leftWidget.setMaxSize(275, Double.MAX_VALUE);
        centerWidget.setPrefSize(300,400);
        centerWidget.setMinSize(250,200);
        centerWidget.setMaxSize(400,500);
        rightWidget.setPrefSize(200,200);
        rightWidget.setMinSize(100,200);
        rightWidget.setMaxSize(300,300);
        rowLayoutPane.addWidget(leftWidget, RowLayoutPane.Position.CENTER);
        rowLayoutPane.addWidget(centerWidget, RowLayoutPane.Position.FILL);
        rowLayoutPane.addWidget(rightWidget, RowLayoutPane.Position.TOP);
        Canvas canvas = rowLayoutPane.getCanvas();
        borderPane.setCenter(canvas);

        borderPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setWidth((double) newVal - right.getPrefWidth() - left.getPrefWidth());
            borderPane.getChildren().remove(canvas);
            borderPane.setCenter(rowLayoutPane.getCanvas());
        });

        borderPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            rowLayoutPane.setHeight((double) newVal - top.getPrefHeight() - bottom.getPrefHeight());
            canvas.setHeight((double) newVal - top.getPrefHeight() - bottom.getPrefHeight());
            borderPane.getChildren().remove(canvas);
            borderPane.setCenter(rowLayoutPane.getCanvas());
        });

        // Listener for colour list selection.
        right.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> bottom.setText(newValue));

        primaryStage.sizeToScene();
        primaryStage.setTitle("CS3035 Assignment 2 - part 2");
        primaryStage.setScene(new Scene(borderPane, windowWidth, windowHeight));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
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
}
