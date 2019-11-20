package Classes;

import javafx.application.Application;
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

public class RPSLS extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle("Rock Paper Scissors Lizard Spock");
		GridPane gridPane = createGridPane();
		addItems(gridPane);
		Scene scene = new Scene(gridPane, 600, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private GridPane createGridPane()
	{
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(50);
		gridPane.setBackground(getBackground());
		return gridPane;
	}

	private void addItems(GridPane gridPane)
	{
		Label headerLabel = new Label("Welcome To");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
		headerLabel.setTextFill(Color.ANTIQUEWHITE);
		gridPane.add(headerLabel, 0, 0);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(-250, 0, 0, 0));

		Label nameLabel = new Label("Rock Paper Scissors Lizard Spock");
		nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		nameLabel.setTextFill(Color.ANTIQUEWHITE);
		gridPane.add(nameLabel, 0, 1);
		GridPane.setHalignment(nameLabel, HPos.CENTER);
		GridPane.setMargin(nameLabel, new Insets(-200, 0, 0, 0));

		Button playButton = new Button("Play");
		playButton.setPrefHeight(80);
		playButton.setPrefWidth(350);
		playButton.setOnAction(openNewWindow());
		playButton.setStyle("-fx-font-size: 2em;");
		gridPane.add(playButton, 0, 2);
		GridPane.setHalignment(playButton, HPos.CENTER);

		Button testButton = new Button("Test ");
		testButton.setPrefHeight(80);
		testButton.setPrefWidth(350);
		testButton.setOnAction(openNewWindow());
		testButton.setStyle("-fx-font-size: 2em;");
		gridPane.add(testButton, 0, 3);
		GridPane.setHalignment(testButton, HPos.CENTER);
	}

	private Background getBackground()
	{
		BackgroundImage woodBackground = new BackgroundImage(
				new Image("https://image.freepik.com/free-photo/old-wooden-texture-background-vintage_55716-1138.jpg",
						32, 32, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		return new Background(woodBackground);
	}

	private EventHandler<ActionEvent> openNewWindow()
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

	public static void main(String[] args)
	{
		launch(args);
	}
}
