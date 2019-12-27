package Helpers;

import java.util.NoSuchElementException;
import java.util.Optional;
import Logic.Logic;
import Logic.Logic.Element;
import Logic.Player;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;

public class WindowItems
{
	static Image elementImage;

	private WindowItems()
	{
		throw new IllegalStateException("Utility class");
	}

	static Player[] players = new Player[5];
	static TextInputDialog dialog = new TextInputDialog("5");
	static Stage currentStage;

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
		playButton.setOnAction(new EventHandlers().playWindow());
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

	protected static void addPlayWindowItems(GridPane gridPane)
	{

		gridPane.setAlignment(Pos.CENTER);
		Label scoreLabel = JavaFXHelper.makeLabel("Score", 30);

		Element[] elements =
		{ Element.ROCK, Element.PAPER, Element.SCISSORS, Element.LIZARD, Element.SPOCK };
		ComboBox<String> comboBox = new ComboBox<String>();
		Label hintLabel = JavaFXHelper.makeLabel("Choose an element:", 25);

		comboBox.setPrefWidth(220);
		for (Element element : elements)
			comboBox.getItems().add(element.toString());
		GridPane.setMargin(hintLabel, new Insets(-100, 0, 0, 0));
		GridPane.setMargin(comboBox, new Insets(-100, 0, 0, 0));
		GridPane.setMargin(scoreLabel, new Insets(100, 0, 0, 0));

//		gridPane.add(scoreLabel, 0, 3);
		gridPane.add(hintLabel, 0, 0);
		gridPane.add(comboBox, 0, 1);
		
		
		Label youChose = JavaFXHelper.makeLabel("You chose:", 20);
		youChose.setVisible(false);
		GridPane.setMargin(youChose, new Insets(50, 0, -50, -115));
		gridPane.add(youChose, 0, 1);
		comboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, element) ->
		{
			switch (element)
			{
			case "ROCK":
				elementImage = new Image(Hyperlinks.ROCK_ICON, 150, 150, false, true);
				youChose.setVisible(true);
				break;
			case "PAPER":
				elementImage = new Image(Hyperlinks.PAPER_ICON, 150, 150, false, true);
				youChose.setVisible(true);
				break;
			case "SCISSORS":
				elementImage = new Image(Hyperlinks.SCISSORS_ICON, 150, 150, false, true);
				youChose.setVisible(true);
				break;
			case "LIZARD":
				elementImage = new Image(Hyperlinks.LIZARD_ICON, 150, 150, false, true);
				youChose.setVisible(true);
				break;
			case "SPOCK":
				elementImage = new Image(Hyperlinks.SPOCK_ICON, 150, 150, false, true);
				youChose.setVisible(true);
				break;
			default:
				break;
			}
			

			ImageView elementView = new ImageView(elementImage);
			gridPane.add(elementView, 0, 2);
			GridPane.setMargin(elementView, new Insets(0, 0, 0, -130));

		});

		int score = 0;
		Element computerElement = Logic.getRandomElement();

	}

	protected static void addInstructionWindowItems(GridPane gridPane)
	{
		gridPane.setAlignment(Pos.CENTER);
		Image image = new Image(Hyperlinks.INSTRUCTIONS_IMAGE, 400, 580, false, true);
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
		currentStage = (Stage) gridPane.getScene().getWindow();
		dialog.setTitle("Input");
		dialog.setHeaderText("How many games should each player play?");
		dialog.setContentText("Enter here:");

		Optional<String> numGames = dialog.showAndWait();
		if (checkForInput(numGames))
			numGames = validateInput(numGames);
		else
		{
			currentStage.close();
			return;
		}

		if (numGames == null) // Cancelled during validation
		{
			currentStage.close();
			return;
		}
		initiatePlayers(players, Integer.parseInt(numGames.get()));

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

		Button testAgainButton = JavaFXHelper.makeButton("Test again");
		testAgainButton.setPrefWidth(120);
		testAgainButton.setDefaultButton(true);
		gridPane.add(testAgainButton, 1, 4);
		GridPane.setMargin(testAgainButton, new Insets(0, 0, 0, -170));
		testAgainButton.setOnAction(new EventHandlers().testWindow());

		Image playerImage = new Image(Hyperlinks.PLAYER_ICON, 100, 100, false, true);
		ImageView[] playerImages = new ImageView[5];
		for (int i = 0; i < playerImages.length; i++)
			playerImages[i] = new ImageView(playerImage);

		gridPane.add(playerImages[0], 1, 2);
		gridPane.add(playerImages[1], 0, 3);
		gridPane.add(playerImages[2], 2, 3);
		gridPane.add(playerImages[3], 0, 4);
		gridPane.add(playerImages[4], 2, 4);

		GridPane.setMargin(playerImages[0], new Insets(-50, 0, 0, -160));
		GridPane.setMargin(playerImages[1], new Insets(-20, 0, 0, 70));
		GridPane.setMargin(playerImages[2], new Insets(-20, 0, 0, 0));
		GridPane.setMargin(playerImages[3], new Insets(0, 0, 0, 70));
		GridPane.setMargin(playerImages[4], new Insets(0, 0, 0, 0));

		Label[] playerLabels = new Label[5];
		for (int i = 0; i < playerLabels.length; i++)
			playerLabels[i] = JavaFXHelper.makeLabel("Score: " + players[i].score, 20);
		Label tiesLabel = JavaFXHelper.makeLabel("Ties: " + Logic.getTies(), 25);

		gridPane.add(playerLabels[0], 1, 2);
		gridPane.add(playerLabels[1], 0, 3);
		gridPane.add(playerLabels[2], 2, 3);
		gridPane.add(playerLabels[3], 0, 4);
		gridPane.add(playerLabels[4], 2, 4);
		gridPane.add(tiesLabel, 1, 3);

		GridPane.setMargin(playerLabels[0], new Insets(-185, 0, 0, -165));
		GridPane.setMargin(playerLabels[1], new Insets(-150, 0, 0, 80));
		GridPane.setMargin(playerLabels[2], new Insets(-150, 0, 0, 0));
		GridPane.setMargin(playerLabels[3], new Insets(-130, 0, 0, 80));
		GridPane.setMargin(playerLabels[4], new Insets(-130, 0, 0, 0));
		GridPane.setMargin(tiesLabel, new Insets(0, 0, 0, -165));

		String winnerScore = getWinner();
		for (int i = 0; i < playerLabels.length; i++)
		{
			if (playerLabels[i].getText().equals(winnerScore))
			{
				playerLabels[i].setTextFill(Color.GREEN);
				playerLabels[i].setBackground(
						new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		}
	}

	private static void initiatePlayers(Player[] players, int numGames)
	{
		for (int i = 0; i < players.length; i++)
			players[i] = new Player();

		long start = System.currentTimeMillis();
		Logic.playGame(players, numGames);

		while (equalScoreExists())
			Logic.playGame(players, numGames);

		// Round runtime from milliseconds to seconds
		System.out.println(
				"Gameplay runtime took: " + ((double) (System.currentTimeMillis() - start) / 1000) + " seconds");

	}

	private static String getWinner()
	{
		int maxScore = 0;

		for (int i = 0; i < players.length; i++)
			if (maxScore < players[i].score)
				maxScore = players[i].score;

		return ("Score: " + maxScore);
	}

	private static boolean equalScoreExists()
	{
		for (int i = 0; i < players.length; i++)
			for (int j = i + 1; j < players.length; j++)
				if (players[j].score == players[i].score)
					return true;
		return false;
	}

	private static Optional<String> validateInput(Optional<String> input)
	{

		while (!input.get().matches("^\\d+$")) // Only numbers and non-empty input
		{
			dialog.setHeaderText("Please enter a valid number!");
			input = dialog.showAndWait();

			if (!checkForInput(input))
				return null;
		}

		return input;
	}

	private static boolean checkForInput(Optional<String> input)
	{
		try
		{
			input.get();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}

		return true;
	}

}
