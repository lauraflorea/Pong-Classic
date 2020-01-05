import java.io.*; 
import java.util.*;

public class Player 
{
	String name;
	int score; 
	
	// Sets the name and the score of the player. 
	public Player (String name, int score) 
	{
		this.name = name;
		this.score = score;
	}

	//Returns the name of the player. 
	public String getName() 
	{
		return this.name;
	}
	
	//Returns the score of the player.
	public int getScore() 
	{
		return this.score;
	}
}