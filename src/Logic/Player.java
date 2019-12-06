package Logic;

import Logic.Logic.Element;

public class Player
{
	public Element element;
	public int score;

	public Player()
	{
		element = Logic.getRandomElement();
		score = 0;
	}

	public void incrementScore()
	{
		score++;
	}

}
