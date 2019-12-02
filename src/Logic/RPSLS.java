package Logic;

import Helpers.JavaFX_Helper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RPSLS extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		showStartScreen(primaryStage);
	}
	
	public static void showStartScreen(Stage primaryStage) {
		
		primaryStage.setTitle("Rock Paper Scissors Lizard Spock");
		var helper = new JavaFX_Helper();
		GridPane gridPane = helper.createGridPane();
		helper.addItems(gridPane);
		Scene scene = new Scene(gridPane, 600, 700);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(e ->
		{
			Platform.exit();
			System.exit(0);
		});
		
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
