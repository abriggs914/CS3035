/* This class shows a slider object in a basic example */


//javafx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

//java imports
import java.text.NumberFormat;




public class SliderExampleP1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);
        Slider slider = new Slider();
        slider.setMin(150.0);
        slider.setMax(1000.0);
        slider.setValue(275.0);
        // slider.setShowTickLabels(true);
        // slider.setShowTickMarks(true);
        // slider.setMajorTickUnit(50);
        // slider.setMinorTickCount(5);
        slider.setBlockIncrement(50);
        primaryStage.setTitle("Hello Sliders!");
        Label label = new Label("275.0");
        label.setFont(new Font("Arial", 24));
        Button quitbtn = new Button();

        quitbtn.setText("Quit?");
        quitbtn.setOnAction(e -> {
          System.out.println("Goodbye!");
          System.exit(0);
        });

        slider.setOnMouseDragged(e -> {
          double val = slider.getValue();
          label.setText(nf.format(val));
        });

        slider.setOnMouseClicked(e -> {
          double val = slider.getValue();
          label.setText(nf.format(val));
        });





        VBox vbox1 = new VBox(10);
        vbox1.getChildren().addAll(slider,label,quitbtn);
        vbox1.setAlignment(Pos.CENTER);

        
        /*

        //borderpane ( Nodes; center , top , right , bot , left)
        BorderPane root = new BorderPane(label,slider,null,quitbtn,null);
        //adds to the same pane
        // root.getChildren().add(btn);
        // root.getChildren().add(label);

        root.setAlignment(slider,Pos.CENTER);
        root.setAlignment(quitbtn,Pos.CENTER);

        */


        //stage/scene creation
        primaryStage.setScene(new Scene(vbox1, 300, 250));
        primaryStage.show();
    }
}
