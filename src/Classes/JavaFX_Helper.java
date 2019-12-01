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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JavaFX_Helper
{
//TODO : Add EventHandlers to other class
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
		howToPlay.setOnAction(howToPlayWindow()); 
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
	
	protected void addInstructionWindowItems(GridPane gridPane) 
	{
		var image = new Image("https://d3qdvvkm3r2z1i.cloudfront.net/media/catalog/product/cache/1/image/1800x/6b9ffbf72458f4fd2d3cb995d92e8889/r/o/rockpaperscissorslizardspock_newthumb.png",
				400, 550, false, true);
		ImageView imageView = new ImageView(image);
		gridPane.add(imageView, 0, 0);
		String instructions = getInstructions();
		Label text = new Label(instructions);
		text.setFont(Font.font("Arial",FontWeight.BOLD, 20));
		text.setTextFill(Color.rgb(95,197,185));
		GridPane.setHalignment(text, HPos.CENTER);
		GridPane.setMargin(text, new Insets(0, 0, 0, -10));
		text.setTextAlignment(TextAlignment.CENTER);
		text.setPrefHeight(600);
		text.setStyle("-fx-line-spacing: 0.5em;");
		text.setBackground(
		new Background(new BackgroundFill(Color.rgb(20,19,60), CornerRadii.EMPTY, Insets.EMPTY)));
		gridPane.add(text, 1, 0);
		
	}

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
			GridPane gridPane = createGridPane();
			Scene scene = new Scene(gridPane, 988, 550);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			((Node) event.getSource()).getScene().getWindow().hide();
			stage.setOnCloseRequest(closeWindowEvent());
			addInstructionWindowItems(gridPane);
		};
	}
	
	protected EventHandler<WindowEvent> closeWindowEvent()
	{
		return event -> RPSLS.showStartScreen(new Stage());
    }
	
	private String getInstructions() {
		return 
		"The game is an extension of the game Rock, Paper, Scissors. \n" + 
		"Every player picks an element. \n" + 
		"The winner is the player who beats the other players. \n" +
		"In case of a tie, the game repeats until we find a winner. \n \n" + 
		"The rules of the game are as follows: \n" +
		"- Scissors cuts Paper \n" +
		"- Paper covers Rock \n" +
		"- Rock crushes Lizard \n" +
		"- Lizard poisons Spock\n" +
		"- Spock smashes Scissors \n" +
		"- Scissors decapitates Lizard \n" +
		"- Paper disproves Spock \n" +
		"- Spock vaporizes Rock \n" +
		"- (and as it always has) Rock crushes Scissors \n";
	}
}
