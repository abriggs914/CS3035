package sample;

import java.util.ArrayList;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Shape;

class Model {

    private SimpleListProperty<Shape> shapesListProperty;
    private SimpleListProperty<Shape> cutShapesListProperty;

    private int squareSideLength;
    private Shapes currShape;

    Model(int squareSideLength) {
        ArrayList<Shape> list = new ArrayList<>();
        ObservableList<Shape> observableList = FXCollections.observableArrayList(list);
        ObservableList<Shape> observableListCut = FXCollections.observableArrayList(list);
        shapesListProperty = new SimpleListProperty<>(observableList);
        cutShapesListProperty = new SimpleListProperty<>(observableListCut);
        this.currShape = Shapes.SQUARE;
        this.squareSideLength = squareSideLength;
    }

    SimpleListProperty<Shape> shapeListProperty(){
        return shapesListProperty;
    }

    private int getSquareSideLength() {return squareSideLength;}

    void addSquare(double x, double y) {
        int squareX = (int) x - Main.model.getSquareSideLength()/2;
        int squareY = (int) y - Main.model.getSquareSideLength()/2;

        Shape shape = new DrawableShapes().newShape(squareX, squareY, squareSideLength, currShape);
        shapesListProperty.add(shape);
    }

    void shapeIsSquare() {
        this.currShape = Shapes.SQUARE;
    }

    void shapeIsCircle() {
        this.currShape = Shapes.CIRCLE;
    }

    void shapeIsTriangle() {
        this.currShape = Shapes.TRIANGLE;
    }

    ObservableList<Shape> getCutShapesListProperty() {
        return cutShapesListProperty.get();
    }

    SimpleListProperty<Shape> cutShapesListPropertyProperty() {
        return cutShapesListProperty;
    }

    void resetCutList() {
        cutShapesListProperty.clear();
    }
}
