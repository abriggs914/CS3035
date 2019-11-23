package sample;

import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class DrawableShapes {

    DrawableShapes() {}

    Shape newShape(int x, int y, int sideLength, Shapes currShape) {
        Shape shape = null;
        switch(currShape) {
            case SQUARE     :   int[] sqBnds = generateSquareBounds(x, y, sideLength);
                                shape = new Rectangle(sqBnds[0], sqBnds[1], sqBnds[2], sqBnds[3]);
                                break;
            case CIRCLE     :   int[] crBnds = generateCircleBounds(x, y, sideLength);
                                shape = new Circle(crBnds[0], crBnds[1], crBnds[2]);
                                break;
            case TRIANGLE   :   ArrayList<Double> trBnds = generateTriangleBounds(x, y, sideLength);
                                Polygon triangle = new Polygon();
                                triangle.getPoints().addAll(trBnds);
                                shape = triangle;
                                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currShape);
        }
        return shape;
    }

    private int[] generateSquareBounds(int x, int y, int sideLength) {
        int width, height;
        width = sideLength;
        height = sideLength;
        return new int[]{x, y, width, height};
    }

    private int[] generateCircleBounds(int x, int y, int sideLength) {
        int xPos, yPos, radius;
        xPos = x + (sideLength / 2);
        yPos = y + (sideLength / 2);
        radius = sideLength / 2;
        return new int[]{xPos, yPos, radius};
    }

    private ArrayList<Double> generateTriangleBounds(int x, int y, int sideLength) {
        ArrayList<Double> res = new ArrayList<>();
        double equalSideLength = sideLength * (2 / Math.pow(3, 0.5));
        y += sideLength / 2;
        x += sideLength / 2;

        res.add((double)x);                     // x1
        res.add(y - (double)(sideLength / 2));  // y1

        res.add(x - (equalSideLength / 2));     // x2
        res.add(y + (double)(sideLength / 2));  // y2

        res.add(x + (equalSideLength / 2));     // x3
        res.add(y + (double)(sideLength / 2));  // y3

        return res;
    }

    public int[] calculateCentroid(Shape s) {
        int x, y;
        if (s.getClass() == Polygon.class) {
            x = 0;
            y = 0;
            Polygon p = (Polygon)s;
            ObservableList<Double> points = p.getPoints();
            for (int i = 0; i < points.size(); i++) {
                double point = points.get(i);
                if (i % 2 == 0) {
                    x += point;
                }
                if (i % 2 == 1) {
                    y += point;
                }
            }
            x /= 3;
            y /= 3;
        }
        else if (s.getClass() == Rectangle.class) {
            Rectangle sq = (Rectangle) s;
            x = (int)sq.getX();
            y = (int)sq.getY();
        }
        else if (s.getClass() == Circle.class) {
            Circle c = (Circle) s;
            x = (int)c.getCenterX();
            y = (int)c.getCenterY();
        }
        else {
            x = -1;
            y = -1;
        }
        return new int[] {x, y};
    }
}
