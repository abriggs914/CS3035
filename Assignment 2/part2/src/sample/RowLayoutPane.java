package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RowLayoutPane extends Pane {

    public static enum Position {TOP, CENTER, FILL};

    static ArrayList<Widget> widgetsList;
    static ArrayList<RowCell> rowCells;
    static Canvas canvas;

    RowLayoutPane(double width, double height) {
        widgetsList = new ArrayList<>();
        canvas = new Canvas(width, height);
    }

    Canvas getCanvas() {
        drawRow();
        return canvas;
    }

    static ArrayList<Widget> getWidgetsList() {
        return widgetsList;
    }

    public void addWidget(Widget w) {
        RowCell rowCell = new RowCell();
        rowCell.setWidget(w);
        rowCell.positionWidgetVertical();
        widgetsList.add(w);
    }

    public void setVerticalPosition(Widget w, Position position) {
        // sets the given widget’s vertical position as TOP, CENTER, or FILL
        w.setPosition(position);
    }

    public void drawRow() {
        //draws the current layout to the canvas. This method should also draw text to show the min, pref, max, and actual
        //width of the RowLayoutPane (note: the first three are based on the widths of the child widgets in the RowLayoutPane)
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (Widget w : widgetsList) {
            setVerticalPosition(w, genRandomPosition());
            w.draw(gc);
        }
    }

    public Position genRandomPosition() {
        Random rnd = new Random();
        int rndNum = rnd.nextInt(3);
        switch (rndNum) {
            case 0 : return Position.TOP;
            case 1 : return Position.CENTER;
            case 2 : return Position.FILL;
            default: return Position.FILL;
        }
    }

    private class RowCell {

        Widget widget;

        RowCell() {}

        public void setWidget(Widget w) {
            this.widget = w;
        }

        public void draw(GraphicsContext gc) {
            gc.setFill(Color.WHITE);
            gc.strokeOval(0, widget.getLayoutHeight(), widget.getWidth(), widget.getHeight());
            widget.draw(gc);
        }

        public void positionWidgetVertical() {
            //  sets the vertical position and height of the widget, given its positioning constraint
            if (widget.getPosition().equals("TOP")) {
                widget.setLayoutHeight(0);
            }
            else if (widget.getPosition().equals("CENTER")) {
                widget.setLayoutHeight(widget.getHeight() / 2);
            }
            else {
                widget.setLayoutHeight(0);
            }

        }
    }

    @Override
    public void layoutChildren() {
        for(Widget w : widgetsList) {
            Rectangle rect = new Rectangle();
            // fill rect
            doLayout(w, rect);
        }
    }

        public void doLayout(Widget w, Rectangle newBounds) {
            Dimension min = w.getMinSize();
            Dimension desired = w.getDesiredSize();
            Dimension max = w.getMaxSize();

            if (min.width >= newBounds.getWidth()) {
                // give all children their minimum and let them be clipped
                int childLeft = newBounds.;
                foreach(child widget C) {
                    Rectangle childBounds = new Rectangle();
                    childBounds.top = newBounds.top;
                    childBounds.height = newBounds.height;
                    childBounds.left = childLeft;
                    childBounds.width = C.getMinSize().width;
                    childLeft += childBounds.width;
                    C.dolayout(childBounds);
                }
            }
            else if (desired.width >= newBounds.width) {
                //give min to all and porportional on what is available for desired
                int desiredMargin = desired.width - min.width;
                float fraction = (float) (newBounds.width - min.width) / desiredMargin;
                int childLeft = newBounds.left;
                foreach(child widget C) {
                    Rectangle childBounds = new Rectangle();
                    childBounds.top = newBounds.top;
                    childBounds.height = newBounds.height;
                    childBounds.left = childLeft;
                    int minWidth = C.getMinSize().width;
                    int desWidth = C.getDesiredSize().width;
                    childBounds.width = Math.round(minWidth + (desWidth - minWidth) * fraction);
                    childLeft += childBounds.width;
                    C.dolayout(childBounds);
                }
            }
            else {
                //allocate what remains based on maximum widths
                int maxMargin = max.width - desired.width;
                float fraction = (float) (newBounds.width - desired.width) / maxMargin;
                int childLeft = newBounds.left;
                foreach(child widget C) {
                    // similar code to previous case
                }
            }
        }

}
