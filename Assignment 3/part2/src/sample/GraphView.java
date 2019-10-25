package sample;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.*;

public class GraphView extends Pane {

    private Line workingEdge;

    static final Color SELECTED_COLOR = Color.ORANGE;

    GraphView() {
        this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        this.workingEdge = null;
        Main.graphModel.vertexListProperty().addListener((ListChangeListener<Vertex>) v -> draw());
    }

    boolean tryDrawingEdge(int x1, int y1, int x2, int y2) {
        workingEdge = drawEdge(x1, y1, x2, y2);
        Vertex a = Main.graphModel.getVertexAt(x1, y1);
        Vertex b = Main.graphModel.getVertexAt(x2, y2);
        if (b != null && a != b) {
            b.select();
            b.setIsConnecting();
            Main.graphModel.addEdge(x1, y1, x2, y2);
            return true;
        }
        return false;
    }

    private Line drawEdge(int x1, int y1, int x2, int y2) {
        Line edgeLine = new Line(x1, y1, x2, y2);
        Vertex b = Main.graphModel.getVertexAt(x2, y2);
        if (b == null) {
            edgeLine.setStrokeWidth(3);
        }
        else {
            edgeLine.setStrokeWidth(1);
        }
        edgeLine.setFill(Color.BLACK);
        edgeLine.setStroke(Color.BLACK);
        return edgeLine;
    }

    void resetWorkingEdge() {
        this.workingEdge = null;
    }

    @Override
    public void layoutChildren() {
        draw();
    }

    private void draw() {
        this.getChildren().clear();
        if (workingEdge != null) {
            int x1 = (int) workingEdge.getStartX();
            int y1 = (int) workingEdge.getStartY();
            int x2 = (int) workingEdge.getEndX();
            int y2 = (int) workingEdge.getEndY();
            this.getChildren().add(drawEdge(x1, y1, x2, y2));
        }

        Set<Map.Entry<String, Edge>> edgeEntries = Main.graphModel.getEdges().entrySet();
        for (Object o: edgeEntries) {
            Map.Entry entry = (Map.Entry) o;
            Edge e = (Edge) entry.getValue();
            Point2D start = e.getStartPosition();
            Point2D end = e.getEndPosition();
            int x1 = (int) start.getX();
            int y1 = (int) start.getY();
            int x2 = (int) end.getX();
            int y2 = (int) end.getY();
            Vertex a = Main.graphModel.getVertexAt(x1, y1);
            Vertex b = Main.graphModel.getVertexAt(x2, y2);
            if (a != null && b != null) {
                this.getChildren().add(drawEdge(x1, y1, x2, y2));
            }
        }
        for (Vertex v : Main.graphModel.vertexListProperty()) {
            Circle c = new Circle(
                    v.getPosition().getX(),
                    v.getPosition().getY(),
                    v.getRadius()
            );
            if (v.getIsSelected()) {
                c.setFill(SELECTED_COLOR);
            }
            else {
                c.setFill(v.getColor());
            }
            if (v.isConnecting()) {
                c.setStroke(Color.BLACK);
                c.setStrokeWidth(3);
                c.setStrokeType(StrokeType.OUTSIDE);
                c.setStrokeLineJoin(StrokeLineJoin.ROUND);
            }

            Label label = new Label(Integer.toString(v.getId()));
            label.setMinSize(40, 20);
            label.setFont(new Font("times", 18));
            label.setStyle("-fx-font-weight: bold");
            label.setTextFill(Color.WHITE);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setAlignment(Pos.CENTER);

            StackPane stackPane = new StackPane();
            stackPane.setLayoutX(v.getPosition().getX());
            stackPane.setLayoutY(v.getPosition().getY());
            stackPane.getChildren().add(c);
            stackPane.getChildren().add(label);
            stackPane.toBack();

            this.getChildren().add(stackPane);
        }
    }
}
