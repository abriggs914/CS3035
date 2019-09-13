//************************************************************************
//		HelloJavaFX.java 		Author: Lewis/Loftus
//
//		Demonstrates a basic JavaFX application.
//************************************************************************

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class HelloJavaFX extends Application
{
	private int count = 0; 

	//--------------------------------------------------------------------
	//	Creates and displays two Text obje cts in a JavaFX window.
	//--------------------------------------------------------------------
	public void start(Stage primaryStage)
	{
		Text hello = new Text(50, 50, "Hello, JavaFX!");
		Text question = new Text(120, 80, "How's it going?");

		Button button = new Button("Button Clicked 0");
		button.setOnAction(this::buttonEvent);

		FlowPane root = new FlowPane(hello, question, button);
		Scene scene = new Scene(root, 300, 120, Color.FORESTGREEN);

		
		
		primaryStage.setTitle("A JavaFX Program");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void buttonEvent(ActionEvent event)
	{
		Button b = (Button) event.getSource();

		b.setText("Button Clicked "+(++count));
	}

}
