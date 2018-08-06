package stack;

import java.awt.Rectangle;

public class Stack {
	Rectangle topface;
	int x11 = 150, x12 = 150, y11 = 660, y12 = 640; //line base1
	int x21 = x11 + 100, x22 = x12 + 100, y21 = 660, y22 = 640; //line base2
	int xcorner = 150;
	int ycorner = 540;
	int width = 100;
	int height = 100;
	public Stack() //default base stack
	{
		topface = new Rectangle(xcorner, ycorner, 100, 100);
	}
	public Stack(int ax11, int ay11, int ax12, int ay12, int ax21, int ay21, int ax22, int ay22, int awidth, int aheight, int axcorner, int aycorner)
	{
		x11 = ax11;
		y11 = ay11;
		x12 = ax12;
		y12 = ay12;
		x21 = ax21;
		y21 = ay21;
		x22 = ax22;
		y22 = ay22;
		xcorner = axcorner;
		ycorner = aycorner;
		width = awidth;
		height = aheight;
		topface = new Rectangle(xcorner, ycorner, width, height);
	}
	public void move()
	{
		
	}
}
