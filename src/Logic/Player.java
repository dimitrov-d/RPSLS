package Logic;


import Logic.Logic.Element;

public class Player
{
	 Element element;
	 int score;
	
	Player() {
		element = Logic.getRandomElement();
		score = 0;
	}
	
	public void incrementScore() {
		score++;
	}
	
}
