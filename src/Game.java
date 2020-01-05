import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public abstract class Game extends JFrame 
{	
	protected int numPlayers = -1; 	
	private Timer _t; 
	private ArrayList<PongObject> _ObjectList = new ArrayList(); 
	
	// if the 'Z' key is being held down.
	private boolean p1Left = false;
	
	 // if the 'X' key is being held down.
	private boolean p1Right = false;
	
	//if the 'N' key is being held down.
	private boolean p2Left = false;
	
	// if the 'M' key is being held down.  
	private boolean p2Right = false;
	
	// JPanel pops up displaying welcome text. Two button options are available that allow users to exit the game, or view instructions
	public void instructions()
	{
		Object[] options1 = {"Instructions", "Exit"};
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("Welcome to Pong!"));
		
		int result = JOptionPane.showOptionDialog(null, panel,
			    "Pong", 
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.PLAIN_MESSAGE, null, options1, null);
		
		if(result == JOptionPane.YES_OPTION)
		{
			runInstructions();
		} else {
			exit();
		}
	}
	
	// JPanel pops up displaying game instructions with button that allows players to exit the program.
	private void runInstructions()
	{
		Object[] option1 = {"1 Player Game", "Exit", "2 Player Game"};
			
		int  n = JOptionPane.showOptionDialog(null, 
				"Player 1 Movement: Press X for move up, press Z to move down. \n"
				+ "Player 2 Movement: Press M to move up, press N to move down" +
				"\nObjective: Bounce the ball off the paddle to prevent it from hitting the wall." +
				"\nFirst player to reach 10 points wins."
				,"Instructions" ,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, option1, null);
		
		
		if (n == 0) // If button 0 is pressed, this means that there will be a 1 player game ("yes" button)  
		{
			numPlayers = 1; 
		} else if (n == 1) // If button 1 is pressed, this means that there will be a 2 player game ("cancel" button)
		{
			numPlayers = 2;
		}
		
		if(n == JOptionPane.NO_OPTION) // If the "no" button is pressed, then the game will be exited. 
		{
			exit();
		}
	}
	
	// Exits the game. 
	public static void exit()
	{
		System.exit(0);
	}
	
	// Shows a pop up on the screen that depicts which player earned a point.  
	public void continueScreen(int player)
	{
		JOptionPane.showMessageDialog(getOwner(),
			    "Player " + player + " gained 1 point.\n Click Ok to continue");
	}
	
	// Returns true if the 'Z' key is being pressed. 
	public boolean ZKeyPressed() 
	{
		return p1Left;
	}
		
	// Returns true if the 'X' key is being pressed. 
	public boolean XKeyPressed() 
	{
		return p1Right;
	}
		
	// Returns true if the 'N' key is being pressed. 
	public boolean NKeyPressed() 
	{
		return p2Left;
	}
		
	//Returns true if the 'M' key is being pressed. 
	public boolean MKeyPressed() 
	{
		return p2Right;
	}
	
	public abstract void setup(); 
	
	public abstract void act();	
	
	public abstract void startOver(); 

	public void initComponents() 
	{
		getContentPane().setBackground(Color.black);
		setup();
		for (int i = 0; i < _ObjectList.size(); i++) 
		{
				PongObject o = (PongObject)_ObjectList.get(i);
				o.repaint();
		}
		_t.start(); 	
	}

	// Adds an object to the object list to be added on the screen. 
	public void add(PongObject o)
	{
		_ObjectList.add(o);
		getContentPane().add(o);
	}
	
	//Removes a game object from the screen. 
	public void remove(PongObject o) 
	{
		_ObjectList.remove(o);
		getContentPane().remove(o);
	}
	
	// Sets the delay of an object. 
	public void setDelay(int delay) 
	{
		_t.setDelay(delay);
	}
	
	public Game() 
	{	
		setSize(800, 600); // Sets the size of the window to 800 x 600 pixels. 
		setUndecorated(false);
		setResizable(false);
		setLocationRelativeTo(null);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) 
		{
			e1.printStackTrace();
		}
	
		getContentPane().setBackground(Color.black);
		getContentPane().setLayout(null);
		JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Menu");
        JMenuItem menuFileExit = new JMenuItem("Exit");
        JMenuItem menuFileAgain = new JMenuItem("New Game");
        menuBar.add(menuFile);
        menuFile.add(menuFileExit);
        menuFile.add(menuFileAgain);
        setJMenuBar(menuBar);
		setTitle("Pong");
		
		// Adds window listener.
        addWindowListener (
            new WindowAdapter() 
            {
                public void windowClosing(WindowEvent e) 
                {
                    System.exit(0);
                }
            }
        );
      
        menuFileExit.addActionListener( 
       		new ActionListener() 
       		{
       			public void actionPerformed(ActionEvent e) 
       			{
       				System.exit(0);
       			}
       		}
       	);
        // Action listener for the "New Game" button
        menuFileAgain.addActionListener( 
           		new ActionListener() {
           			public void actionPerformed(ActionEvent e) 
           			{
           				startOver();
           				startGame();
           			}
           		}
           	);
		
		_t = new Timer(1, new ActionListener() 
		{
       		public void actionPerformed(ActionEvent e) 
       		{
   					act();
   				for (int i = 0; i < _ObjectList.size(); i++) 
   				{
   					PongObject o = (PongObject)_ObjectList.get(i);
   					o.act();
   				}
       		}
       });
		
		addKeyListener(new KeyListener() 
		{     
			public void keyTyped(KeyEvent e) 
			{
			}
	
			// Instructions for if a certain key is pressed; sets that variable to true.  
			public void keyPressed(KeyEvent e) 
			{
				char pressed = Character.toUpperCase(e.getKeyChar());
				switch (pressed) {
					case 'Z' : p1Left = true; break;
					case 'X' : p1Right = true; break;
					case 'N' : p2Left = true; break;
					case 'M' : p2Right = true; break;
				}
			}
	
			// Instructions for if a certain key is released; sets that variable to false. 
			public void keyReleased(KeyEvent e) 
			{
				char released = Character.toUpperCase(e.getKeyChar());
				switch (released) {
					case 'Z' : p1Left = false; break;
					case 'X' : p1Right = false; break;
					case 'N' : p2Left = false; break;
					case 'M' : p2Right = false; break;
				}
			}
       }); 
	}
	
	// Starts updates to the game
	public void startGame() 
	{
		_t.start();
	}
	
	// Stops updates to the game
	public void stopGame() 
	{
		_t.stop();
	}
	
	//  Shows the winner of the game. 
	public void playerWins(String playerName) 
	{
		JOptionPane.showMessageDialog(null,
			    "GAME OVER! "+playerName+" wins!",
			    "GGWP",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	// Gets the width of the game field 
	public int getFieldWidth() 
	{
		int fw = getContentPane().getBounds().width;
	    return fw;
	}
	
	// Gets the height of the game field 
	public int getFieldHeight() 
	{
		int fh = getContentPane().getBounds().height -100; // 100 reserved for the scoreboard at the frame bottom
		return fh;
	}

	// Resets all keys back to false. 
	public void resetAllKeys()
	{
		p1Left = false;
		p1Right = false;
		p2Left = false;
		p2Right = false;
	}
}