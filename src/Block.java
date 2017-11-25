import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Block extends Rectangle {
	Image pic;
	double dx=3;
	double dy=3;
	Boolean destroyed = false;
	
	Rectangle left;
	Rectangle right;
	
	Boolean powerup = false;
	Block(int a, int b, int w, int h, String s){
		x = a;
		y = b;
		width = w;
		height = h;
		pic = Toolkit.getDefaultToolkit().getImage(s);
		left = new Rectangle(a-1, b, 1, h);
		right = new Rectangle(a+w+1, b, 1, h);
	}
	
	public void draw(Graphics g, Component c) {
		if(!destroyed)
			g.drawImage(pic, x, y, width, height, c);
	}
}
