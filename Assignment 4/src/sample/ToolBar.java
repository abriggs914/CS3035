package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

class ToolBar extends Pane {

    private ToggleGroup shapeToggleGroup;

    private ToggleButton squareToggleButton;
    private ToggleButton circleToggleButton;
    private ToggleButton triangleToggleButton;
    private Button cutButton;
    private Button pasteButton;

    ToolBar() {
        init();
    }

    private void init() {
        this.shapeToggleGroup = new ToggleGroup();

        this.squareToggleButton = new ToggleButton();
        this.circleToggleButton = new ToggleButton();
        this.triangleToggleButton = new ToggleButton();

        this.cutButton = new Button();
        this.pasteButton = new Button();

        squareToggleButton.setFont(new Font("Helvetica", 15));
        circleToggleButton.setFont(new Font("Helvetica", 15));
        triangleToggleButton.setFont(new Font("Helvetica", 15));
        cutButton.setFont(new Font("Helvetica", 15));
        pasteButton.setFont(new Font("Helvetica", 15));

        squareToggleButton.setTooltip(new Tooltip("square"));
        circleToggleButton.setTooltip(new Tooltip("circle"));
        triangleToggleButton.setTooltip(new Tooltip("triangle"));
        cutButton.setTooltip(new Tooltip("cut"));
        pasteButton.setTooltip(new Tooltip("paste"));

        squareToggleButton.setGraphic(new ImageView(new Image("resources/square.png")));
        circleToggleButton.setGraphic(new ImageView(new Image("resources/circle.png")));
        triangleToggleButton.setGraphic(new ImageView(new Image("resources/triangle.png")));
        cutButton.setGraphic(new ImageView(new Image("resources/cut.png")));
        pasteButton.setGraphic(new ImageView(new Image("resources/paste.png")));

        shapeToggleGroup.getToggles().addAll(squareToggleButton, circleToggleButton, triangleToggleButton);
        squareToggleButton.setSelected(true);

        HBox shapeButtons = new HBox();
        HBox actionButtons = new HBox();

        shapeButtons.getChildren().addAll(squareToggleButton, circleToggleButton, triangleToggleButton);
        actionButtons.getChildren().addAll(cutButton, pasteButton);

        shapeButtons.setMinWidth(150);
        actionButtons.setMinWidth(100);

        HBox buttons = new HBox();
        buttons.setPadding(new Insets(5,15,10,15));
        buttons.getChildren().addAll(shapeButtons, actionButtons);
        HBox.setMargin(shapeButtons, new Insets(0,50,0,5));
        getChildren().addAll(buttons);
        this.setMinWidth(400.0);
        this.setMinHeight(35.0);
        this.setStyle("-fx-background-color: #708090");
    }

    ToggleButton getSquareToggleButton() {
        return squareToggleButton;
    }

    ToggleButton getCircleToggleButton() {
        return circleToggleButton;
    }

    ToggleButton getTriangleToggleButton() {
        return triangleToggleButton;
    }

    Button getCutButton() {
        return cutButton;
    }

    Button getPasteButton() {
        return pasteButton;
    }
}
