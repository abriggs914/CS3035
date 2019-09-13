import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class PushCounter extends Application
{
	private int count;
	private Text countText;

	public void start(Stage primaryStage)
	{
		count = 0;
		countText = new Text("Pushes: "+count);

		Button push = new Button("Push Me!");
		push.setOnAction(this::processButtonPress);

		FlowPane pane = new FlowPane(push, countText);
		pane.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(pane, 300, 100);

		primaryStage.setTitle("Push Counter");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void processButtonPress(ActionEvent event)
	{
		count++;
		countText.setText("Pushes: " + count);
	}
}