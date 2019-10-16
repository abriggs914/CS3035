package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int vertexRadius = 25;
    public static final GraphModel graphModel = new GraphModel(vertexRadius);
    public static final GraphView graphView = new GraphView();
    public static final GraphViewController graphViewController = new GraphViewController();
//    public static final Vertex vertex = new Vertex(1, 100, 100, vertexRadius);
//    public static final Edge edge = new Edge();

    @Override
    public void start(Stage primaryStage) {
//        graphModel.addVertex(100, 100);	//populate the model with an initial vertex
        primaryStage.setTitle("CS3035 Assignment 3 - part 1");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(graphView, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
