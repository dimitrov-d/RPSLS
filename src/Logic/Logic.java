package Logic;

import java.util.Arrays;
import java.util.Random;

public class Logic
{
	protected enum Element
	{
		ROCK, PAPER, SCISSORS, LIZARD, SPOCK
	}

	protected static Element getRandomElement()
	{
		return Element.values()[new Random().nextInt(Element.values().length)];
	}

	protected static Element getWinner(Element el1, Element el2)
	{
		switch (el1)
		{

		case ROCK:
			switch (el2)
			{
			case PAPER:
				return Element.PAPER;
			case SCISSORS:
				return Element.ROCK;
			case LIZARD:
				return Element.ROCK;
			case SPOCK:
				return Element.SPOCK;
			}

		case LIZARD:
			switch (el2)
			{
			case ROCK:
				return Element.ROCK;
			case PAPER:
				return Element.LIZARD;
			case SCISSORS:
				return Element.SCISSORS;
			case SPOCK:
				return Element.LIZARD;
			}
		case PAPER:
			switch (el2)
			{
			case ROCK:
				return Element.PAPER;
			case SCISSORS:
				return Element.SCISSORS;
			case LIZARD:
				return Element.LIZARD;
			case SPOCK:
				return Element.PAPER;
			}
		case SCISSORS:
			switch (el2)
			{
			case ROCK:
				return Element.ROCK;
			case PAPER:
				return Element.SCISSORS;
			case LIZARD:
				return Element.SCISSORS;
			case SPOCK:
				return Element.SPOCK;
			}
		case SPOCK:
			switch (el2)
			{
			case ROCK:
				return Element.SPOCK;
			case PAPER:
				return Element.PAPER;
			case SCISSORS:
				return Element.SPOCK;
			case LIZARD:
				return Element.LIZARD;

			}
		default:
			return el1; // Tie
		}
	}

	public static void main(String[] args)
	{
		int tie = 0;
		Player[] player = new Player[5];
		for (int i = 0; i < player.length; i++)
		{
			player[i] = new Player();
		}

		for (int i = 0; i < player.length; i++)
		{
			for (int j = 0; j < player.length; j++)
			{
				if (player[i].element == player[j].element)
				{
					if (i != j)
						tie++;
					continue;
				}	

				if (player[i].element == getWinner(player[i].element, player[j].element))
					player[i].incrementScore();
				else
					player[j].incrementScore();

			}
		}

		System.out.println("Ties: " + tie);
		Arrays.asList(player).stream().forEach(p -> System.out.println(p.element + ": " + p.score));
	}
}
