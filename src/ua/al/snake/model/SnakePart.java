package ua.al.snake.model;

import java.util.Random;

public class SnakePart extends GraphObject{
	
	private boolean newPart;

	SnakePart(int i, int j){
		this.x = i;
		this.y = j;
		this.newPart = true;
	};
	
	SnakePart(){
		Random rnd = new Random();
		this.x = rnd.nextInt(Consts.SIZE)+1;
		this.y = rnd.nextInt(Consts.SIZE)+1;
		this.newPart = true;
		
	}
	
	public boolean isNewPart() {
		return newPart;
	}

	public void setNewPart(boolean newPart) {
		this.newPart = newPart;
	}

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
}
