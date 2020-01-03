package Windows;

import Logic.Logic;
import Logic.Logic.Element;
import Utility.Hyperlinks;
import Utility.JavaFXHelper;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class PlayWindow
{
	
	private PlayWindow()
	{
		throw new IllegalStateException("Utility class");
	}
	
	static Label outcome = JavaFXHelper.makeLabel("", 25);
	static int score = 0;
	static int opponentScore = 0;

	public static void addItems(GridPane gridPane)
	{
		ComboBox<String> comboBox = new ComboBox<>();
		gridPane.setAlignment(Pos.CENTER);
		Element[] elements =
		{ Element.ROCK, Element.PAPER, Element.SCISSORS, Element.LIZARD, Element.SPOCK };
		for (Element element : elements)
			comboBox.getItems().add(element.toString());

		Label hintLabel = JavaFXHelper.makeLabel("Choose an element:", 25);
		Label youChose = JavaFXHelper.makeLabel("You chose:", 20);
		Label itChose = JavaFXHelper.makeLabel("Computer chose:", 20);
		Label yourScore = JavaFXHelper.makeLabel("", 40);
		Label computerScore = JavaFXHelper.makeLabel("", 40);
		Label scoreLabel = JavaFXHelper.makeLabel("Score" + "", 30);

		comboBox.setPrefWidth(220);
		youChose.setVisible(false);
		itChose.setVisible(false);
		outcome.setVisible(false);
		scoreLabel.setVisible(false);

		GridPane.setMargin(hintLabel, new Insets(-130, 0, 0, 120));
		GridPane.setMargin(comboBox, new Insets(-200, 0, 0, 120));
		GridPane.setMargin(youChose, new Insets(-50, -50, 0, 50));
		GridPane.setMargin(itChose, new Insets(-50, 20, 0, 0));
		GridPane.setMargin(outcome, new Insets(-80, 0, 0, -150));
		GridPane.setMargin(scoreLabel, new Insets(-170, -190, 0, 0));
		GridPane.setMargin(yourScore, new Insets(0, 0, -100, 100));
		GridPane.setMargin(computerScore, new Insets(0, 0, -100, 50));
		GridPane.setHalignment(scoreLabel, HPos.CENTER);

		gridPane.add(youChose, 0, 1);
		gridPane.add(itChose, 2, 1);
		gridPane.add(hintLabel, 0, 0);
		gridPane.add(comboBox, 0, 1);
		gridPane.add(scoreLabel, 0, 4);
		gridPane.add(outcome, 2, 2);
		gridPane.add(yourScore, 0, 3);
		gridPane.add(computerScore, 2, 3);

		comboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, element) ->
		{
			youChose.setVisible(true);
			itChose.setVisible(true);
			scoreLabel.setVisible(true);

			ImageView elementView = handleComboboxSelect(element);
			gridPane.add(elementView, 0, 2);
			GridPane.setMargin(elementView, new Insets(-80, 0, 0, 50));

			Element computerElement = Logic.getRandomElement();
			ImageView computerView = handleComboboxSelect(computerElement.toString());
			gridPane.add(computerView, 2, 2);
			GridPane.setMargin(computerView, new Insets(-80, 50, 0, 0));

			evaluateWinner(element, computerElement);
			yourScore.setText(score + "");
			computerScore.setText(opponentScore + "");
		});
	}

	private static ImageView handleComboboxSelect(String element)
	{
		Image elementImage;
		switch (element)
		{
		case "ROCK":
			elementImage = new Image(Hyperlinks.ROCK_ICON, 150, 150, false, true);
			break;
		case "PAPER":
			elementImage = new Image(Hyperlinks.PAPER_ICON, 150, 150, false, true);
			break;
		case "SCISSORS":
			elementImage = new Image(Hyperlinks.SCISSORS_ICON, 150, 150, false, true);
			break;
		case "LIZARD":
			elementImage = new Image(Hyperlinks.LIZARD_ICON, 150, 150, false, true);
			break;
		case "SPOCK":
			elementImage = new Image(Hyperlinks.SPOCK_ICON, 150, 150, false, true);
			break;
		default:
			elementImage = new Image(Hyperlinks.ROCK_ICON, 150, 150, false, true);
		}

		return new ImageView(elementImage);
	}

	protected static void evaluateWinner(String element, Element computerElement)
	{
		Element yourElement = Enum.valueOf(Element.class, element);
		if (yourElement.equals(computerElement))
		{
			outcome.setText("Tie");
			outcome.setStyle("-fx-text-fill: grey;");
		} else
		{
			Element el = Logic.getWinnerElement(yourElement, computerElement);
			boolean youWin = el.name().equals(yourElement.toString());
			outcome.setText(youWin ? "You Win" : "Computer\n Wins");
			outcome.setStyle(youWin ? "-fx-text-fill: green;" : "-fx-text-fill: red;");

			if (youWin)
				score++;
			else
				opponentScore++;
		}
		outcome.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		outcome.setVisible(true);
	}
}
