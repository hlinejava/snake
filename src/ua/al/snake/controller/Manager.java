package ua.al.snake.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;



import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ua.al.snake.model.Direction;
import ua.al.snake.model.Food;
import ua.al.snake.model.Snake;
import ua.al.snake.model.Status;
import ua.al.snake.view.Field;

public class Manager {

	private Status status;
	private Direction dir;
	private Direction dirBuf;
	
	public static Manager controller = null;
	
	private Food food = null; 
	private Snake snake = null;
	private Field field = null;

	
	public Manager() {
		controller = this;
		setStatus(Status.PAUSE);
		//snake
		snake = new Snake();
		//food
		food = getFood();
		
		createTimer(); 
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction newDir) {
		
		// we can't move back
		if ( (this.dir == Direction.UP && newDir != Direction.DOWN)
				|| (this.dir == Direction.DOWN && newDir != Direction.UP)
				|| (this.dir == Direction.LEFT && newDir != Direction.RIGHT)
				|| (this.dir == Direction.RIGHT && newDir != Direction.LEFT)
				|| (this.dir == null)
				)
			
			this.dir = newDir;
	}

	public Direction getDirBuf() {
		return dirBuf;
	}

	public void setDirBuf(Direction dirBuf) {
		this.dirBuf = dirBuf;
	}
	
	public Manager getController() {
		if (controller == null)	{
			controller = new Manager();
		}
		return controller;
	}
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Snake getSnake() {
		return snake;
	}
	
	public void setSnake(Snake snake) {
		this.snake = snake;
	}
	
	public Food getFood() {
		return Food.getFood();
	}

	public void setFood(Food food) {
		this.food = Food.getFood();
	}
	
	public void createTimer() {

		TimerTask task = new TimerTask() {
			public void run() {

				refresh();
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 0, 300);
	}
	
	
	public void refresh(){
		if (getStatus() == Status.RUN) {

			if (snake.move(this)){ 
				field.repaint();
			}
			else { 
				//JOptionPane.showMessageDialog(null, "Game over");
				if (JOptionPane.showConfirmDialog(field, 
						"New game?" ,"Game over!",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					controller = new Manager();
					controller.setField(field);
					field.repaint();
				}
				else{
					((JFrame)field.getParent().getParent().getParent().getParent()).dispose();
				}
			}

		}	
	}
	
	public void paintSnake(Graphics2D g){
		snake.paint(g);
	}
	
	public void paintFood(Graphics2D g){
		if (food!=null)
			food.paint(g, Color.GREEN);
	}


}
