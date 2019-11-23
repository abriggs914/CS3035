package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Rectangle;


class InteractionModel {
    private SelectionGroup selectedSquares;
    private SimpleObjectProperty<Rectangle> selectRegion;
    private double[] startClick;

    InteractionModel() {
        startClick = new double[2];
        selectedSquares = new SelectionGroup();
        selectRegion = new SimpleObjectProperty<>();
    }

    SimpleObjectProperty<Rectangle> selectRegionProperty()
    {
        return selectRegion;
    }

    SelectionGroup getSelectedSquares(){
        return selectedSquares;
    }

    void startSelectRegion(double x, double y) {
        startClick[0] = x;
        startClick[1] = y;
        selectRegion.set(new Rectangle(x,y,0,0));
    }

    void clearSelected() {
        selectedSquares.itemsProperty().clear();
    }

    void updateSelectRegion(double x, double y) {
        Rectangle selectRect = selectRegionProperty().getValue();
        double x1, x2, y1, y2, width, height;
        x1 = Math.min(x, startClick[0]);
        y1 = Math.min(y, startClick[1]);
        x2 = Math.max(x, startClick[0]);
        y2 = Math.max(y, startClick[1]);
        width = x2 - x1;
        height = y2 - y1;
        selectRect.setX(x1);
        selectRect.setY(y1);
        selectRect.setWidth(width);
        selectRect.setHeight(height);
    }
}
