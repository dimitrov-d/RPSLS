package Classes;

import Classes.Logic.Element;

public class Player
{
	protected Element element;
	protected int score;
	
	Player() {
		element = Logic.getRandomElement();
		score = 0;
	}
	
	protected void incrementScore() {
		score++;
	}
}
