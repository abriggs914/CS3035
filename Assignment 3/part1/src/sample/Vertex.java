package sample;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class Vertex {

    private int id;
    private int radius;
    private int x;
    private int y;
    private boolean isSelected;

    public Vertex(int id, int x, int y, int vertexRadius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = vertexRadius;
        this.isSelected = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point2D getPosition() {
        return new Point2D(x, y);
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void select() {
        this.isSelected = true;
    }

    public void deselect() {
        this.isSelected = false;
    }

    public int getRadius() {
        return radius;
    }

    public int getId() {
        return id;
    }

    public boolean containsPoint(int x, int y) {
        Circle c = new Circle(x, y, radius);
        return c.contains(this.x, this.y);
    }

    public String toString() {
        return "id: " + id + ", x: " + x + ", y: " + y + ", radius: " + radius;
    }
}
