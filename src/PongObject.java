import java.awt.*;
import javax.swing.JComponent;

public abstract class PongObject extends JComponent
{
	Color colour = Color.WHITE;
	int corner; 
	
	public PongObject() 
	{
		super(); 
	}
	
	//Sets the dimensions of an object. 
	public void setSize(int height, int width) 
	{
		super.setSize(width, height);  
	}
	
	// Gets the horizontal position of an object. 
	public int getXPos() 
	{
		return getLocation().x; 
	}
	
	//	Gets the vertical position of an object. 
	public int getYPos()
	{ 
		return getLocation().y; 
	}
	
	// Sets the horizontal position of an object. 
	public void setXPos(int x) 
	{
		super.setLocation(x, getLocation().y);
	}
	
	// Sets the vertical position of an object. 
	public void setYPos(int y) 
	{
		super.setLocation(getLocation().x, y);
	}

	// Sets the colour of an object. 
	public void setColour(Color c) 
	{
		this.colour = c;
	}

	public void paint(Graphics g) 
	{
		Rectangle r = getBounds();
		g.setColor(colour);
		g.fillRect(0, 0, (int)r.getWidth(), (int)r.getHeight());
	}
	
	// Returns true if the object collides with another. 
	public boolean collides(PongObject o) 
	{
		return getBounds().intersects(o.getBounds());
	}
	
	public abstract void act(); 
}
