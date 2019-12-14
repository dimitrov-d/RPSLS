package Helpers;

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
		Label headerLabel = JavaFXHelper.makeLabel("Welcome To", 36);
		gridPane.add(headerLabel, 0, 0);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(0, 0, 0, 0));

		Label nameLabel = JavaFXHelper.makeLabel("Rock Paper Scissors Lizard Spock", 30);
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

		Label number = JavaFXHelper.makeLabel("Number of games per player: " + numGames.get(), 20);
		gridPane.add(number, 0, 0);
		GridPane.setHalignment(number, HPos.LEFT);
		GridPane.setMargin(number, new Insets(-30, 0, 0, 20));

		Label total = JavaFXHelper.makeLabel("Total number of games: " + (5 * 4 * Integer.parseInt(numGames.get())),
				20);
		gridPane.add(total, 0, 0);
		GridPane.setHalignment(total, HPos.LEFT);
		GridPane.setMargin(total, new Insets(10, 0, 0, 20));

		Label max = JavaFXHelper
				.makeLabel("Maximum number of wins per player: " + (5 * Integer.parseInt(numGames.get())), 20);
		gridPane.add(max, 0, 0);
		GridPane.setHalignment(max, HPos.LEFT);
		GridPane.setMargin(max, new Insets(50, 0, 0, 20));

		Image playerImage = new Image(
				"https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/User_icon_1.svg/768px-User_icon_1.svg.png",
				100, 100, false, true);
		ImageView player1 = new ImageView(playerImage);
		ImageView player2 = new ImageView(playerImage);
		ImageView player3 = new ImageView(playerImage);
		ImageView player4 = new ImageView(playerImage);
		ImageView player5 = new ImageView(playerImage);
		gridPane.add(player1, 1, 2);
		gridPane.add(player2, 0, 3);
		gridPane.add(player3, 2, 3);
		gridPane.add(player4, 0, 4);
		gridPane.add(player5, 2, 4);
		GridPane.setMargin(player1, new Insets(-50, 0, 0, -160));
		GridPane.setMargin(player2, new Insets(-20, 0, 0, 70));
		GridPane.setMargin(player3, new Insets(-20, 0, 0, 0));
		GridPane.setMargin(player4, new Insets(0, 0, 0, 70));
		GridPane.setMargin(player5, new Insets(0, 0, 0, 0));

		Label player1Label = JavaFXHelper.makeLabel("Score: " + player[0].score, 20);
		Label player2Label = JavaFXHelper.makeLabel("Score: " + player[1].score, 20);
		Label player3Label = JavaFXHelper.makeLabel("Score: " + player[2].score, 20);
		Label player4Label = JavaFXHelper.makeLabel("Score: " + player[3].score, 20);
		Label player5Label = JavaFXHelper.makeLabel("Score: " + player[4].score, 20);
		Label tiesLabel = JavaFXHelper.makeLabel("Ties: " + Logic.getTies(), 25);
		gridPane.add(player1Label, 1, 2);
		gridPane.add(player2Label, 0, 3);
		gridPane.add(player3Label, 2, 3);
		gridPane.add(player4Label, 0, 4);
		gridPane.add(player5Label, 2, 4);
		gridPane.add(tiesLabel, 1, 3);
		GridPane.setMargin(player1Label, new Insets(-185, 0, 0, -165));
		GridPane.setMargin(player2Label, new Insets(-150, 0, 0, 80));
		GridPane.setMargin(player3Label, new Insets(-150, 0, 0, 0));
		GridPane.setMargin(player4Label, new Insets(-130, 0, 0, 80));
		GridPane.setMargin(player5Label, new Insets(-130, 0, 0, 0));
		GridPane.setMargin(tiesLabel, new Insets(0, 0, 0, -175));

	}

	private static void initiatePlayers(Player[] player, int numGames)
	{
		for (int i = 0; i < player.length; i++)
			player[i] = new Player();

		long start = System.nanoTime();
		Logic.playGame(player, numGames);

		while (equalScoreExists())
			Logic.playGame(player, numGames);

		getWinner();

		System.out.println( // 1 second = 1_000_000_000 nanoseconds
				"Gameplay runtime took: " + ((double) (System.nanoTime() - start) / 1_000_000_000) + " seconds");

	}

	private static String getWinner()
	{
		int maxScore = 0;
		String winner = "Player 0";

		for (int i = 0; i < player.length; i++)
			if (maxScore > player[i].score)
			{
				maxScore = player[i].score;
				winner = "Player " + i;
			}

		return winner;
	}

	private static boolean equalScoreExists()
	{
		for (int i = 0; i < player.length; i++)
			for (int j = i + 1; j < player.length; j++)
				if (player[j] == player[i])
					return true;
		return false;
	}

	private static Optional<String> validateInput(Optional<String> input)
	{
		while (!input.get().matches("^\\d+$")) // Only numbers and non-empty input
		{
			dialog.setHeaderText("Please enter a valid number!");
			input = dialog.showAndWait();
		}

		return input;
	}

}
