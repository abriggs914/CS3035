package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class ToolBarController {

    ToolBarController() {
        ToolBar toolBar = Main.view.getToolbar();
        ToggleButton squareButton = toolBar.getSquareToggleButton();
        ToggleButton circleButton = toolBar.getCircleToggleButton();
        ToggleButton triangleButton = toolBar.getTriangleToggleButton();

        Button cutButton = toolBar.getCutButton();
        Button pasteButton = toolBar.getPasteButton();

        squareButton.setOnMouseClicked(event -> {
            System.out.println("\tSquare button clicked");
            Main.model.shapeIsSquare();
        });

        circleButton.setOnMouseClicked(event -> {
            System.out.println("\tCircle button clicked");
            Main.model.shapeIsCircle();
        });

        triangleButton.setOnMouseClicked(event -> {
            System.out.println("\tTriangle button clicked");
            Main.model.shapeIsTriangle();
        });

        cutButton.setOnMouseClicked(event -> {
            Main.iModel.getSelectedSquares().itemsProperty().removeAll(Main.model.cutShapesListPropertyProperty());
            Main.model.resetCutList();
            System.out.println("\tcut button clicked");
            SelectionGroup selectedShapes = Main.iModel.getSelectedSquares();
//            System.out.println("items: " + selectedShapes.itemsProperty());
            if (Main.model.cutShapesListPropertyProperty().size() > 0 &&
                    selectedShapes.itemsProperty().size() > 0) {
                Main.model.resetCutList();
            }
            Main.model.cutShapesListPropertyProperty().addAll(Main.iModel.getSelectedSquares().itemsProperty());
            Main.model.shapeListProperty().removeAll(Main.iModel.getSelectedSquares().itemsProperty());
//            System.out.println("\tFINALIZED CUT LIST:\n" + Main.model.getCutShapesListProperty());
        });

        pasteButton.setOnMouseClicked(event -> {
            System.out.println("\tpaste button clicked");
            Main.model.shapeListProperty().addAll(Main.model.getCutShapesListProperty());
            Main.model.resetCutList();
            Main.iModel.clearSelected();
        });
    }
}
