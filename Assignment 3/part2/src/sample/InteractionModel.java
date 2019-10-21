package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InteractionModel extends Pane {

//    final ToolBarController toolBarController = new ToolBarController();
    FXMLLoader fxmlLoader;
    HBox toolBar;
    private Color selectedColor;
    private boolean creatingVertices;

    public InteractionModel() throws Exception{
        this.fxmlLoader = new FXMLLoader(getClass().getResource("toolbar.fxml"));
        this.toolBar = (HBox) fxmlLoader.load();
        this.getChildren().add(toolBar);
        this.selectedColor = Color.valueOf("#ff3333");
        this.creatingVertices = true;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void draw() {
//        this.getChildren().clear();
//        this.getChildren().add(toolBar);
    }

    @Override
    public void layoutChildren() { draw(); }

    public void setSelectedColor(Color color) {
        this.selectedColor = color;
    }

    public void redButtonSelected() {
        toolBar.lookup("#redButton").setStyle("-fx-text-fill: white; -fx-base: #ff3333;");
    }

    public void greenButtonSelected() {
        toolBar.lookup("#greenButton").setStyle("-fx-text-fill: white; -fx-base: green;");
    }

    public void blueButtonSelected() {
        toolBar.lookup("#blueButton").setStyle("-fx-text-fill: white; -fx-base: blue;");
    }

    public void unSelectRedButton() {
        toolBar.lookup("#redButton").setStyle("-fx-text-fill: black; -fx-base: #ff3333;");
    }

    public void unSelectGreenButton() {
        toolBar.lookup("#greenButton").setStyle("-fx-text-fill: black; -fx-base: green;");
    }

    public void unSelectBlueButton() {
        toolBar.lookup("#blueButton").setStyle("-fx-text-fill: black; -fx-base: blue;");
    }

    public void setIsCreating() {
        this.creatingVertices = true;
    }
    public void setIsDeleting() {
        this.creatingVertices = false;
    }

    public boolean isCreatingVertices() {
        return creatingVertices;
    }
}
