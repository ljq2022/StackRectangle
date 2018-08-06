package stack;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Disk {
	@SuppressWarnings("deprecation")
	Integer score = new Integer(0);
	private Game game;
	int x11 = 150, x12 = 150, y11 = 540, y12 = 520; //line base1
	int x21 = x11 + 100, x22 = x12 + 100, y21 = 480, y22 = 460; //line base2
	int x31 = x21, x32 = x22, y31 = 460, y32 = 440; //line base3
	int x41 = 150, x42 = 150, y41 = 460, y42 = 440; //line base4
	int width = 100;
	int height = 100;
	Rectangle topface;
	Rectangle bottomface;
	int xcorner = 150;
	int topycorner = 520;
	int botycorner = 540;
	int difficulty = 1;
	int xa = difficulty; //movement factor
	public Disk(Game game)
	{
		topface = new Rectangle(xcorner, topycorner, width, height);
		bottomface = new Rectangle(xcorner, botycorner, width, height);
		this.game= game;
		if(game.greaterFive.size() == 0)
		{
			game.greaterFive.add(game.stack); //adds the stack at the start of the game
		}
	}
	public void move()
	{
		if (xcorner + xa < 0) //left boundary
		{
			xa = Math.abs(xa); //if it bounces off the left boundary, it moves right
		}
		if (xcorner + width + xa > game.getWidth()) //right boundary
		{
			xa = -difficulty;
		}
		xcorner += xa;
		topface = new Rectangle(xcorner, topycorner, 100, 100);
		bottomface = new Rectangle(xcorner, botycorner, 100, 100);
		x11+= xa;
		x12+= xa;
		x21+= xa;
		x22+= xa;
		x31+= xa;
		x32+= xa;
		x41+= xa;
		x42+= xa; //the following just adds the movement factor to the position coordinates of the disk
	}
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(DoesPolygonIntersectPolygon())
			{
				//DRAWING NEW DISK AND NEW STACK
				Rectangle newPosition  = game.disk.bottomface.intersection(game.greaterFive.get(game.greaterFive.size() - 1).topface);
				height = (int)newPosition.getHeight();
				width = (int)newPosition.getWidth();
				xcorner = (int) newPosition.getX();
				x11 = (int) newPosition.getX();
				x12 = (int) newPosition.getX();
				x21 = (int) newPosition.getX() + width;
				x22 = (int) newPosition.getX() + width;
				x31 = x21;
				x32 = x22;
				x41 = x11;
				x42 = x12;
				y11 = (int) newPosition.getY() + height;
				y12 = (int) newPosition.getY() - 20 + height;
				y21 = (int) newPosition.getY() + height;
				y22 = (int) newPosition.getY() - 20 + height;
				game.greaterFive.add(new Stack(x11, y11, x12, y12, x21, y21, x22, y22, width, height, xcorner, topycorner));				
				if(game.greaterFive.size() < 15) //moves the disk up until there are a certain number of stacks
				{
					//UPDATING POSITION
					y11 -= 20;
					y12 -= 20;
					y22 -= 20;
					y21 -= 20;
					y31 -= 20;
					y32 -= 20;
					y41 -= 20;
					y42 -= 20;
					topycorner -= 20;
					botycorner -= 20;
				}
				if(score % 20 == 0) //if the score is divisible by 20, it resets the disk to the left. Otherwise, it goes right
				{
					x11 = 0;
					x12 = 0;
					x41 = 0;
					x42 = 0;
					x21 = 100;
					x22 = 100;
					x31 = 100;
					x32 = 100;
					xcorner = 0;
				}
				else
				{
					x11 = game.getWidth() - 100;
					x12 = game.getWidth() - 100;
					x41 = game.getWidth() - 100;
					x42 = game.getWidth() - 100;
					x21 = game.getWidth();
					x22 = game.getWidth();
					x31 = game.getWidth();
					x32 = game.getWidth();
					xcorner = game.getWidth() - 100;
				}
			}
			else
			{
				game.gameOver(); 
			}
		}
		
	} 
	public boolean DoesPolygonIntersectPolygon()
    {
		if(score % 60 == 0 && score != 0)
		{
			difficulty++; //ranks the difficulty up everytime the score is divisible by 60
		}
	if(!((game.greaterFive.get(game.greaterFive.size() - 1).xcorner > game.disk.xcorner + game.disk.width) || (game.greaterFive.get(game.greaterFive.size() - 1).xcorner + width < game.disk.xcorner))) //if the corners of the disk are not within the bounds of the corners of the stacks, then it does not intersect
	   {
		   score+= 10;
		   return true;
	   }
		
       return false;
    } 
}
