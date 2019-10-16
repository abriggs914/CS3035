package sample;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GraphViewController {

    Vertex movingVertex;
    Vertex connectingVertex;
    boolean shiftIsPressed;
    boolean dragOver;

    public GraphViewController() {
        Main.graphView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int xPos = (int) e.getX();
                int yPos = (int) e.getY();
                if (shiftIsPressed && !dragOver) {
                    Point2D vPos = connectingVertex.getPosition();
                    connectingVertex.select();
                    if (Main.graphView.tryDrawingEdge((int) vPos.getX(), (int) vPos.getY(), xPos, yPos)) {
                        System.out.println("drag over");
                        dragOver = true;
                        return;
                    }
                    System.out.println("dragging");
                }
                else {
                    if (movingVertex != null) {
                        movingVertex.setX(xPos);
                        movingVertex.setY(yPos);
                        movingVertex.select();
                    }
                }
                Main.graphView.layoutChildren();
            }
        });

        Main.graphView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int xPos = (int) e.getX();
                int yPos = (int) e.getY();
                Vertex selected = Main.graphModel.getVertexAt(xPos, yPos);
                if (e.isSecondaryButtonDown() && !e.isPrimaryButtonDown()) {
                    Main.graphModel.deleteVertexAt(xPos, yPos);
                }
                // add edge
                else if (e.isShiftDown()) {
                    dragOver = false;
                    System.out.println("shift is pressed");
                    connectingVertex = selected;
                    shiftIsPressed = true;
                }
                // move vertex
                else if (selected != null) {
//                    selected.select();
                    movingVertex = selected;
                }
                // add node
                else if (e.getButton() == MouseButton.PRIMARY){
                    Main.graphModel.addVertex(xPos, yPos);
                }
            }
        });

        Main.graphView.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Main.graphView.resetWorkingEdge();
                if (movingVertex != null) {
                    movingVertex.deselect();
                    Main.graphView.layoutChildren();
                }
                if (connectingVertex != null) {
                    connectingVertex.deselect();
                    Main.graphView.layoutChildren();
                }
                movingVertex = null;
                connectingVertex = null;
                shiftIsPressed = false;
                dragOver = true;
            }
        });
    }
}
