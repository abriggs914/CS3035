package sample;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * This example is created to demonstrate Model-View-Controller with an InteractionModel,
 * and the use of a state machine.
 * Like previous versions of this example, it introduces a fair bit of code, but this helps
 * to keep things organized as interfaces start to get large.
 *
 *
 * @author scottb
 *
 */
public class Main extends Application {
    private static final int squareSideLength = 40;
    static final Model model = new Model(squareSideLength);
    static final InteractionModel iModel = new InteractionModel();
    static final View view = new View();
    public static final Controller controller = new Controller();
    public static final ToolBarController toolBarController = new ToolBarController();

    @Override
    public void start(Stage primaryStage) {
        try {
            Scene scene = new Scene(view,400,400);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            primaryStage.setTitle("CS3035 Assignment 4");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}