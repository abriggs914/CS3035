package sample;

import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

class GraphViewController {

    private Vertex movingVertex;
    private Vertex connectingVertex;
    private Vertex connectedVertex;
    private boolean shiftIsPressed;
    private boolean dragOver;

    GraphViewController() {
        Main.graphView.addEventHandler(MouseEvent.ANY, new MouseHandler());
//        Main.getScene().setOnMousePressed(event -> {
//            System.out.println("Scene clicked");
//        });
    }

    public class MouseHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

            if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
//                System.out.println("MOUSE PRESSED");
                int xPos = (int) e.getX();
                int yPos = (int) e.getY();
                Vertex selected = Main.graphModel.getVertexAt(xPos, yPos);
                if (e.isSecondaryButtonDown() && !e.isPrimaryButtonDown()) {
                    if (!Main.interactionModel.isCreatingVertices()) {
//                        System.out.println("\t\tRight click\t->\tdelete vertex");
                        Main.graphModel.deleteVertexAt(xPos, yPos);
                    }
                }
                // add edge
                else if (e.isShiftDown()) {
                    if (Main.interactionModel.isCreatingVertices()) {
                        dragOver = false;
                        connectingVertex = selected;
                        shiftIsPressed = true;
//                        System.out.println("\t\tshift pressed\t->\tadd edge\n\t\tConnectingVertex: " + connectingVertex);
                    }
                }
                // move vertex
                else if (selected != null) {
                    if (Main.interactionModel.isCreatingVertices()) {
                        movingVertex = selected;
                        movingVertex.select();
//                        System.out.println("\t\tDrag click\t->\tmove vertex\n\t\tMovingVertex: " + movingVertex);
                        if (!e.isDragDetect()) {
                            if (e.getTarget().getClass() == LabeledText.class) {
                                LabeledText txt = (LabeledText) e.getTarget();
                                txt.setText("A");
                            }
                            else if (e.getTarget().getClass() == Circle.class) {
                                Circle c = (Circle) e.getTarget();
                                c.setFill(GraphView.SELECTED_COLOR);
                            }
                            else if (e.getTarget().getClass() == Label.class) {
                                Label txt = (Label) e.getTarget();
                                txt.setText("A"); // not sure why this works and the below didn't..
//                                txt.setText(Integer.toString(movingVertex.getId()));
                            }
                        }
                    }
                }
                // add node
                else if (e.getButton() == MouseButton.PRIMARY){
                    if (Main.interactionModel.isCreatingVertices()) {
//                    System.out.println("\t\tLeft click\t->\tadd vertex");
                        Main.graphModel.addVertex(xPos, yPos);
                    }
                }
                if (selected != null && Main.interactionModel.isCreatingVertices()) {
                    selected.select();
                }
            }

            if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
//                System.out.println("MOUSE RELEASED");
                if (movingVertex != null) {
                    movingVertex.deselect();
                }
                if (connectingVertex != null) {
                    connectingVertex.deselect();
                    connectingVertex.setNotConnecting();
                    if (connectedVertex != null) {
                        connectedVertex.deselect();
                        connectedVertex.setNotConnecting();
                    }
                }

                int x = (int) e.getX();
                int y = (int) e.getY();
                Vertex hoveringOver = Main.graphModel.getVertexAt(x, y);
                if (hoveringOver != null) {
                    hoveringOver.deselect();
                }

                movingVertex = null;
                connectingVertex = null;
                shiftIsPressed = false;
                dragOver = true;
                Main.graphView.resetWorkingEdge();
                Main.graphView.layoutChildren();
            }

            if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
//                System.out.println("MOUSE DRAGGED");
                if (Main.interactionModel.isCreatingVertices()) {
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
                    } else {
                        if (movingVertex != null) {
                            int horizontalSpace = (int) (Main.getScene().getWidth() - movingVertex.getRadius());
                            int verticalSpace = (int) ((Main.getScene().getHeight() - Main.interactionModel.getHeight())
                                                        - movingVertex.getRadius());
                            xPos = Math.min(horizontalSpace, Math.max(movingVertex.getRadius(), xPos));
                            yPos = Math.min(verticalSpace, Math.max(movingVertex.getRadius(), yPos));
                            movingVertex.setX(xPos);
                            movingVertex.setY(yPos);
                            movingVertex.select();
                            Main.graphModel.updateEdgePositions(movingVertex);
                        }
                    }
                    Main.graphView.layoutChildren();
                }
            }

            if (e.getEventType() == MouseEvent.MOUSE_MOVED) {
                if (!Main.interactionModel.isCreatingVertices()) {
                    int x = (int) e.getX();
                    int y = (int) e.getY();
                    Vertex v = Main.graphModel.getVertexAt(x, y);
                    if (v != null) {
                        Main.getScene().setCursor(Cursor.CROSSHAIR);
                    }
                    else {
                        Main.getScene().setCursor(Cursor.DEFAULT);
                    }
                }
            }
        }
    }
}
