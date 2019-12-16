package Helpers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EventHandlers
{
	protected EventHandler<ActionEvent> openNewWindow()
	{
		return event ->
		{
			Stage stage = new Stage();
			stage.setTitle("My New Stage Title");
			stage.setScene(new Scene(new Button("click"), 600, 700));
			stage.show();
			((Node) event.getSource()).getScene().getWindow().hide();
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
