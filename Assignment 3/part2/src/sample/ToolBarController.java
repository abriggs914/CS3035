package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class ToolBarController {

    @FXML private ToggleGroup colorGroup;// = new ToggleGroup();
    @FXML private ToggleGroup actionGroup;//0 = new ToggleGroup();

    @FXML public ToggleButton redButton;// = new ToggleButton();
    @FXML public ToggleButton greenButton;// = new ToggleButton();
    @FXML public ToggleButton blueButton;// = new ToggleButton();
    @FXML public ToggleButton createButton;// = new ToggleButton();
    @FXML public ToggleButton deleteButton ;//= new ToggleButton();

    private Color selectedColor;
    private boolean creatingVertices;

    public ToolBarController() {
        System.out.println("CONSTRUCTOR");
//        initialize();
        System.out.println("colorGroup: " + colorGroup);
        System.out.println("actionGroup: " + actionGroup);
//        redButton.setToggleGroup(colorGroup);
//        greenButton.setToggleGroup(colorGroup);
//        blueButton.setToggleGroup(colorGroup);
//        createButton.setToggleGroup(actionGroup);
//        deleteButton.setToggleGroup(actionGroup);

        redButton.setOnMouseClicked(event -> System.out.println("REDBUTTONCLICKED"));
        greenButton.setOnMouseClicked(event -> System.out.println("GREENBUTTONCLICKED"));
        blueButton.setOnMouseClicked(event -> System.out.println("BLUEBUTTONCLICKED"));
        createButton.setOnMouseClicked(event -> System.out.println("CREATEBUTTONCLICKED"));
        deleteButton.setOnMouseClicked(event -> System.out.println("DELETEBUTTONCLICKED"));

        colorGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle toggle, Toggle new_toggle) {
                Color selectedColor = (Color) colorGroup.getSelectedToggle().getUserData();
                System.out.println("TOGGLE CHANGED - selectedColor: " + selectedColor);
                if (colorGroup.getSelectedToggle() == redButton) {
                    redButton.setTextFill(Color.BLACK);
                }
                else if (colorGroup.getSelectedToggle() == greenButton) {
                    greenButton.setTextFill(Color.BLACK);
                }
                else {
                    blueButton.setTextFill(Color.BLACK);
                }
            }
        });
//        this.selectedColor = getSelectedColorFromGroup();
    }

    public void colorButtonClicked(){

    }

//    @FXML
    public void initialize() {
        System.out.println("INIT TOGGLES");
        colorGroup = new ToggleGroup();
        actionGroup = new ToggleGroup();
        redButton = new ToggleButton();
        greenButton = new ToggleButton();
        blueButton = new ToggleButton();
        createButton = new ToggleButton();
        deleteButton = new ToggleButton();
        redButton.setToggleGroup(colorGroup);
        greenButton.setToggleGroup(colorGroup);
        blueButton.setToggleGroup(colorGroup);
        createButton.setToggleGroup(actionGroup);
        deleteButton.setToggleGroup(actionGroup);
//        redButton.isSelected();
    }

    public Color getSelectedColorFromGroup() {
        Toggle colorChoice = colorGroup.getSelectedToggle();
        System.out.println(colorChoice.getUserData());
        return Color.WHEAT;
    }
}
