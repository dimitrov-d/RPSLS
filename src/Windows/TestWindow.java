package Windows;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import Logic.Logic;
import Logic.Player;
import Utility.EventHandlers;
import Utility.Hyperlinks;
import Utility.JavaFXHelper;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;

public class TestWindow
{

	static Player[] players = new Player[5];
	static TextInputDialog dialog = new TextInputDialog("5");
	static Stage currentStage;

	private TestWindow()
	{
		throw new IllegalStateException("Utility class");
	}

	public static void addItems(GridPane gridPane)
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

		Label total = JavaFXHelper.makeLabel("Total number of games: " + (5 * 2 * Integer.parseInt(numGames.get())),
				20);
		gridPane.add(total, 0, 0);
		GridPane.setHalignment(total, HPos.LEFT);
		GridPane.setMargin(total, new Insets(10, 0, 0, 20));

		Label max = JavaFXHelper
				.makeLabel("Maximum number of wins per player: " + (4 * Integer.parseInt(numGames.get())), 20);
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
			playerLabels[i] = JavaFXHelper.makeLabel("Score: " + players[i].getScore(), 20);
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
				break;
			}
		}
	}

	private static void initiatePlayers(Player[] players, int numGames)
	{
		for (int i = 0; i < players.length; i++)
			players[i] = new Player();

		long start = System.currentTimeMillis();
		while (maxScoreTieExists(players))
			Logic.playGame(players, numGames);

		// Round runtime from milliseconds to seconds
		System.out.println(
				"Gameplay runtime took: " + ((double) (System.currentTimeMillis() - start) / 1000) + " seconds");

	}

	private static String getWinner()
	{
		int maxScore = 0;

		for (int i = 0; i < players.length; i++)
			if (maxScore < players[i].getScore())
				maxScore = players[i].getScore();

		return ("Score: " + maxScore);
	}

	public static boolean maxScoreTieExists(Player[] players)
	{
		var set = new HashSet<Integer>();
		if (players[0] == null)
			return true;
		for (int i = 0; i < players.length; i++)
		{
			int score = players[i].getScore();
			int maxScore = getMaxScore(players);

			if (score == maxScore && set.contains(score))
				return true;

			set.add(score);
		}
		return false;
	}
	
	private static int getMaxScore(Player[] players)
	{
		int maxScore = 0;

		for (int i = 0; i < players.length; i++)
			if (maxScore < players[i].getScore())
				maxScore = players[i].getScore();

		return maxScore;
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
