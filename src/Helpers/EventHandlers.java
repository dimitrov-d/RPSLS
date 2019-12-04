package Helpers;

import Logic.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EventHandlers
{
	
	JavaFX_Helper helper = new JavaFX_Helper();
	
	
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
			GridPane gridPane = helper.createGridPane();
			Scene scene = new Scene(gridPane, 988, 550);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			((Node) event.getSource()).getScene().getWindow().hide();
			stage.setOnCloseRequest(closeWindowEvent());
			helper.addInstructionWindowItems(gridPane);
		};
	}
	
	protected EventHandler<ActionEvent> testWindow()
	{
		return event ->
		{
			Stage stage = new Stage();
			stage.setTitle("Test Players");
			GridPane gridPane = helper.createGridPane();
			Scene scene = new Scene(gridPane, 600, 700);
			stage.setScene(scene);
			stage.show();
			((Node) event.getSource()).getScene().getWindow().hide();
			stage.setOnCloseRequest(closeWindowEvent());
			helper.addTestWindowItems(gridPane);
		};
	}

	protected EventHandler<WindowEvent> closeWindowEvent()
	{
		return event -> Main.showStartScreen(new Stage());
	}
}
