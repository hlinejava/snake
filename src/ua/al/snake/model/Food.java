package ua.al.snake.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import ua.al.snake.controller.Manager;

public class Food extends GraphObject{

	public static Food f = null;
	
	
	private Food() {
		Random rnd = new Random();
		
		do {

			this.x = rnd.nextInt(Consts.SIZE) + 1;
			this.y = rnd.nextInt(Consts.SIZE) + 1;
			
		} while (!isRightFood());
			
	}
	
	

	public static Food getFood() {
		if (f==null){
			f = new Food();
		}
		return f;
	}
	
	private boolean isRightFood(){
		boolean flag = true;
		for(SnakePart p: Manager.controller.getSnake().getBody()) {
			if(x == p.getX() && y == p.getY()){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public void destroyFood(){
		f = null;
	}



	@Override
	public void paint(Graphics2D g2d, Color c) {
		int k = Consts.WIDTH;
		g2d.setColor(c);
		g2d.fillOval(k + 1 + x * k,
				k + 1 + y * k,
				k - 2, k - 2);// x,y.width,height
	}

}
