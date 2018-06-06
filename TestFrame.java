import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.Timer;

public class TestFrame extends JFrame implements ActionListener
{
	private ChickenNugger test;
	private FrenchFriedMinion french;
	private Bullet ball;
	private ArrayList<Bullet> balls;
	private Bars bar;
	private HireWireJugggler juggler;
	private SweerPotatoMinion sweet;
	private SweerPotato sweeter;
	
	public TestFrame()
	{
		setBounds(0,0,1920,1080);
		test = new ChickenNugger(200,200);
		french = new FrenchFriedMinion(1000,156);
		bar = new Bars();
		balls = new ArrayList<Bullet>();
		juggler = new HireWireJugggler(500,250);
		sweet = new SweerPotatoMinion(1100,156);
		sweeter = new SweerPotato(1200,100);
		setLayout(null);
		add(test);
		add(french);
		add(bar);
		add(juggler);
		add(sweet);
		add(sweeter);
		add(new Platform(0,600));
		Timer timer = new Timer(10,this);
		timer.start();
		juggler.animate();
		juggler.bombDrop(this);
		sweeter.shoot(this);
		addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
					test.jumping();
				if (e.getKeyCode() == KeyEvent.VK_A)
					test.setDX(-3);
				if (e.getKeyCode() == KeyEvent.VK_D)
					test.setDX(3);
				if (e.getKeyCode() == KeyEvent.VK_S)
					test.superMode();
				if (e.getKeyCode() == KeyEvent.VK_W)
					test.punching();
				if (e.getKeyCode() == KeyEvent.VK_X)
					french.setHP(0);
				if (e.getKeyCode() == KeyEvent.VK_T){
					if(ChickenNugger.isShooting()){
						ball = new Bullet(test.getX()+test.getWidth(), (test.getY() + 100));
						balls.add(ball);
						add(ball);
						if(!ChickenNugger.isLeft()) {
							ball.setDX(+10);
						}
						if(ChickenNugger.isLeft()) {
							ball.setDX(-10);
						}
						Bars.setAmmo(Bars.getAmmo() - 1);
					}
				}
					
			}
			
			public void keyReleased(KeyEvent e) 
			{
				if (e.getKeyCode() == KeyEvent.VK_A)
					test.setDX(0);
				if (e.getKeyCode() == KeyEvent.VK_D)
					test.setDX(0);
			}

			public void keyTyped(KeyEvent e) 
			{
				
			}
		});
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		new TestFrame();
	}

	public void actionPerformed(ActionEvent arg0)
	{
		test.update();
		french.update();
		juggler.update();
		sweet.update();
		if (test.isSuper())
		{
			test.contact(french, 10, 25);
			test.contact(sweet, 15, 25);
		}
		else
		{
			test.contact(french, 10, 15);
			test.contact(sweet, 15, 15);
		}
		for(int i = 0; i<balls.size(); i++) 
		{
			balls.get(i).update();
			balls.get(i).damage(french);
			balls.get(i).damage(sweet);
			if(balls.get(i).getX() > getWidth() || balls.get(i).getX() < 0 || balls.get(i).getY() < 0 || balls.get(i).getY() > getHeight()) 
			{
				remove(balls.get(i));
				balls.remove(balls.get(i));
			}
		}
		for (int i = 0; i < juggler.getBombs().size(); i++)
		{
			juggler.getBombs().get(i).update();
			if(juggler.getBombs().get(i).damage(test))
			{
				remove(juggler.getBombs().get(i));
				juggler.getBombs().remove(i);
			}
		}
		for (int i = 0; i < sweeter.getBullets().size(); i++)
		{
			sweeter.getBullets().get(i).update();
			if(sweeter.getBullets().get(i).damage(test))
			{
				remove(sweeter.getBullets().get(i));
				sweeter.getBullets().remove(i);
			}
		}
		repaint();
	}
}
