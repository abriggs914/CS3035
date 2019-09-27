//javafx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.shape.Rectangle;

//java imports
import java.text.NumberFormat;

public class BarControl{

  final public int yellowBarHeight = 190;
  final public int yellowBarWidth = 40;
  final public int blackBarHeight = 200;
  final public int blackBarWidth = 50;

  public HBox slider;
  public Label valueLabel;
  public double startVal;
  public double minVal;
  public double maxVal;


  public BarControl(double minVal, double maxVal, double startVal){
    //for formatting double values on the pane
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMinimumFractionDigits(1);
    nf.setMaximumFractionDigits(1);

    this.minVal = minVal;
    this.maxVal = maxVal;
    this.startVal = startVal;
    this.slider = createNewSlider();
  }


    public HBox createNewSlider(){
    double initYellowHeight;
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMinimumFractionDigits(1);
    nf.setMaximumFractionDigits(1);
    //slider
    if(this.minVal > this.startVal){
      this.minVal = 0;
      if(this.minVal > this.startVal){
        this.startVal = 0;
      }
    }

    if(this.startVal > this.maxVal){
      this.startVal = 0;
    }

    if(this.minVal > this.maxVal){
      double temp = this.minVal;
      this.minVal = this.maxVal;
      this.maxVal = temp;
    }
    
    initYellowHeight = ((this.startVal - this.minVal)/(this.maxVal - this.minVal)) * (double)yellowBarHeight;

    if((initYellowHeight > this.maxVal) || (initYellowHeight < this.minVal)){
      initYellowHeight = 0;
    }//end inital yellow bar bounds check

    nf.format(initYellowHeight);
    Label valueLabel = new Label(String.valueOf(initYellowHeight));
    Rectangle blackBar = new Rectangle(0,0,blackBarWidth,blackBarHeight);
    blackBar.setFill(Color.BLACK);
    Rectangle yellowBar = new Rectangle(5,5,yellowBarWidth,initYellowHeight);
    yellowBar.setFill(Color.YELLOW);

    //creating pane with yellow bar, when placing bars
    //on top of each other one goes missing. this solves that problem
    StackPane yellowPane = new StackPane();
    yellowPane.setAlignment(Pos.BOTTOM_CENTER);
    yellowPane.setPadding(new Insets(5,5,5,5));
    yellowPane.getChildren().add(yellowBar);

    //placing yellow pane on the black bar on the black pane
    StackPane blackPane = new StackPane();
    blackPane.setAlignment(Pos.CENTER);
    blackPane.setPadding(new Insets(5,5,5,5));
    blackPane.getChildren().addAll(blackBar,yellowPane);

    blackPane.setOnMouseClicked(e -> {
      double newYellowHeight = e.getY() - 10; // -10 for the padding of yellowBar
      double tempHeight = Math.max(0,Math.min(yellowBarHeight,newYellowHeight));
      tempHeight = yellowBarHeight - tempHeight;

      //show one pixel if the bar size is 0.
      if(tempHeight <= 0){
        tempHeight = 1.0;
        nf.format(tempHeight);
      }
      else{
        nf.format(tempHeight);
      }
      valueLabel.setText(String.valueOf(tempHeight));
      yellowBar.setHeight(tempHeight);

    });

    blackPane.setOnMouseDragged(e -> {
      double newYellowHeight = e.getY() - 10; // -10 for the padding of yellowBar
      double tempHeight = Math.max(0,Math.min(yellowBarHeight,newYellowHeight));
      tempHeight = yellowBarHeight - tempHeight;

      //show one pixel if the bar size is 0.
      if(tempHeight <= 0){
        tempHeight = 1.0;
        nf.format(tempHeight);
      }
      else{
        nf.format(tempHeight);
      }
      valueLabel.setText(String.valueOf(tempHeight));
      yellowBar.setHeight(tempHeight);
    });

    HBox hbox = new HBox();
    hbox.setPadding(new Insets(10,10,10,10));
    //this.initReportValue();
    valueLabel.setText(String.valueOf(startVal));
    hbox.getChildren().addAll(blackPane, valueLabel);
    return hbox;



  }//end SliderBar Constructor

  HBox getBarAsHBox(){
    return this.slider;
  }
  // public HBox createNewSlider(double min,double max, double init){
  //
  //       HBox slider = new HBox();
  //       slider.setPadding(new Insets(10,10,10,10));
  //       //this.initReportValue();
  //
  //       slider.getChildren().addAll(bar, valueLabel);
  //       return hbox;
  //
  // }

}//end class
