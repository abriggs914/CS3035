package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Widget {

    private double width;
    private double height;
    private double layoutHeight;
    private double minHeight;
    private double maxHeight;
    private double minWidth;
    private double maxWidth;
    private double prefWidth;
    private double prefHeight;
    private RowLayoutPane.Position position;
    private ArrayList<Widget> children;

    Widget() {
//        double minW, double maxW, double minH, double maxH, double prefW, double prefH
//        this.minWidth = minW;
//        this.maxWidth = maxW;
//        this.minHeight = minH;
//        this.maxHeight = maxH;
//        this.prefWidth = prefW;
//        this.prefHeight = prefH;
//        this.width = prefW;
//        this.height = prefH;
        this.children = new ArrayList<>();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return this.height;
    }

    public double getMinWidth() {
        return minWidth;
    }

    public double getMaxWidth() {
        return this.maxWidth;
    }

    public double getMaxHeight() {
        return this.maxHeight;
    }

    public double getMinHeight() {
        return minHeight;
    }

    public double getPrefWidth() {
        return this.prefWidth;
    }

    public double getPrefHeight() {
        return this.prefHeight;
    }

    public void addChild(Widget w) {
        children.add(w);
    }

    public ArrayList<Widget> getChildren() {
        return this.children;
    }

    public void setMinSize(double newMinWidth, double newMinHeight) {
        this.minWidth = newMinWidth;
        this.minHeight = newMinHeight;
    }

    public void setMaxSize(double newMaxWidth, double newMaxHeight) {
        this.maxWidth = newMaxWidth;
        this.maxHeight = newMaxHeight;
    }

    public void setPrefSize(double newPrefWidth, double newPrefHeight) {
        this.prefWidth = newPrefWidth;
        this.prefHeight = newPrefHeight;
        if (width == 0 || height == 0) {
            this.width = newPrefWidth;
            this.height = newPrefHeight;
        }
    }

    public Dimension getMinSize() {
        int minWidth = (int) this.getMinWidth();
        int minHeight = (int) this.getMinHeight();
        for(Widget w : this.getChildren()) {
            Dimension childSize = w.getMinSize();
            minWidth += childSize.width;
            if (minHeight < childSize.height) {
                minHeight = childSize.height;
            }
        }
        return new Dimension(minWidth, minHeight);
    }

    public Dimension getDesiredSize() {
        int prefWidth = (int) this.getPrefWidth();
        int prefHeight = (int) this.getPrefHeight();
        for(Widget w : this.getChildren()) {
            Dimension childSize = w.getDesiredSize();
            prefWidth += childSize.width;
            if (prefHeight < childSize.height) {
                prefHeight = childSize.height;
            }
        }
        return new Dimension(prefWidth, prefHeight);
    }

    public Dimension getMaxSize() {
        int maxWidth = (int) this.getMaxWidth();
        int maxHeight = (int) this.getMaxHeight();
        for(Widget w : this.getChildren()) {
            Dimension childSize = w.getMaxSize();
            maxWidth += childSize.width;
            if (maxHeight < childSize.height) {
                maxHeight = childSize.height;
            }
        }
        return new Dimension(maxWidth, maxHeight);
    }

    public void setLayoutHeight(double layoutHeight) {
        this.layoutHeight = layoutHeight;
    }

    public void setPosition(RowLayoutPane.Position position) {
        this.position = position;
    }

    public Object getPosition() {
        return this.position;
    }

    public double getLayoutHeight() {
        return this.layoutHeight;
    }

    //draws a rectangle to indicate the position and size of the widget.
    // The rectangle is red if the widget is at its min width, orange
    // if between min and preferred width, green if at preferred width,
    // purple if between preferred and max width, and blue if at max width.
    // In addition, draw lines from corner to corner (see figures above)
    // to clearly show the size of the widget rectangle. Last, draw text
    // to show the min, pref, max, and actual width of the widget
    void draw(GraphicsContext gc) {
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
        gc.fillRect(0,layoutHeight, width, height);
        gc.strokeLine(0, layoutHeight, width, height);
        gc.strokeLine(width, layoutHeight, 0, height);
        String string = "";
        string += "minWidth: " + stringifyDouble(minWidth) + "\n" + "prefWidth: " + stringifyDouble(prefWidth) + "\n" +
                  "maxWidth: " + stringifyDouble(maxWidth) + "\n" + "width: " + stringifyDouble(width) + "\n";
        gc.strokeText(string,0,layoutHeight);
    }

    private String stringifyDouble(double val) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(val);
    }
}
