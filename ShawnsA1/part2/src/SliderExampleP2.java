//javafx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

//java imports
import java.text.NumberFormat;




public class SliderExampleP2 extends Application {
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
          //System.out.println("clicked");
          double val = slider.getValue();
          label.setText(nf.format(val));
        });


        VBox vbox2 = new VBox(10);
        vbox2.getChildren().addAll(slider,label,quitbtn);
        vbox2.setAlignment(Pos.CENTER);



        HBox hbox = new HBox(5);
        HBox barPane1 = new HBox(10);
        HBox barPane2 = new HBox(10);
        HBox barPane3 = new HBox(10);
        barPane1.setPadding(new Insets(5,5,5,5));
        barPane2.setPadding(new Insets(5,5,5,5));
        barPane3.setPadding(new Insets(5,5,5,5));

        BarControl bar1 = new BarControl(0,1000,500);
        BarControl bar2 = new BarControl(150,750,200);
        BarControl bar3 = new BarControl(50,100,100);

        barPane1 = bar1.getBarAsHBox();
        barPane2 = bar2.getBarAsHBox();
        barPane3 = bar3.getBarAsHBox();

        hbox.getChildren().addAll(barPane1,barPane2,barPane3);

        VBox vbox1 = new VBox(30);
        vbox1.getChildren().addAll(hbox,vbox2);

        /*
        used in helloworld example
        //borderpane ( Nodes; center , top , right , bot , left)
        BorderPane root = new BorderPane(label,slider,null,quitbtn,null);
        //adds to the same pane
        // root.getChildren().add(btn);
        // root.getChildren().add(label);

        root.setAlignment(slider,Pos.CENTER);
        root.setAlignment(quitbtn,Pos.CENTER);

        */


        //stage/scene creation
        primaryStage.setScene(new Scene(vbox1, 600, 500));
        primaryStage.show();
    }
}
