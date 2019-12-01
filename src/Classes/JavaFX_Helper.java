package Classes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JavaFX_Helper
{

	protected GridPane createGridPane()
	{
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(80);
		gridPane.setBackground(getBackground());
		return gridPane;
	}

	protected void addItems(GridPane gridPane)
	{
		Label headerLabel = new Label("Welcome To");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
		headerLabel.setTextFill(Color.ANTIQUEWHITE);
		gridPane.add(headerLabel, 0, 0);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(0, 0, 0, 0));

		Label nameLabel = new Label("Rock Paper Scissors Lizard Spock");
		nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		nameLabel.setTextFill(Color.ANTIQUEWHITE);
		gridPane.add(nameLabel, 0, 1);
		GridPane.setHalignment(nameLabel, HPos.CENTER);
		GridPane.setMargin(nameLabel, new Insets(-50, 0, 50, 0));

		Button playButton = makeButton("Play");
		gridPane.add(playButton, 0, 2);
		playButton.setOnAction(openNewWindow()); 
		GridPane.setHalignment(playButton, HPos.CENTER);

		Button testButton = makeButton("Test ");
		gridPane.add(testButton, 0, 3);
		testButton.setOnAction(openNewWindow()); 
		GridPane.setHalignment(testButton, HPos.CENTER);
		
		Button howToPlay = makeButton("How to play ");
		gridPane.add(howToPlay, 0, 4);
		howToPlay.setOnAction(openNewWindow()); 
		GridPane.setHalignment(howToPlay, HPos.CENTER);
	}
	
	
	protected Button makeButton(String text) {
		Button button = new Button(text);
		button.setPrefHeight(60);
		button.setPrefWidth(350);
		button.setStyle("-fx-font-size: 1.5em;");
		
		return button;
	}
	
	protected Background getBackground()
	{
		BackgroundImage woodBackground = new BackgroundImage(
				new Image("https://image.freepik.com/free-photo/old-wooden-texture-background-vintage_55716-1138.jpg",
						32, 32, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		return new Background(woodBackground);
	}

	protected EventHandler<ActionEvent> openNewWindow()
	{
		return event ->
		{
			Stage stage = new Stage();
			stage.setTitle("My New Stage Title");
			stage.setScene(new Scene(new Button("click"), 450, 450));
			stage.show();
			((Node) event.getSource()).getScene().getWindow().hide();
		};
	}
}
