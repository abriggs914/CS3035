package sample;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.*;

public class GraphView extends Pane {

    final boolean PRINT = false;
    Line workingEdge;

    public static final Color FILL_COLOR = Color.LIGHTBLUE;
    public static final Color SELECTED_COLOR = Color.ORANGE;

    public GraphView() {
        this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        this.workingEdge = null;
        Main.graphModel.vertexListProperty().addListener(new ListChangeListener<Vertex>() {
            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends Vertex> v) {
                if (PRINT) {System.out.println("vertexList UPDATED\t->\tdraw()");}
                draw();
            }
        });
    }

    boolean tryDrawingEdge(int x1, int y1, int x2, int y2) {
        workingEdge = drawEdge(x1, y1, x2, y2);
        Vertex a = Main.graphModel.getVertexAt(x1, y1);
        Vertex b = Main.graphModel.getVertexAt(x2, y2);
        // current position dragged to, isn't a vertex
//        System.out.println("b: " + b);
        if (b != null && a != b) {
            b.select();
            Main.graphModel.addEdge(x1, y1, x2, y2);
            return true;
        }
        return false;
    }

    Line drawEdge(int x1, int y1, int x2, int y2) {
        Line edgeLine = new Line(x1, y1, x2, y2);
        edgeLine.setStrokeWidth(3);
        edgeLine.setFill(Color.BLACK);
        edgeLine.setStroke(Color.BLACK);
//        if (b == null) {
//            workingEdge = edgeLine;
////            if (PRINT) {
//                System.out.println("Setting workingEdge: " + workingEdge);
//                System.out.println("Drawing line: (" + x1 + ", " + y1 + "), to (" + x2 + ", " + y2 + ")");
////            }
//        }
//        else {
////            System.out.println("RESETTING WORKINGEDGE");
//            workingEdge = null;
//        }
        return edgeLine;
//        this.getChildren().add(edgeLine);
//        layoutChildren();
    }

    public void resetWorkingEdge() {
        this.workingEdge = null;
    }

    /**
     * Overriding this method from Pane will allow draw to be called whenever necessary.
     * In this example, it will be first called when the View is displayed.
     */
    @Override
    public void layoutChildren() {
        draw();
    }

    private void draw() {
        if (PRINT) {System.out.println("\tv - DRAWING - v");}
        this.getChildren().clear();
//        Canvas canvas = new Canvas();
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setStroke(Color.BLACK);
        if (workingEdge != null) {
            int x1 = (int) workingEdge.getStartX();
            int y1 = (int) workingEdge.getStartY();
            int x2 = (int) workingEdge.getEndX();
            int y2 = (int) workingEdge.getEndY();
            this.getChildren().add(drawEdge(x1, y1, x2, y2));
        }
        if (PRINT) {System.out.println("Num edges: " + Main.graphModel.getEdges().size()); }
        Set<Map.Entry<String, Edge>> edgeEntries = Main.graphModel.getEdges().entrySet();
        for (Object o: edgeEntries) {
//            System.out.println("Drawing Edge: " + e);
            Map.Entry entry = (Map.Entry) o;
            Edge e = (Edge) entry.getValue();
            Point2D start = e.getStartPosition();
            Point2D end = e.getEndPosition();
            int x1 = (int) start.getX();
            int y1 = (int) start.getY();
            int x2 = (int) end.getX();
            int y2 = (int) end.getY();
            this.getChildren().add(drawEdge(x1, y1, x2, y2));
        }
        if (PRINT) {System.out.println("Num vertexes: " + Main.graphModel.vertexListProperty().size());}
        for (Vertex v : Main.graphModel.vertexListProperty()) {
            Circle c = new Circle(
                    v.getPosition().getX(),
                    v.getPosition().getY(),
                    v.getRadius()
            );
            if (v.getIsSelected()) {
//                c.setStrokeWidth(3);
                c.setFill(SELECTED_COLOR);
//                c.setStyle("-fx-border-style: solid inside;" +
//                        "-fx-border-width: 3;");
//                c.setStrokeType(StrokeType.OUTSIDE);
//                c.setStrokeLineJoin(StrokeLineJoin.ROUND);
//                c.setStrokeType(StrokeType.INSIDE);
            }
            else {
                c.setFill(FILL_COLOR);
                c.setStrokeWidth(1);
            }
            if (PRINT) {System.out.println("drawing circle: [" + v.getId() + "] " + c + "\n\tstrokeWidth: " + c.getStrokeWidth());}
            Label label = new Label(Integer.toString(v.getId()));
            label.setMinSize(40, 20);
            label.setFont(new Font("times", 18));
//            label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
            label.setTextAlignment(TextAlignment.CENTER);
            label.setAlignment(Pos.CENTER);
            StackPane stackPane = new StackPane();
            stackPane.setLayoutX(v.getPosition().getX());
            stackPane.setLayoutY(v.getPosition().getY());
            stackPane.getChildren().add(c);
            stackPane.getChildren().add(label);
            this.getChildren().add(stackPane);
        }
        if (PRINT) {System.out.println("\t^ - DRAWING - ^\n");}
    }
}
