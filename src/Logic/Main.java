package Logic;

import Utility.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		showStartScreen(primaryStage);
	}
	
	public static void showStartScreen(Stage primaryStage) {
		
		primaryStage.setTitle("Rock Paper Scissors Lizard Spock");
		GridPane gridPane = JavaFXHelper.createGridPane();
		WindowItems.addMainWindowItems(gridPane);
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
