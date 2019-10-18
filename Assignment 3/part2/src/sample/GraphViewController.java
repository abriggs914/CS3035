package sample;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;

public class GraphViewController {

    private Vertex movingVertex;
    private Vertex connectingVertex;
    private Vertex connectedVertex;
    private boolean shiftIsPressed;
    private boolean dragOver;

    GraphViewController() {
        Main.graphView.setOnMouseDragged(e -> {
            int xPos = (int) e.getX();
            int yPos = (int) e.getY();
            if (shiftIsPressed && !dragOver && connectingVertex != null) {
                Point2D vPos = connectingVertex.getPosition();
                connectingVertex.select();
                connectingVertex.setIsConnecting();
                connectedVertex = Main.graphModel.getVertexAt(xPos, yPos);
                if (Main.graphView.tryDrawingEdge((int) vPos.getX(), (int) vPos.getY(), xPos, yPos)) {
                    dragOver = true;
                    return;
                }
            }
            else {
                if (movingVertex != null) {
                    movingVertex.setX(xPos);
                    movingVertex.setY(yPos);
                    movingVertex.select();
                    Main.graphModel.updateEdgePositions(movingVertex);
                }
            }
            Main.graphView.layoutChildren();
        });
        Main.graphView.setOnMousePressed(e -> {
//            System.out.println("\tMOUSE PRESSED");
            int xPos = (int) e.getX();
            int yPos = (int) e.getY();
            Vertex selected = Main.graphModel.getVertexAt(xPos, yPos);
            if (e.isSecondaryButtonDown() && !e.isPrimaryButtonDown()) {
//                System.out.println("\t\tRight click\t->\tdelete vertex");
                Main.graphModel.deleteVertexAt(xPos, yPos);
            }
            // add edge
            else if (e.isShiftDown()) {
                dragOver = false;
                connectingVertex = selected;
                shiftIsPressed = true;
//                System.out.println("\t\tshift pressed\t->\tadd edge\n\t\tConnectingVertex: " + connectingVertex);
            }
            // move vertex
            else if (selected != null) {
                movingVertex = selected;
//                System.out.println("\t\tDrag click\t->\tmove vertex\n\t\tMovingVertex: " + movingVertex);
            }
            // add node
            else if (e.getButton() == MouseButton.PRIMARY){
//                System.out.println("\t\tLeft click\t->\tadd vertex");
                Main.graphModel.addVertex(xPos, yPos);
            }
        });

        Main.graphView.setOnMouseReleased(e -> {
//            System.out.println("\tMOUSE RELEASED");
            if (movingVertex != null) {
                movingVertex.deselect();
                Main.graphView.layoutChildren();
            }
            if (connectingVertex != null) {
                connectingVertex.deselect();
                connectingVertex.setNotConnecting();
                Main.graphView.layoutChildren();
                if (connectedVertex != null) {
                    connectedVertex.deselect();
                    connectedVertex.setNotConnecting();
                }
            }
            movingVertex = null;
            connectingVertex = null;
            shiftIsPressed = false;
            dragOver = true;
            Main.graphView.resetWorkingEdge();
        });
    }
}
