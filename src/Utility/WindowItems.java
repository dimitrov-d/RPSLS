package Utility;

import Windows.InstructionWindow;
import Windows.MainWindow;
import Windows.PlayWindow;
import Windows.TestWindow;
import javafx.scene.layout.GridPane;

public class WindowItems
{
	private WindowItems()
	{
		throw new IllegalStateException("Utility class");
	}

	public static void addMainWindowItems(GridPane gridPane)
	{
		MainWindow.addItems(gridPane);
	}

	protected static void addPlayWindowItems(GridPane gridPane)
	{
		PlayWindow.addItems(gridPane);
	}

	protected static void addInstructionWindowItems(GridPane gridPane)
	{
		InstructionWindow.addItems(gridPane);
	}

	protected static void addTestWindowItems(GridPane gridPane)
	{
		TestWindow.addItems(gridPane);
	}

}
