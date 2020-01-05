@SuppressWarnings("serial")    
public class Paddle extends PongObject 
{
	int dY = 2;
	
	public Paddle() 
	{
		super(); 
	}
	
	// Moves the paddle down. 
	public void moveDown()
	{
		setYPos(getY()+ dY);
	}
	
	// Moves the paddle up. 
	public void moveUp()
	{
		setYPos(getY() - dY);
	}

	// Stops the paddle so it doesn't go beyond the bottom of the screen
	public void stopPaddleDown() 
	{
		setYPos(getY() - dY);
	}
	
	// Stops the paddle so it doesn't go beyond the top of the screen 
	public void stopPaddleUp()
	{
		setYPos(getY() + dY);
	}
	
	public void act()
	{	
	}
}