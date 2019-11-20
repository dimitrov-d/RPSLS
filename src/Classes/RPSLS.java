package Classes;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
		GridPane gridPane = createRegistrationFormPane();
		gridPane.setBackground(getBackground());
		addUIControls(gridPane);
		Scene scene = new Scene(gridPane, 600, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private GridPane createRegistrationFormPane()
	{
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(50);

		return gridPane;
	}

	private void addUIControls(GridPane gridPane)
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
		gridPane.add(playButton, 0, 2);
		GridPane.setHalignment(playButton, HPos.CENTER);

		Button testButton = new Button("Test ");
		testButton.setPrefHeight(80);
		testButton.setPrefWidth(350);
		gridPane.add(testButton, 0, 3);
		GridPane.setHalignment(testButton, HPos.CENTER);
	}

	private Background getBackground()
	{
		BackgroundImage woodBackground = new BackgroundImage(new Image(
				"https://image.freepik.com/free-photo/old-wooden-texture-background-vintage_55716-1138.jpg",
				32, 32, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);

		return new Background(woodBackground);
		
//		return new Background(
//              Collections.singletonList(new BackgroundFill(
//                      Color.WHITE, 
//                      new CornerRadii(500), 
//                      new Insets(10))),
//              Collections.singletonList(new BackgroundImage(
//                      new Image("https://image.freepik.com/free-photo/old-wooden-texture-background-vintage_55716-1138.jpg", 1000, 1000, false, true),
//                      BackgroundRepeat.NO_REPEAT,
//                      BackgroundRepeat.NO_REPEAT,
//                      BackgroundPosition.CENTER,
//                      BackgroundSize.DEFAULT)));
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}
