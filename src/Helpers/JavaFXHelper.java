package Helpers;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

public class JavaFXHelper
{
	
	private JavaFXHelper()
	{
		throw new IllegalStateException("Utility class");
	}
	
	public static GridPane createGridPane()
	{
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(80);
		gridPane.setBackground(getBackground());
		return gridPane;
	}

	protected static Button makeButton(String text)
	{
		Button button = new Button(text);
		button.setPrefHeight(60);
		button.setPrefWidth(350);
		button.setStyle("-fx-font-size: 1.5em;");

		return button;
	}

	protected static Background getBackground()
	{
		BackgroundImage woodBackground = new BackgroundImage(
				new Image("https://image.freepik.com/free-photo/old-wooden-texture-background-vintage_55716-1138.jpg",
						32, 32, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		return new Background(woodBackground);
	}

	protected static String getInstructions()
	{
		return "The game is an extension of the game Rock, Paper, Scissors. \n" + "Every player picks an element. \n"
				+ "Every player plays the given amount \n"
				+ "of games with each other player. \n"
				+ "The winner is the player who beats the other players. \n"
				+ "In case of a tie, the game repeats until we find a winner. \n \n"
				+ "The rules of the game are as follows: \n" + "- Scissors cuts Paper \n" + "- Paper covers Rock \n"
				+ "- Rock crushes Lizard \n" + "- Lizard poisons Spock\n" + "- Spock smashes Scissors \n"
				+ "- Scissors decapitates Lizard \n" + "- Paper disproves Spock \n" + "- Spock vaporizes Rock \n"
				+ "- (and as it always has) Rock crushes Scissors \n";
	}
}
