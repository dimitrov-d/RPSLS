package Logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import Utility.*;
import Windows.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		if (netIsAvailable())
			showStartScreen(primaryStage);
		else
			showNoConnectionWarning();

	}

	public static void showStartScreen(Stage primaryStage)
	{
		primaryStage.setTitle("Rock Paper Scissors Lizard Spock");
		GridPane gridPane = JavaFXHelper.createGridPane();
		MainWindow.addItems(gridPane);
		Scene scene = new Scene(gridPane, 600, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Platform.exit());

	}

	public static void main(String[] args)
	{
		launch(args);
	}

	private static boolean netIsAvailable()
	{
		try
		{
			// Custom URL for testing
			final URLConnection connection = new URL("http://www.google.com").openConnection();
			connection.connect();
			connection.getInputStream().close();
			return true;
		}
		catch (MalformedURLException e)
		{
			throw new RuntimeException(e);
		}
		catch (IOException e)
		{
			return false;
		}
	}
	
	private static void showNoConnectionWarning()
	{
		Alert alert = new Alert(AlertType.WARNING, "Please connect to the internet and try again", ButtonType.OK);
		alert.showAndWait();
	}
}
