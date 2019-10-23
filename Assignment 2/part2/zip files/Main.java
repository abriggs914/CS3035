package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * CS3035 Assignment 3 - part 2
 * October 18 2019
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
 * Additionally, this part implements a FXML toolbar and controller to create a more friendly interface.
 *
 *      ToolBar functionality includes:
 * Colour selection -   User can select one of red, green or blue to create vertices.
 * action selection -   User now can choose to act in creation mode or deletion mode.
 *                      Creation mode has identical functionality to part 1.
 *                      Deletion mode allows the user to delete vertices and their
 *                      connecting edges. In deletion mode, no creating or manipulation
 *                      can be done.
 *
 *      Creation controls:
 * left-click           -   draw a vertex.
 * shift + left-click   -   draw an edge from one selected vertex to another.
 * left-click + drag    -   drag a selected vertex around the screen.
 *
 *      Deletion controls
 * right-click          -   delete a selected vertex.
 *
 * @author Avery Briggs
 * */

public class Main extends Application {

    private static final int vertexRadius = 25;
    static final GraphModel graphModel = new GraphModel(vertexRadius);
    static final GraphView graphView = new GraphView();
    static final GraphViewController graphViewController = new GraphViewController();
    static InteractionModel interactionModel;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        interactionModel = new InteractionModel();

        primaryStage.setTitle("CS3035 Assignment 3 - part 2");
        primaryStage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(graphView);
        borderPane.setTop(interactionModel);

        scene = new Scene(borderPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
