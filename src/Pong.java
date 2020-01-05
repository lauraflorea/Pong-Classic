import java.awt.*; 
import javax.swing.*;

@SuppressWarnings("serial")
public class Pong extends Game
{
	Ball b; 
	Paddle p1, p2;
	AI ai; 
	ScoreBoard sb;
	
	int pointP1 = 0;
	int pointP2 = 0;
	int scoreP1 = 0;
	int scoreP2 = 0;
	String player1 = "";
	String player2 = "";

	int corner; 
	
	public int getNumPlayers() 
	{
		return numPlayers;	
	}
	
	public void setup()
	{
		// Calls the method that displays the instructions
		instructions();
		
		//Gets the name of the first player
		player1 = JOptionPane.showInputDialog(
				this, 
				"Enter the name of player 1:",
				"P1 Name",
				JOptionPane.PLAIN_MESSAGE);			
		
		// If the 1 player option is selected, then the name of the other player will be automatically set to be "AI". 
		if (numPlayers == 1)  
		{
			player2 = "AI"; 
		} 
		else // If not, then the second player can input their name. 
		{
			player2 = JOptionPane.showInputDialog(
					this, 
					"Enter the name of player 2:",
					"P2 Name",
					JOptionPane.PLAIN_MESSAGE);
		}
					
		// Sets delay of the ball to 10, changes the movement speed
		setDelay(10);
				
		// Creates a new score board object that displays the score of the players during the game
		sb = new ScoreBoard();
		sb.setXPos(0);
		sb.setYPos(0);
		sb.setSize(95, 800);
		sb.setXPos(0);
		sb.setYPos(getFieldHeight() + 10);
		sb.setPoint(1, pointP1);
		sb.setPoint(2, pointP2);
		sb.setPlayers(player1, player2);
		add(sb);
		
		// Creates a new ball object, add it to the center of the screen
		b = new Ball();
		b.setSize(20, 20);
		b.setXPos(getFieldWidth()/2);
		b.setYPos(getFieldHeight()/2);
		add(b);
		
		// Creates a new paddle object and adds it to the left side of the screen
		p1 = new Paddle();
		p1.setSize(80, 10);
		p1.setXPos(0);
		p1.setYPos(getFieldHeight() / 2 - 40);
		add(p1);

		// Creates a new paddle object and adds it to the right side of the screen
		p2 = new Paddle();
		p2.setSize(80, 10);
		p2.setXPos(getFieldWidth() - 10);
		p2.setYPos(getFieldHeight() / 2 - 40);
		add(p2);

		// If there is only one player playing, then the AI will be created and used as the second player. 
		if (numPlayers == 1) 
		{
			ai = new AI(); 
		}
	}
	
	// Resets all of the positions of the paddles and the ball to the middle of the screen, as well as the keys. 
	public void resetup()
	{	
		b.setXPos(getFieldWidth()/2);
		b.setYPos(getFieldHeight()/2);
		b.reset();
		
		p1.setXPos(0);
		p1.setYPos(getFieldHeight()/2 - 40);
	
		p2.setXPos(getFieldWidth() - 10);  
		p2.setYPos(getFieldHeight()/2 - 40);
	
		resetAllKeys();
	}
	
	// Resets all required variables and prepares for a new game between same players
	public void startOver()
	{
		pointP1 = 0;
		pointP2 = 0;
		scoreP1 = 0;
		scoreP2 = 0;
		sb.setPoint(1, pointP1);
		sb.setPoint(2, pointP2);
		resetup();
	}
	
	// Tells program what methods(actions) to perform each millisecond
	public void act() 
	{
		// If ball hits the right side of the screen, stop the game and add one to player 1's score
		if(b.getX() >= getFieldWidth() - 20)
		{ 
			stopGame();
			pointP1++;
			sb.setPoint(1, pointP1);

				if(pointP1 == 10)
				{			
					playerWins(player1); 
				} else 
				{ 
					continueScreen(1); //Displays pop up dialog indicating that player 1 has earned a point
					resetup(); //Resets the screen, put paddles and balls back to their original positions
					startGame(); // Continues the game 
				}			
		}
				
		//If ball hits the left side of the screen, stop the game and add one to player 2's score
		if(b.getX() < 0)
		{
			stopGame();
			pointP2++;
			sb.setPoint(2, pointP2);
			
			if(pointP2 == 10)
			{
				playerWins(player2); 

					} else 
					{
						continueScreen(2); //Displays pop up dialog indicating that player 2 has earned a point
						resetup(); //Resets the screen, put paddles and balls back to their original positions
						startGame(); //Continues the game
					}
				}
		
		// Change vertical direction of ball when it hits the top or bottom of the screen
		if(b.getY() >= getFieldHeight() - 20 || b.getY() < 0)
		{ 
			b.changeYDir();
		}

		//When ball hits paddle 1, change lateral direction, and increase speed
		if(b.getBounds().intersects(p1.getBounds())) 
		{
			if (b.getBounds().intersects(10, getTopCorner(p1), 10, 80)) 
			{
				b.setXPos(b.getX() + 5);
				b.setYPos(b.getY() + 5);
			} else if (b.getBounds().intersects(10, getBottomCorner(p1), 10, 80))
			{
				b.setXPos(b.getX() + 5); 
				b.setYPos(b.getY() - 5);
			} 
			
			b.changeXDir();
			b.speedIncrease();			
		}
		
		// When ball hits paddle 2, change lateral direction, and increase speed
		if(b.getBounds().intersects(p2.getBounds())) 
		{
			if (b.getBounds().intersects(790, getTopCorner(p2), 10, 80)) 
			{
				b.setXPos(b.getX() - 20);
				b.setYPos(b.getY() + 10);
			} else if (b.getBounds().intersects(790, getBottomCorner(p2), 10, 80))
			{
				b.setXPos(b.getX() - 20);
				b.setYPos(b.getY() - 10);
			}
			
			b.changeXDir();
			b.speedIncrease();
		}
			
		//When x key is pressed move player 1 up and stop it at the top of the screen
		if(XKeyPressed() && !(p1.getYPos() < 0))
		{ 
			p1.moveUp();
		}
		
		//When z key is pressed move player 1 down and stop it at the bottom of the screen
		if(ZKeyPressed() && !(p1.getYPos() > getFieldHeight() - 80))
		{ 
			p1.moveDown();
		}
		
		//When m key is pressed move player 2 up and stop it at the top of the screen
		if((MKeyPressed()) &&!(p2.getYPos() < 0))
		{ 
			p2.moveUp();
		}
		
		//When n key is pressed move player 2 down and stop it at the bottom of the screen
		if(NKeyPressed() && !(p2.getYPos()>getFieldHeight() - 80 ))
		{ 
			p2.moveDown();
		}
		
		// If there is only one person, then the AI will move the paddle according to its position and the ball's position.   
		if (numPlayers == 1) 
		{
			ai.react(p2.getX(), p2.getY(), b.getX(), b.getY());
		}
	}
	
	// Gets the top corner of a paddle. 
	private int getTopCorner(Paddle paddle) 
	{
		corner = paddle.getY(); 
					
		return corner; 
	}
		
	// Gets the bottom corner of a paddle. 
	private int getBottomCorner(Paddle paddle) 
	{
		corner = paddle.getYPos() + 80;
		
		return corner; 
	}

	// Returns the score of the players. 
	public String getScore() 
	{
		 return scoreP1 + " : " + scoreP2;		
	}
	
	public static void main(String[] args) 
	{
		Pong p = new Pong();	
		p.setVisible(true);
		p.initComponents();		
	}	
}