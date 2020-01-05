import java.awt.*;
import javax.swing.JLabel; 

public class ScoreBoard extends	PongObject 
{
	int pointP1;
	int pointP2;
	String player1;
	String player2;
	
	// Paints the score board at the bottom of the screen. 
	@Override
	public void paint(Graphics g) 
	{
		Rectangle r = getBounds();
		int ht =  (int)r.getHeight();
		int wd = (int)r.getWidth();
		g.setColor(Color.BLUE); 
		g.setFont(new Font("Calibri", Font.PLAIN, 20));
		g.fillRect(0, 0, wd, ht);
		g.setColor(new Color(255, 255, 255));
		g.drawString("Player 1: " + player1, 10, 30);
		g.drawString("Points: " + Integer.toString(pointP1), 10, 50);
		g.setColor(new Color(255, 255, 255));
		g.drawString("Player 2: "+ player2, 610, 30);
		g.drawString("Points: " + Integer.toString(pointP2), 610, 50);
	}
	
	// Updates the points of the players on the score board
	public void setPoint(int player, int point)
	{
		if ( player == 1)  // If the player is player 1, add 1 to their point, if not add 1 to player 2's point.
		{
			pointP1 = point;
		} else 
		{
			pointP2 = point;
		}
		this.repaint();
	}
	
	// Sets the names of the players 
	public void setPlayers(String p1, String p2)
	{
		player1 = p1;
		player2 = p2;
	}

	@Override
	public void act() 
	{		
	}
}
