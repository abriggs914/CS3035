package sample;

import javafx.geometry.Point2D;

public class Edge {

    private Point2D startPosition;
    private Point2D endPosition;
    private Vertex vertexA;
    private Vertex vertexB;

    Edge(Vertex a, Vertex b, int x1, int y1, int x2, int y2) {
        this.startPosition = new Point2D(x1, y1);
        this.endPosition = new Point2D(x2, y2);
        this.vertexA = a;
        this.vertexB = b;
    }

    void setStartPosition(Point2D startPosition) {
        this.startPosition = startPosition;
    }

    void setEndPosition(Point2D endPosition) {
        this.endPosition = endPosition;
    }

    Point2D getStartPosition() {
        return startPosition;
    }

    Point2D getEndPosition() {
        return endPosition;
    }

    public Vertex getVertexA() {
        return vertexA;
    }

    public Vertex getVertexB() {
        return vertexB;
    }

    public String toString() {
        return "[a -> b]: [ " + vertexA + " -> " + vertexB + " ], from: ["  + startPosition + "] to [" + endPosition+ "]";
    }
}
