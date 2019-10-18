package sample;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class Vertex {

    private int id;
    private int radius;
    private int x;
    private int y;
    private boolean isSelected;
    private boolean isConnecting;

    Vertex(int id, int x, int y, int vertexRadius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = vertexRadius;
        this.isSelected = false;
        this.isConnecting = false;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    Point2D getPosition() {
        return new Point2D(x, y);
    }

    boolean getIsSelected() {
        return isSelected;
    }

    void select() {
        this.isSelected = true;
    }

    void deselect() {
        this.isSelected = false;
    }

    int getRadius() {
        return radius;
    }

    int getId() {
        return id;
    }

    boolean containsPoint(int x, int y) {
        Circle c = new Circle(x, y, radius);
        return c.contains(this.x, this.y);
    }

    void setIsConnecting() {
        this.isConnecting = true;
    }

    void setNotConnecting() {
        this.isConnecting = false;
    }

    boolean isConnecting() {
        return isConnecting;
    }

    public String toString() {
        return "id: " + id + ", x: " + x + ", y: " + y + ", radius: " + radius;
    }
}
