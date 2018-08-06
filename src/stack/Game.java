package stack;

import java.awt.Graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Game extends JPanel {
	Stack stack = new Stack();
	ArrayList<Stack> greaterFive = new ArrayList<>(); 
	Disk disk = new Disk(this);
	int ytrack = 660;
	int ycornertrack = 540;
	int r = 51; //color values
	int g = 204;
	int b = 255;
	Color c = new Color(r, g, b, 200);
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				disk.keyPressed(e);
				if(r + 10 < 255)
				{
					r += 10;
				}
				else if(r - 10 > 0)
				{
					r -= 10;
				}
				if(g + 10 < 255)
				{
					g += 10;
				}
				else if(g - 10 > 0)
				{
					g -= 10;
				}
				if(b + 10 < 255)
				{
					b += 10;
				}
				else if(b - 10 > 0)
				{
					b -= 10;
				}
				c = new Color(r, g, b, 200);
			}
		});
		setFocusable(true);
		requestFocus();
	}
	private void move()
	{
		disk.move();
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//Disk
		g.drawRect(disk.xcorner, disk.topycorner, disk.width, disk.height);
		g.drawRect(disk.xcorner, disk.botycorner, disk.width, disk.height);
		g.drawString(disk.score.toString(), 5, 10);
		if(greaterFive.size() < 14) //before the stacks have reached the max height allowed in the level
		{
			for(int i = 0; i < greaterFive.size(); i++)
			{
				g.drawLine(greaterFive.get(i).x11, greaterFive.get(i).y11, greaterFive.get(i).x12, greaterFive.get(i).y12);
				g.drawLine(greaterFive.get(i).x21, greaterFive.get(i).y21, greaterFive.get(i).x22, greaterFive.get(i).y22);
				if(i == greaterFive.size() - 1)
				{
					g.setColor(c); //if it is the top stack, then it fills in the color for better visibility
					g.fillRect(greaterFive.get(i).xcorner, greaterFive.get(i).ycorner, greaterFive.get(i).width, greaterFive.get(i).height);
				}
				else
				{
					g.drawRect(greaterFive.get(i).xcorner, greaterFive.get(i).ycorner, greaterFive.get(i).width, greaterFive.get(i).height);
				}
			}
		}
		else //after the stacks have reached the max height
		{
			ytrack = 660; //these are variables intended to keep track of where the positions of y are
			ycornertrack = 540;
			for(int i = greaterFive.size() - 14; i < greaterFive.size(); i++) //goes from the number of possible stacks to the max size
			{
				greaterFive.get(i).y11 = ytrack;
				greaterFive.get(i).y12 = ytrack - 20;
				greaterFive.get(i).y21 = ytrack;
				greaterFive.get(i).y22 = ytrack - 20;
				greaterFive.get(i).ycorner = ycornertrack;
				greaterFive.get(i).topface = new Rectangle(greaterFive.get(i).xcorner, greaterFive.get(i).ycorner, greaterFive.get(i).width, greaterFive.get(i).height);
				g.drawLine(greaterFive.get(i).x11, greaterFive.get(i).y11, greaterFive.get(i).x12, greaterFive.get(i).y12);
				g.drawLine(greaterFive.get(i).x21, greaterFive.get(i).y21, greaterFive.get(i).x22, greaterFive.get(i).y22);
				if(i == greaterFive.size() - 1)
				{
					g.setColor(c);
					g.fillRect(greaterFive.get(i).xcorner, greaterFive.get(i).ycorner, greaterFive.get(i).width, greaterFive.get(i).height);
				}
				else
				{
					g.drawRect(greaterFive.get(i).xcorner, greaterFive.get(i).ycorner, greaterFive.get(i).width, greaterFive.get(i).height);
				}
				ytrack -= 20; //subtracts by 20 each time to account for the rising stack
				ycornertrack -= 20;
			}
		} 
 
	}
	public void gameOver() {
		removeNotify();
		JOptionPane.showMessageDialog(this, "Game Over", "You lose.", JOptionPane.YES_NO_OPTION);
		System.exit(0);
	}
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Stack");
		Game game = new Game();
		frame.add(game);
		frame.setSize(400, 700);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
