package Utility;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EventHandlers
{
	protected EventHandler<ActionEvent> playWindow()
	{
		return event ->
		{
			Stage stage = new Stage();
			stage.setTitle("Play Game");
			GridPane gridPane = JavaFXHelper.createGridPane();
			Scene scene = new Scene(gridPane, 610, 700);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			WindowItems.addPlayWindowItems(gridPane);
		};
	}

	protected EventHandler<ActionEvent> howToPlayWindow()
	{
		return event ->
		{
			Stage stage = new Stage();
			stage.setTitle("How to play");
			GridPane gridPane = JavaFXHelper.createGridPane();
			Scene scene = new Scene(gridPane, 988, 580);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			WindowItems.addInstructionWindowItems(gridPane);
		};
	}

	protected EventHandler<ActionEvent> testWindow()
	{
		return event ->
		{
			Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			if (previousStage.getTitle().equals("Test Players"))
				((Node) event.getSource()).getScene().getWindow().hide();

			Stage stage = new Stage();
			stage.setTitle("Test Players");
			stage.setResizable(false);
			GridPane gridPane = JavaFXHelper.createGridPane();
			Scene scene = new Scene(gridPane, 610, 700);
			stage.setScene(scene);
			stage.show();
			WindowItems.addTestWindowItems(gridPane);

		};
	}
}
