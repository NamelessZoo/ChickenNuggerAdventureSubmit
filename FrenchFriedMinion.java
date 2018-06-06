import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FrenchFriedMinion extends Character
{	
	private BufferedImage image = null;
	
	private String frenchFriedMinion = "frenchminion.PNG";
	
	public FrenchFriedMinion(int x, int y)
	{
		super(x,y);
		setDX(-1);
		setHP(15);
		try 
		{
			image = ImageIO.read(new File(frenchFriedMinion));
			setSize(image);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, this);
		setSize(image);
	}

	public void update()
	{
		if (getHP() <= 0)
		{
			image = null;
		}
		for (int i = 0; i < Platform.getPlatforms().size(); i++)
		{
			if (getRect().intersects(Platform.getPlatforms().get(i).getRect()) && ((getDY() <= 0 && getY() > Platform.getPlatforms().get(i).getRect().getY())))
			{
				setDY(0);
		}
			else if (getRect().intersects(Platform.getPlatforms().get(i).getRect()) && ((getDY() >= 0 && getY() < Platform.getPlatforms().get(i).getRect().getY())))
			{
				setDY(0);
			}
			else  {
				setDY(2);
			}
		}
		if ((getX() <= 0 && getDX() < 0) || (getX() >= 1920 && getDX() > 0))
			setLocation(getX(), getY() + getDY());
		else
			setLocation(getX() + getDX(), getY() + getDY());
		if (getX() == 0)
			setDX(0);
		setLocation(getX() + getDX(), getY() + getDY());
	}
}
