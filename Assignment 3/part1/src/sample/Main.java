package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * CS3035 Assignment 3 - part 1
 * October 16 2019
 * Avery Briggs
 * 3471065
 *
 * JavaFX application to create graphs and manipulate them.
 * Application uses the model: Model-View-Controller.
 * GraphModel contains the graph structure and implements graph logic on edges.
 * GraphView creates visible framework and drawing components to be manipulated by the controller.
 * GraphViewController contains all event handlers for on-click and on-drag events to run the application.
 * Vertex is a structure to store vertex's attributes.
 * Edge is a structure to store an edge's attributes.
 *
 *      Controls:
 * left-click           -   draw a vertex.
 * right-click          -   delete a selected vertex.
 * shift + left-click   -   draw an edge from one selected vertex to another.
 * left-click + drag    -   drag a selected vertex around the screen.
 *
 * @author Avery Briggs
 * */

public class Main extends Application {

    private static final int vertexRadius = 25;
    static final GraphModel graphModel = new GraphModel(vertexRadius);
    static final GraphView graphView = new GraphView();
    static final GraphViewController graphViewController = new GraphViewController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CS3035 Assignment 3 - part 1");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(graphView, 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
