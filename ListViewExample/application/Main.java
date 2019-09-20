package application;

import javafx.collections.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;


/**
 * A toy example to show how a ListView works. An object with an external model.
 *
 * @author scottb
 *
 */

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			Scene scene = new Scene(root,200,200);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			ObservableList<String> names = FXCollections.observableArrayList(
			          "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
			ListView<String> listView = new ListView<String>(names);
			listView.setCellFactory(TextFieldListCell.forListView());
			listView.setEditable(true);

			String[] nameAr = {"Jimmy","Jill","Harold","Jen"};
			ArrayList<String> newNames = new ArrayList<String>((List<String>) Arrays.asList(nameAr));

			Button add = new Button("add name");
			add.setOnAction((event)->{
				 if (!newNames.isEmpty())
					 names.add(newNames.remove(0));
				 if (newNames.isEmpty())
					 add.setDisable(true);
			});
			root.getChildren().add(add);

			ListView<String> horizontalListView = new ListView<String>(names);
			horizontalListView.setOrientation(Orientation.HORIZONTAL);

			root.getChildren().add(listView);
			root.getChildren().add(horizontalListView);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
