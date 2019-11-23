package sample;

import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

class View extends Pane {

    private static final Color FILL_COLOR = Color.GREEN;
    private static final Color SELECTED_FILL_COLOR = Color.BLUE;
    private static Group root;
    private ToolBar toolbar;

    View() {

        Main.model.shapeListProperty().addListener((ListChangeListener<Shape>) c -> {
            while (c.next()) {
                for (Shape r : c.getRemoved()) {
                    root.getChildren().remove(r);
                }
                for (Shape r : c.getAddedSubList()) {
                    r.setStroke(Color.BLACK);
                    r.setFill(FILL_COLOR);
                    root.getChildren().add(r);
                    if (Main.model.cutShapesListPropertyProperty().contains(r)) {
                        deselect(r);
                    }
                }
            }
        });
        Main.iModel.getSelectedSquares().itemsProperty().addListener((ListChangeListener<Shape>) c -> {
            deselectAll();
            for (Shape r : Main.iModel.getSelectedSquares().itemsProperty()) {
                selectSquare(r);
            }
        });

        Main.iModel.selectRegionProperty().addListener((ChangeListener<Shape>) (observable, oldValue, newValue) -> {
            root.getChildren().remove(oldValue);

            if (newValue !=null) {
                root.getChildren().add(newValue);
                newValue.setFill(new Color(0,0,.5,.3));
                newValue.setStroke(new Color(0,0,.5,1));
            }
        });

        root = new Group();
        this.toolbar = new ToolBar();
        toolbar.setMinSize(420, 35);
        this.getChildren().addAll(root, toolbar);
    }

    private void deselect(Shape r) {
        r.setFill(FILL_COLOR);
        r.setStrokeWidth(1);
    }

    private void deselectAll() {
        for (Shape s : Main.model.shapeListProperty())
        {
            deselect(s);
        }
    }

    private void selectSquare(Shape node) {
        node.setFill(View.SELECTED_FILL_COLOR);
        node.setStrokeWidth(4);
    }

    ToolBar getToolbar() {
        return toolbar;
    }

}