package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.text.NumberFormat;

class Widget {

    private double width;
    private double height;
    private double layoutX;
    private double layoutY;
    private double minHeight;
    private double maxHeight;
    private double minWidth;
    private double maxWidth;
    private double prefWidth;
    private double prefHeight;
    private RowLayoutPane.Position position;

    Widget() {}

    double getWidth() {
        return width;
    }

    double getHeight() {
        return this.height;
    }

    double getMinWidth() {
        return minWidth;
    }

    double getMaxWidth() {
        return this.maxWidth;
    }

    double getPrefWidth() {
        return this.prefWidth;
    }

    void setWidth(double widthIn) {
        this.width = widthIn;
    }

    void setHeight(double heightIn) {
        this.height = heightIn;
    }

    void setMinSize(double newMinWidth, double newMinHeight) {
        this.minWidth = newMinWidth;
        this.minHeight = newMinHeight;
    }

    void setMaxSize(double newMaxWidth, double newMaxHeight) {
        this.maxWidth = newMaxWidth;
        this.maxHeight = newMaxHeight;
    }

    void setPrefSize(double newPrefWidth, double newPrefHeight) {
        this.prefWidth = newPrefWidth;
        this.prefHeight = newPrefHeight;
        if (width == 0 || height == 0) {
            this.width = newPrefWidth;
            this.height = newPrefHeight;
        }
    }

    void setPosition(RowLayoutPane.Position position) {
        this.position = position;
    }

    Object getPosition() {
        return this.position;
    }

    void setLayoutX(double layoutXIn) {
        this.layoutX = layoutXIn;
    }

    void setLayoutY(double layoutYIn) {
        this.layoutY = layoutYIn;
    }

    double getLayoutX() {
        return this.layoutX;
    }

    //draws a rectangle to indicate the position and size of the widget.
    // The rectangle is red if the widget is at its min width, orange
    // if between min and preferred width, green if at preferred width,
    // purple if between preferred and max width, and blue if at max width.
    // In addition, draw lines from corner to corner (see figures above)
    // to clearly show the size of the widget rectangle. Last, draw text
    // to show the min, pref, max, and actual width of the widget
    void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        if (width == minWidth) {
            gc.setFill(Color.RED);
        }
        else if (width > minWidth && width < prefWidth) {
            gc.setFill(Color.ORANGE);
        }
        else if (width == prefWidth) {
            gc.setFill(Color.GREEN);
        }
        else if (width > prefWidth && width < maxWidth) {
            gc.setFill(Color.PURPLE);
        }
        else {
            gc.setFill(Color.BLUE);
        }
        gc.setLineWidth(2);
        gc.fillRect(layoutX, layoutY, width, height);
        gc.strokeLine(layoutX, layoutY, layoutX + width, layoutY + height);
        gc.strokeLine(layoutX + width, layoutY, layoutX, layoutY + height);
        gc.setLineWidth(1);
        String string = "";
        string += "minWidth: " + stringifyDouble(minWidth) + "\n" + "prefWidth: " + stringifyDouble(prefWidth) + "\n" +
                  "maxWidth: " + stringifyDouble(maxWidth) + "\n" + "Position: " + position  + "\n" +
                  "width: " + stringifyDouble(width) + "\n";
        gc.strokeText(string, layoutX, layoutY + 20);
    }

    private String stringifyDouble(double val) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(val);
    }
}
