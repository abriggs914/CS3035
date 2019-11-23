package sample;

import java.util.ArrayList;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.BoundingBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

class SelectionGroup {
    private SimpleListProperty<Shape> items;

    SelectionGroup() {
        ArrayList<Shape> list = new ArrayList<>();
        ObservableList<Shape> observableList = FXCollections.observableArrayList(list);
        items = new SimpleListProperty<>(observableList);

        itemsProperty().addListener((ListChangeListener<Shape>) c -> calculateBoundingBox());
    }

    SimpleListProperty<Shape> itemsProperty()
    {
        return items;
    }

    private void calculateBoundingBox() {
        double 	minX = Double.MAX_VALUE, minY = Double.MAX_VALUE,
                maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
        for (Shape r : itemsProperty()) {
            double x, y, width, height;
            x = r.getLayoutX();
            y = r.getLayoutY();
            System.out.println("shape: " + r + ", shape.class: " + r.getClass());
            if (r.getClass() == Rectangle.class) {
                Rectangle s = (Rectangle) r;
                width = s.getWidth();
                height = s.getHeight();
            }
            else if (r.getClass() == Circle.class) {
                Circle s = (Circle) r;
                width = s.getRadius() * 2;
                height = s.getRadius() * 2;
            }
            else if (r.getClass() == Polygon.class) {
                Polygon s = (Polygon) r;
                width = s.getPoints().get(4) - s.getPoints().get(2);
                height = s.getPoints().get(5) - s.getPoints().get(3);
            }
            else {
                width = 0;
                height = 0;
            }
            if (x < minX) {
                minX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (x + width > maxX) {
                maxX = x + width;
            }
            if (y + height > maxY) {
                maxY = y + height;
            }
        }
        System.out.println("minX: " + minX + ", maxX: " + maxX + ", minY: " + minY + ", maxY: " + maxY);
        BoundingBox bounds = new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }
}
