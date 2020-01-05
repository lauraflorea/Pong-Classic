import java.awt.Color;

import javax.swing.JFrame;

public class Mod1Test 
{

	public static void main(String[] args) 
	{		
		Window window = new Window();		
	}
}

class Window extends JFrame 
{ 
	public Window() 
	{
		super("Pong Test"); 
		
		final int WINDOW_X = 800;
		final int WINDOW_Y = 600; 
		
		int startBallX = 375;
		int startBallY = 275;
		int startPaddle1X = 15;
		int startPaddle1Y = 250;
		int startPaddle2X = 750;
		int startPaddle2Y = 250;
		
		this.setResizable(false);
		this.setSize(WINDOW_X, WINDOW_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		this.setLayout(null);
		this.getContentPane().setBackground(Color.BLACK);
		
		//this.add(new Ball(startBallX, startBallY)); 
		//this.add(new Paddle( startPaddle1X, startPaddle1Y)); 
		//this.add(new Paddle(startPaddle2X, startPaddle2Y));
	
		this.setVisible(true);	
	}	
}