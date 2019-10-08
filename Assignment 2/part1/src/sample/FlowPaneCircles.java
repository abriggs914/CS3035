package sample;

import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * CS3503 Assignment 2 - part 1
 * Oct. 7 / 2019
 * Avery Briggs
 * 3471065
 *
 * Generates a FlowPane with 40 randomly
 * coloured circles of radius 25, in
 * a 10 rows x 4 cols configuration.
 * */

class FlowPaneCircles {

    private FlowPane flowPane;

    FlowPaneCircles() {
        this.flowPane = genFlowPaneCircles();
    }

    private FlowPane genFlowPaneCircles() {
        FlowPane flowPane = new FlowPane();
        for (int i = 0; i < 40; i++) {
            Circle newCircle = new Circle(25);
            newCircle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
            flowPane.getChildren().add(newCircle);
        }
        flowPane.setPrefWrapLength(200.0);
        return flowPane;
    }

    FlowPane getFlowPane() {
        return flowPane;
    }
}
