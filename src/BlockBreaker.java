import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class BlockBreaker extends JPanel implements KeyListener{
	ArrayList<Block> blocks = new ArrayList<Block>();
	Block paddle;
	Thread thread;
	Animate animate;
	ArrayList<Block> balls = new ArrayList<Block>();
	ArrayList<Block> powerUps = new ArrayList<Block>();
	
	int ballSize =25;
	int size = 25;
	BlockBreaker(){
		for(int i = 0; i < 8; i++) {
			blocks.add(new Block(i*60+2, 0, 60, 25, "blue.png"));
		}
		for(int i = 0; i < 8; i++) {
			blocks.add(new Block(i*60+2, 25, 60, 25, "red.png"));
		}
		for(int i = 0; i < 8; i++) {
			blocks.add(new Block(i*60+2, 50, 60, 25, "yellow.png"));
		}
		for(int i = 0; i < 8; i++) {
			blocks.add(new Block(i*60+2, 75, 60, 25, "green.png"));
		}
		paddle = new Block(175, 480, 125, 25, "paddle.png");
		balls.add(new Block(230, 450, 25, 25, "ball.png"));
		Random random = new Random();
		
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Block b: blocks) {
			b.draw(g, this);
		}
		for(Block ba: balls) {
			ba.draw(g,  this);
		}
		for(Block p: powerUps) {
			p.draw(g, this);
		}
		paddle.draw(g, this);
	}
	public void update() {
		
		for(Block p: powerUps){
			p.y += 1;
			if(p.intersects(paddle) && !p.destroyed) {
				p.destroyed = true;
				balls.add(new Block(paddle.x + 100, 437, 25, 25, "ball.png"));
			}
		}
		
		for(Block ba : balls) {
			
			ba.x += ba.dx;
			
			if(ba.x >  (getWidth() - ballSize) && ba.x > 0 || ba.x < 0 )
				ba.dx *= -1;
			//if (ba.y > paddle.y)
				//ba.destroyed= true;
			//if(ba.y < 0 || ba.y > (getHeight() - ballSize) || ba.intersects(paddle))
			if(ba.y < 0 ||  ba.intersects(paddle))
				ba.dy *= -1;
			ba.y += ba.dy;
			
			for(Block b: blocks) {
				if((ba.intersects(b.left)  || ba.intersects(b.right)) && !b.destroyed) {
					b.destroyed = true;
					ba.dx *= -1;
					ba.dy *= -1;
					if (b.powerup == true) {
						powerUps.add(new Block(b.x, b.y, 25, 15, "extra.png"));
					}
				}
				else if(ba.intersects(b) && !b.destroyed){
					b.destroyed = true;
					ba.dx *= -1;
					ba.dy *= -1;
					if (b.powerup == true) {
						powerUps.add(new Block(b.x, b.y, 25, 15, "extra.png"));
					}
				}
			}
			//System.out.println("ball x value: " + ba.x);
		}
		/*
		for(Block p : powerUps){
			p.y+=1;
			if(p.intersects(paddle) && !p.destroyed){
				p.destroyed = true;
				balls.add(new Block(paddle.x+75, 437, 25, 25, "ball.png"));
			}
		}
		for(Block ba : balls){
			ba.x+=ba.dx;
			if(ba.x>(getWidth()-size) && ba.dx>0 || ba.x < 0)
				ba.dx*=-1;
			if(ba.y < 0 || ba.intersects(paddle))
				ba.dy*=-1;
			ba.y+=ba.dy;
			for(Block b : blocks){
				if((b.left.intersects(ba)||b.right.intersects(ba)) && !b.destroyed){
					ba.dx*=-1;
					b.destroyed = true;
					if(b.powerup)
						powerUps.add(new Block(b.x, b.y, 25, 19, "extra.png"));
				}
				else if(ba.intersects(b) && !b.destroyed){
					b.destroyed = true;
					ba.dy*=-1;
					if(b.powerup)
						powerUps.add(new Block(b.x, b.y, 25, 19, "extra.png"));
				}
			}
		}
		*/
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			animate = new Animate(this);
			thread = new Thread(animate);
			thread.start();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
			paddle.x -=15;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth()-paddle.width)) {
			paddle.x += 15;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
