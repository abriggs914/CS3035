package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * CS3503 Assignment 2 - part 1
 * Oct. 7 / 2019
 * Avery Briggs
 * 3471065
 *
 * Generates a Canvas with a blue background
 * and a white oval drawn to the given dimensions.
 * */

class CenterCanvas extends Region {

    private Canvas centerCanvas;

    CenterCanvas(double height, double width) {
        genCenterCanvas(height, width);
    }

    Canvas getCenterCanvas() {
        return this.centerCanvas;
    }

    private void genCenterCanvas(double height, double width) {
        Canvas background = new Canvas(width, height);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, width, height);
        gc.setStroke(Color.WHITE);
        gc.strokeOval(0, 0, width, height);
        this.centerCanvas = background;
    }
}
