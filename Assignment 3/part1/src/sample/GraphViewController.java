package sample;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GraphViewController {

    Vertex movingVertex;
    Vertex connectingVertex;
    Vertex connectedVertex;
    boolean shiftIsPressed;
    boolean dragOver;

    public GraphViewController() {
        Main.graphView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int xPos = (int) e.getX();
                int yPos = (int) e.getY();
                if (shiftIsPressed && !dragOver && connectingVertex != null) {
                    Point2D vPos = connectingVertex.getPosition();
                    connectingVertex.select();
                    connectedVertex = Main.graphModel.getVertexAt(xPos, yPos);
                    if (Main.graphView.tryDrawingEdge((int) vPos.getX(), (int) vPos.getY(), xPos, yPos)) {
                        System.out.println("drag over");
                        dragOver = true;
                        return;
                    }
//                    System.out.println("dragging");
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
                System.out.println("\tMOUSE PRESSED");
                int xPos = (int) e.getX();
                int yPos = (int) e.getY();
                Vertex selected = Main.graphModel.getVertexAt(xPos, yPos);
                if (e.isSecondaryButtonDown() && !e.isPrimaryButtonDown()) {
                    System.out.println("\t\tRight click\t->\tdelete vertex");
                    Main.graphModel.deleteVertexAt(xPos, yPos);
                }
                // add edge
                else if (e.isShiftDown()) {
                    dragOver = false;
                    connectingVertex = selected;
                    shiftIsPressed = true;
                    System.out.println("\t\tshift pressed\t->\tadd edge\n\t\tConnectingVertex: " + connectingVertex);
                }
                // move vertex
                else if (selected != null) {
//                    selected.select();
                    movingVertex = selected;
                    System.out.println("\t\tDrag click\t->\tmove vertex\n\t\tMovingVertex: " + movingVertex);
                }
                // add node
                else if (e.getButton() == MouseButton.PRIMARY){
                    System.out.println("\t\tLeft click\t->\tadd vertex");
                    Main.graphModel.addVertex(xPos, yPos);
                }
            }
        });

        Main.graphView.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("\tMOUSE RELEASED");
                if (movingVertex != null) {
                    movingVertex.deselect();
                    Main.graphView.layoutChildren();
                }
                if (connectingVertex != null) {
                    connectingVertex.deselect();
                    Main.graphView.layoutChildren();
                    if (connectedVertex != null) {
                        connectedVertex.deselect();
                    }
                }
                movingVertex = null;
                connectingVertex = null;
                shiftIsPressed = false;
                dragOver = true;
                Main.graphView.resetWorkingEdge();
            }
        });
    }
}
