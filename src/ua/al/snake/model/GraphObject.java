package ua.al.snake.model;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class GraphObject {
	protected int x;
	protected int y;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
	public void paint(Graphics2D g2d, Color c){
		int k = Consts.WIDTH;
		
		g2d.setColor(c);
		
		g2d.fillRect(k + 1 + x * k,
				k + 1 + y * k,
				k - 2,
				k - 2);// x,y.width,height
	}
	
}
