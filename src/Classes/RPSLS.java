package Classes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RPSLS extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle("Rock Paper Scissors Lizard Spock");
		var helper = new JavaFX_Helper();
		GridPane gridPane = helper.createGridPane();
		helper.addItems(gridPane);
		Scene scene = new Scene(gridPane, 600, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public static void main(String[] args)
	{
		launch(args);
	}
}
