import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Bars extends JComponent
{
	private static int hp, ketchup, ammo;
	
	private Rectangle hpBar, ketchupBar, ammoBar;
	
	public Bars()
	{
		setLocation(600,600);
		setSize(120,50);
		
		hp = 100;
		ketchup = 0;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle background = new Rectangle(0,0,120,50);
		g2.setColor(Color.BLACK);
		g2.fill(background);
		
		hpBar = new Rectangle(10,10,getHP(),10);
		g2.setColor(Color.GREEN);
		g2.fill(hpBar);
		
		ketchupBar = new Rectangle(10,20,getSP(),10);
		g2.setColor(Color.RED);
		g2.fill(ketchupBar);
		
		ammoBar = new Rectangle(10,30,10*getAmmo(),10);
		g2.setColor(Color.BLUE);
		g2.fill(ammoBar);
	}
	
	public static int getHP()
	{
		return hp;
	}
	
	public static int getSP()
	{
		return ketchup;
	}
	
	public static int getAmmo()
	{
		return ammo;
	}
	
	public static void setHP(int h)
	{
		hp = h;
	}
	
	public static void setSP(int s)
	{
		ketchup = s;
	}
	
	public static void setAmmo(int a)
	{
		ammo = a;
	}
}
