package Logic;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Logic
{
	static int ties = 0;

	protected enum Element
	{
		ROCK, PAPER, SCISSORS, LIZARD, SPOCK
	}

	protected static Element getRandomElement()
	{
		return Element.values()[new Random().nextInt(Element.values().length)];
	}

	protected static void randomizePlayers(Player[] players)
	{
		Arrays.asList(players).stream().forEach(p -> p.element = getRandomElement());
	}

	protected static Element getWinnerElement(Element el1, Element el2)
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

	public static void getWinnerPlayer(Player p1, Player p2)
	{
		if (p1.element == p2.element)
			return;

		if (p1.element == getWinnerElement(p1.element, p2.element))
			p1.incrementScore();
		else
			p2.incrementScore();
	}

	// Total number of games: 5 * 4 * number_of_games
	// Maximum number of wins: 5 * number_of_games
	public static void playGame(Player[] player)
	{
		for (int i = 0; i < player.length; i++)
		{
			for (int j = 0; j < player.length; j++)
			{
				for (int z = 0; z < 5; z++)
				{
					if (i==j)
						break;
					
					if (player[i].element == player[j].element)
							ties++;
					
					getWinnerPlayer(player[i], player[j]);
					randomizePlayers(player);

					System.out.println("Game " + (z+1) + ": Players " + (i+1) + " and " + (j+1));
				}
			}
		}
	}

	public static void main(String[] args)
	{
		Player[] player = new Player[5];
		for (int i = 0; i < player.length; i++)
		{
			player[i] = new Player();
		}

		playGame(player);

		System.out.println("Ties: " + ties);
		Arrays.asList(player).stream().forEach(p -> System.out.println(p.element + ": " + p.score));
	}
}
