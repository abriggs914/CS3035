package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ToolBar extends Pane {

    public ToggleGroup shapeToggleGroup;
    public ToggleGroup actionToggleGroup;

    public ToggleButton squareToggleButton;
    public ToggleButton circleToggleButton;
    public ToggleButton triangleToggleButton;
    public ToggleButton cutToggleButton;
    public ToggleButton pasteToggleButton;

    ToolBar() {
        init();
    }

    private void init() {
        this.shapeToggleGroup = new ToggleGroup();
        this.actionToggleGroup = new ToggleGroup();

        this.squareToggleButton = new ToggleButton("Square");
        this.circleToggleButton = new ToggleButton("Circle");
        this.triangleToggleButton = new ToggleButton("Triangle");

        this.cutToggleButton = new ToggleButton("Cut");
        this.pasteToggleButton = new ToggleButton("Paste");

        squareToggleButton.setFont(new Font("Helvetica", 15));
        circleToggleButton.setFont(new Font("Helvetica", 15));
        triangleToggleButton.setFont(new Font("Helvetica", 15));
        cutToggleButton.setFont(new Font("Helvetica", 15));
        pasteToggleButton.setFont(new Font("Helvetica", 15));

        shapeToggleGroup.getToggles().addAll(squareToggleButton, circleToggleButton, triangleToggleButton);
        actionToggleGroup.getToggles().addAll(cutToggleButton, pasteToggleButton);
        squareToggleButton.setSelected(true);

        HBox shapeButtons = new HBox();
        HBox actionButtons = new HBox();

        shapeButtons.getChildren().addAll(squareToggleButton, circleToggleButton, triangleToggleButton);
        actionButtons.getChildren().addAll(cutToggleButton, pasteToggleButton);

        shapeButtons.setMinWidth(150);
        actionButtons.setMinWidth(100);

        shapeButtons.setStyle("-fx-border-color: black");
        actionButtons.setStyle("-fx-border-color: black");

        HBox buttons = new HBox();
        buttons.setPadding(new Insets(5,15,10,15));
        buttons.getChildren().addAll(shapeButtons, actionButtons);
        HBox.setMargin(shapeButtons, new Insets(0,50,0,5));
        getChildren().addAll(buttons);
        this.setMinWidth(400.0);
        this.setMinHeight(35.0);
    }
}
