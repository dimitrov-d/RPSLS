package Helpers;

import java.util.Arrays;
import java.util.Optional;

import Logic.Logic;
import Logic.Player;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class WindowItems
{

	private WindowItems()
	{
		throw new IllegalStateException("Utility class");
	}

	static Player[] player = new Player[5];
	static TextInputDialog dialog = new TextInputDialog("5");

	public static void addMainWindowItems(GridPane gridPane)
	{
		gridPane.setAlignment(Pos.CENTER);
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

		Button playButton = JavaFXHelper.makeButton("Play");
		gridPane.add(playButton, 0, 2);
		playButton.setOnAction(new EventHandlers().openNewWindow());
		GridPane.setHalignment(playButton, HPos.CENTER);

		Button testButton = JavaFXHelper.makeButton("Test ");
		gridPane.add(testButton, 0, 3);
		testButton.setOnAction(new EventHandlers().testWindow());
		GridPane.setHalignment(testButton, HPos.CENTER);

		Button howToPlay = JavaFXHelper.makeButton("How to play ");
		gridPane.add(howToPlay, 0, 4);
		howToPlay.setOnAction(new EventHandlers().howToPlayWindow());
		GridPane.setHalignment(howToPlay, HPos.CENTER);
	}

	protected static void addInstructionWindowItems(GridPane gridPane)
	{
		gridPane.setAlignment(Pos.CENTER);
		Image image = new Image(
				"https://d3qdvvkm3r2z1i.cloudfront.net/media/catalog/product/cache/1/image/1800x/6b9ffbf72458f4fd2d3cb995d92e8889/r/o/rockpaperscissorslizardspock_newthumb.png",
				400, 580, false, true);
		ImageView imageView = new ImageView(image);
		gridPane.add(imageView, 0, 0);
		String instructions = JavaFXHelper.getInstructions();
		Label text = new Label(instructions);
		text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		text.setTextFill(Color.rgb(95, 197, 185));
		GridPane.setHalignment(text, HPos.CENTER);
		GridPane.setMargin(text, new Insets(0, 0, 0, -10));
		text.setTextAlignment(TextAlignment.CENTER);
		text.setPrefHeight(600);
		text.setStyle("-fx-line-spacing: 0.5em;");
		text.setBackground(new Background(new BackgroundFill(Color.rgb(20, 19, 60), CornerRadii.EMPTY, Insets.EMPTY)));
		gridPane.add(text, 1, 0);

	}

	protected static void addTestWindowItems(GridPane gridPane)
	{
		dialog.setTitle("Input");
		dialog.setHeaderText("How many games should each player play?");
		dialog.setContentText("Enter here:");

		Optional<String> numGames = dialog.showAndWait();
		numGames = validateInput(numGames);
		initiatePlayers(player, Integer.parseInt(numGames.get()));

		Label number = new Label("Number of games per player: " + numGames.get());
		number.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		number.setTextFill(Color.ANTIQUEWHITE);
		gridPane.add(number, 0, 0);
		GridPane.setHalignment(number, HPos.LEFT);
		GridPane.setMargin(number, new Insets(-30, 0, 0, 20));
		
		Label total = new Label("Total number of games: " + (5 * 4 * Integer.parseInt(numGames.get())));
		total.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		total.setTextFill(Color.ANTIQUEWHITE);
		gridPane.add(total, 0, 0);
		GridPane.setHalignment(total, HPos.LEFT);
		GridPane.setMargin(total, new Insets(10, 0, 0, 20));
		
		Label max = new Label("Maximum number of wins per player: " + (5 * Integer.parseInt(numGames.get())));
		max.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		max.setTextFill(Color.ANTIQUEWHITE);
		gridPane.add(max, 0, 0);
		GridPane.setHalignment(max, HPos.LEFT);
		GridPane.setMargin(max, new Insets(50, 0, 0, 20));
	}

	private static void initiatePlayers(Player[] player, int numGames)
	{
		for (int i = 0; i < player.length; i++)
			player[i] = new Player();

		Logic.playGame(player, numGames);

		System.out.println("Ties: " + Logic.getTies());
		Arrays.asList(player).stream().forEach(p -> System.out.println(p.element + ": " + p.score));
	}

	private static Optional<String> validateInput(Optional<String> input)
	{
		while (!input.get().matches("^\\d+$")) // Only numbers and non-empty input
		{
			dialog.setHeaderText("Please enter a number!");
			input = dialog.showAndWait();
		}

		input.ifPresent(games -> System.out.println("Number of games per player: " + games));
		return input;
	}

}
