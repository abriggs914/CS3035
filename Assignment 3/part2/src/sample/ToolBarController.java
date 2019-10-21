package sample;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class ToolBarController {

    @FXML ToggleGroup colorGroup;// = new ToggleGroup();
    @FXML ToggleGroup actionGroup;//0 = new ToggleGroup();

    @FXML ToggleButton redButton;// = new ToggleButton();
    @FXML ToggleButton greenButton;// = new ToggleButton();
    @FXML ToggleButton blueButton;// = new ToggleButton();
    @FXML ToggleButton createButton;// = new ToggleButton();
    @FXML ToggleButton deleteButton ;//= new ToggleButton();

    public ToolBarController() {
//        System.out.println("CONSTRUCTOR");
        initialize();
    }

    public void redButtonClicked() { colorButtonClicked("RED"); }
    public void greenButtonClicked() { colorButtonClicked("GREEN"); }
    public void blueButtonClicked() {
        colorButtonClicked("BLUE");
    }

    public void createButtonClicked() {
        System.out.println("Creation mode");
        Main.getScene().setCursor(Cursor.DEFAULT);
        Main.interactionModel.setIsCreating();
        createButton.setSelected(true);
        deleteButton.setSelected(false);
        actionGroup.selectToggle(createButton);
    }
    public void deleteButtonClicked() {
        System.out.println("Deletion mode");
        Main.interactionModel.setIsDeleting();
        createButton.setSelected(false);
        deleteButton.setSelected(true);
        actionGroup.selectToggle(deleteButton);
    }

    public void colorButtonClicked(String colour){
        System.out.println("color button clicked");
        switch(colour) {
            case    "RED"       :   redButton.setSelected(true);
                                    greenButton.setSelected(false);
                                    blueButton.setSelected(false);
                                    redButton.setTextFill(Color.BLACK);
                                    greenButton.setTextFill(Color.WHITE);
                                    blueButton.setTextFill(Color.WHITE);
                                    colorGroup.selectToggle(redButton);
                                    Main.interactionModel.setSelectedColor(Color.valueOf("#ff3333"));
                                    Main.interactionModel.redButtonSelected();
                                    Main.interactionModel.unSelectGreenButton();
                                    Main.interactionModel.unSelectBlueButton();
                                    break;

            case    "GREEN"     :   greenButton.setSelected(true);
                                    redButton.setSelected(false);
                                    blueButton.setSelected(false);
                                    greenButton.setTextFill(Color.BLACK);
                                    redButton.setTextFill(Color.WHITE);
                                    blueButton.setTextFill(Color.WHITE);
                                    colorGroup.selectToggle(greenButton);
                                    Main.interactionModel.setSelectedColor(Color.GREEN);
                                    Main.interactionModel.greenButtonSelected();
                                    Main.interactionModel.unSelectRedButton();
                                    Main.interactionModel.unSelectBlueButton();
                                    break;

            case    "BLUE"      :   blueButton.setSelected(true);
                                    redButton.setSelected(false);
                                    greenButton.setSelected(false);
                                    blueButton.setTextFill(Color.BLACK);
                                    redButton.setTextFill(Color.WHITE);
                                    greenButton.setTextFill(Color.WHITE);
                                    colorGroup.selectToggle(blueButton);
                                    Main.interactionModel.setSelectedColor(Color.BLUE);
                                    Main.interactionModel.blueButtonSelected();
                                    Main.interactionModel.unSelectRedButton();
                                    Main.interactionModel.unSelectGreenButton();
                                    break;

            default             :   break;
        }
        Main.interactionModel.layoutChildren();
    }

//    @FXML
    public void initialize() {
//        System.out.println("INIT TOGGLES");
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
    }
}
