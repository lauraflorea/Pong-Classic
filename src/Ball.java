import java.awt.*;
import java.util.Random;

public class Ball extends PongObject
{

	Random rand = new Random();  
	int dX = 1;
	int dY;
	double multiplier;	
	

	// Constructor, resets the position of the ball. 
	public Ball()
	{	
		super();  
		reset();	
	}

	// Resets the speed, direction, and angle of the ball for the next round. 
	public void reset() 
	{
		dX *= -1;
		dY = rand.nextInt(2) + 1;
		multiplier = 1;	
	}	
	
	// Updates the position of the ball.
	public void ballPos() 
	{
		setXPos(getX()+ (int) (dX * multiplier));  
		setYPos(getY()+ (int) (dY * multiplier));   
	}
	
	// Gets the position of the ball. 
	public int[] getBallPos() 
	{
		int[] i = {getX(), getY()};  
		
		return i; 		
	}
	
	// Flips the horizontal direction of the ball. 
	public void changeXDir()
	{
		dX *= -1;	
	}

	// Flips the vertical direction of the ball/
	public void changeYDir()
	{
		dY *= -1;
	}
	
	// Increases the speed of the ball. 
	public void speedIncrease() 
	{
		multiplier += 0.2;
	}
	
	// Gets the speed of the ball. 
	public double getSpeed() 
	{
		return multiplier; 
	}
	
	// Updates the position of the ball every millisecond. 
	public void act() 
	{
		setXPos(getX()+ (int) (dX * multiplier));
		setYPos(getY()+ (int) (dY * multiplier));
	}
		
	@Override
	public void paint(Graphics g) 
	{
		Rectangle r = getBounds();
		g.setColor(Color.WHITE);
		g.fillOval(0, 0, (int)r.getWidth(), (int)r.getHeight());
	}
}
