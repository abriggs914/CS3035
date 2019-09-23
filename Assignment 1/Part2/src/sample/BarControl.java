package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.text.NumberFormat;

/**
 * Sept. 24 / 2019
 *
 * Java class to create a slider widget in JavaFX.
 *
 * @author Avery Briggs
 */

public class BarControl extends Pane {

    final private int YELLOW_HEIGHT_SCALE = 190;
    final private int YELLOW_WIDTH_SCALE = 40;
    final private int BLACK_HEIGHT_SCALE = 200;
    final private int BLACK_WIDTH_SCALE = 50;

    private HBox sliderBar;
    private Label reportValue;
    private Rectangle yellowBox;
    private Rectangle blackBox;
    private int initVal;
    private int maxVal;
    private int minVal;

    BarControl(int minVal, int maxVal, int initVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.initVal = initVal;
        this.sliderBar = genSliderBar();
    }

    private HBox genSliderBar() {
        double initialYellowHeight;
        if (this.initVal >= this.minVal && this.initVal <= this.maxVal) {
            initialYellowHeight = (((double) this.initVal - this.minVal) /
                                    (((double) this.maxVal) - this.minVal)) * YELLOW_HEIGHT_SCALE;
        }
        else {
            initialYellowHeight = 0.0;
        }
        Rectangle blackBar = new Rectangle(0, 0, BLACK_WIDTH_SCALE, BLACK_HEIGHT_SCALE);
        Rectangle yellowBar = new Rectangle(5, 5, YELLOW_WIDTH_SCALE, initialYellowHeight);
        blackBar.setFill(Color.BLACK);
        yellowBar.setFill(Color.YELLOW);
        this.yellowBox = yellowBar;
        this.blackBox = blackBar;

        StackPane yellowStack = new StackPane();
        yellowStack.setAlignment(Pos.BOTTOM_CENTER);
        yellowStack.setPadding(new Insets(5,5,5,5));
        ObservableList yellowList = yellowStack.getChildren();
        yellowList.add(yellowBar);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);  //.setCenter(pane);
        stackPane.setPadding(new Insets(5,5, 5,5));
        ObservableList list = stackPane.getChildren();
        list.addAll(blackBar, yellowStack);

        stackPane.setOnMouseClicked(e -> {
            double boxHeight = e.getY() - 10;
            double height = Math.max(0, Math.min(YELLOW_HEIGHT_SCALE, boxHeight));
            height = YELLOW_HEIGHT_SCALE - height;
            String reportNumber;
            if (height <= 0) {
                height = 1;
                reportNumber = stringifyDouble(porportionalizeDimension(0));
            }
            else {
                reportNumber = stringifyDouble(porportionalizeDimension(height));
            }
            yellowBar.setHeight(height);
            this.reportValue.setText(reportNumber);
        });

        stackPane.setOnMouseDragged(e -> {
            double boxHeight = e.getY() - 10;
            double height = Math.max(0, Math.min(YELLOW_HEIGHT_SCALE, boxHeight));
            height = YELLOW_HEIGHT_SCALE - height;
            String reportNumber;
            if (height <= 0) {
                height = 1;
                 reportNumber = stringifyDouble(porportionalizeDimension(0));
            }
            else {
                reportNumber = stringifyDouble(porportionalizeDimension(height));
            }
            yellowBar.setHeight(height);
            this.reportValue.setText(reportNumber);
        });

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        this.initReportValue();
        hbox.getChildren().addAll(stackPane, this.reportValue);
        return hbox;
    }

    HBox getBarControl() {
        return this.sliderBar;
    }

    private void initReportValue() {
        double val = this.initVal;
        String strVal = stringifyDouble(val);
        Label label = new Label();
        label.setFont(Font.font ("Verdana", 18));
        label.setText(strVal);
        this.reportValue = label;
    }

    // Takes in the height of the yellow box and returns
    // a double between the specified boundaries
    private double porportionalizeDimension(double dim) {
        double min = this.minVal;
        double max = this.maxVal;
        return  ((dim * (max - min)) / YELLOW_HEIGHT_SCALE) + min;
    }

    private String stringifyDouble(double val) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(1);
        nf.setMinimumFractionDigits(1);
        return nf.format(val);
    }
}
