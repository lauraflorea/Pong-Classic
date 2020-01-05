import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent; 

public class AI
{
	Robot robot;
	private boolean nPressed; 
	private boolean mPressed; 
	private int counter; // Counts up the number of times the act method is called
	final private int DIVISOR = 1; 
	private int bX1; 
	
	public AI()
	{
		try 
		{
			robot = new Robot();
		} catch (AWTException e) 
		{
			e.printStackTrace();
		} 
				
		nPressed = false; 
		mPressed = false; 
		counter = -1; 
		bX1 = 0; 
	}
	
	// Method which dictates what keys the Robot should press in order to catch the ball. 
	public void react(int p2X, int p2Y, int ballX, int ballY) 
	{
		counter++;
		
		if (counter % DIVISOR != 0) // If the counter is a multiple of the divisor, then the act method will be skipped. 
		{
			return; 
		}
				
		if (bX1 > ballX && ballX < 400 ) // If the ball is going away from the AI paddle, it will not be moved.  
		{
			bX1 = ballX; 
			return;
		}
		
		bX1 = ballX; // Updates the value of bX1 
		
		if ((p2Y + 25) < ballY) // If the paddle is above the ball, then the paddle will be moved down. 
		{
			if (mPressed) 
			{
				robot.keyRelease(KeyEvent.VK_M);
				mPressed = false;
			}
			
			robot.keyPress(KeyEvent.VK_N);
			nPressed = true; 
			return;
		} 
		else if ((p2Y + 25) > ballY) // If the paddle is below the ball, then the paddle will move up. 
		{
			if (nPressed) 
			{
				robot.keyRelease(KeyEvent.VK_N);
				nPressed = false; 
			}
			
			robot.keyPress(KeyEvent.VK_M);
			mPressed = true;
			return;
		} 
	}
}