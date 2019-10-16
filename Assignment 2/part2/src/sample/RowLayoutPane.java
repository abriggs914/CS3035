package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.text.NumberFormat;
import java.util.ArrayList;

public class RowLayoutPane extends Pane {

    public enum Position {TOP, CENTER, FILL}

    private static ArrayList<Widget> widgetsList;
    private static ArrayList<RowCell> rowCells;
    private static Canvas canvas;
    private double width;
    private double height;

    private double minWidth;
    private double maxWidth;
    private double prefWidth;

    private class RowCell {

        Widget widget;

        RowCell() {}

        void setWidget(Widget w, Position p) {
            w.setPosition(p);
            this.widget = w;
        }

        void draw(GraphicsContext gc) {
            gc.setLineWidth(2);
            gc.setStroke(Color.WHITE);
            gc.strokeOval(widget.getLayoutX() + 2, 0, widget.getWidth() - 2, height);
            gc.setLineWidth(1);
            String string = "";
            gc.setStroke(Color.BLACK);
            string += "minWidth: " + stringifyDouble(minWidth) + "\n" + "prefWidth: " + stringifyDouble(prefWidth) + "\n" +
                    "maxWidth: " + stringifyDouble(maxWidth) + "\n" + "width: " + stringifyDouble(width) + "\n";
            gc.strokeText(string, 0, 20);
            widget.draw(gc);
        }

        void positionWidgetVertical(Position p) {
            //  sets the vertical position and height of the widget, given its positioning constraint
            if (p.equals(Position.TOP)) {
                widget.setLayoutY(0);
            }
            else if (p.equals(Position.CENTER)) {
                widget.setLayoutY((canvas.getHeight() / 2) - widget.getHeight() / 2);
            }
            else {
                widget.setLayoutY(0);
                widget.setMinSize(widget.getMinWidth(), canvas.getHeight());
                widget.setPrefSize(widget.getPrefWidth(), canvas.getHeight());
                widget.setMaxSize(widget.getMaxWidth(), canvas.getHeight());
                widget.setHeight(canvas.getHeight());
            }
            widget.setPosition(p);
        }
    }

    RowLayoutPane(double width, double height) {
        widgetsList = new ArrayList<>();
        rowCells = new ArrayList<>();
        canvas = new Canvas(width, height);
        this.height = height;
        setWidth(width);
    }

    Canvas getCanvas() {
        layoutChildren();
        drawRow();
        return canvas;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
        canvas.resize(width, height);
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
        canvas.resize(width, height);
    }

    void addWidget(Widget w, Position p) {
        RowCell rowCell = new RowCell();
        rowCell.setWidget(w, p);
        setVerticalPosition(w, p);
        rowCell.positionWidgetVertical(p);
        widgetsList.add(w);
        rowCells.add(rowCell);
    }

    private void setVerticalPosition(Widget w, Position position) {
        // sets the given widget’s vertical position as TOP, CENTER, or FILL
        w.setPosition(position);
    }

    private void drawRow() {
        //draws the current layout to the canvas. This method should also draw text to show the min, pref, max, and actual
        //width of the RowLayoutPane (note: the first three are based on the widths of the child widgets in the RowLayoutPane)
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DIMGREY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int currX = 0;
        for (RowCell r : rowCells) {
            r.widget.setLayoutX(currX);
            currX += r.widget.getWidth();
            r.draw(gc);
        }
    }

    private double[] getWidgetWidths() {
        double minWidth = 0;
        double maxWidth = 0;
        double prefWidth = 0;
        for (Widget w : widgetsList) {
            minWidth += w.getMinWidth();
            maxWidth += w.getMaxWidth();
            prefWidth += w.getPrefWidth();
        }
        return new double[] {minWidth, maxWidth, prefWidth};
    }

    @Override
    public void layoutChildren() {
        setWidth(canvas.getWidth());
        double[] widgetWidths = getWidgetWidths();
        minWidth = widgetWidths[0];
        maxWidth = widgetWidths[1];
        prefWidth = widgetWidths[2];
        double width = canvas.getWidth();
        double leftOverWidth = width;
        int r = 0;
        boolean greedy = false;
        while(r < rowCells.size() && leftOverWidth > 0) {
            RowCell cell = rowCells.get(r);
            Widget w = cell.widget;
            r++;
            if (width < minWidth) {
                w.setWidth(w.getMinWidth());
            }
            else {
                if (greedy) {
                    double spacePerWidget = leftOverWidth / widgetsList.size();
                    w.setWidth(Math.min(w.getMaxWidth(), w.getWidth() + spacePerWidget));
                } else {
                    if (leftOverWidth < w.getMinWidth()) {
                        w.setWidth(w.getMinWidth());
                        leftOverWidth -= w.getMinWidth();
                    }
                    if (leftOverWidth > 0 && leftOverWidth < w.getPrefWidth()) {
                        w.setWidth(leftOverWidth);
                        leftOverWidth = 0;
                    }
                    if (leftOverWidth >= w.getPrefWidth()) {
                        w.setWidth(w.getPrefWidth());
                        leftOverWidth -= w.getPrefWidth();
                    }
                }
            }

            if (r == rowCells.size() && leftOverWidth > 0) {
                if (greedy) {
                    break;
                }
                r = 0;
                greedy = true;
            }
            if (w.getPosition() == Position.FILL) {
                w.setHeight(canvas.getHeight());
            }
        }
    }

    private String stringifyDouble(double val) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(val);
    }
}
