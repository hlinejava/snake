package ua.al.snake.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;


import ua.al.snake.controller.Manager;
import ua.al.snake.model.SnakePart;

public class Snake {

	private ArrayList<SnakePart> body;
	
	public Snake(){
		body = new ArrayList<SnakePart>();
		//
		SnakePart sp = new SnakePart();
		body.add(sp);
		
	}

	private boolean canMove(Manager controller) {
		SnakePart head = body.get(0);
		
		// new part at snake
		if (newPartAtBody(controller, head)) {
			System.out.println("Error: newPartAtBody");
			return false;
		}

		// out of field
		if (controller.getDir() == Direction.UP) {
			if ((head.getY() - 1) == 0){
				System.out.println("Error: out of field");
				return false;
			}
		} else if (controller.getDir() == Direction.DOWN) {
			if ((head.getY() + 1) > Consts.SIZE){
				System.out.println("Error: out of field");
				return false;
			}
		} else if (controller.getDir() == Direction.LEFT) {
			if ((head.getX() - 1) == 0){
				System.out.println("Error: out of field");
				return false;
			}
		} else if (controller.getDir() == Direction.RIGHT) {
			if ((head.getX() + 1) > Consts.SIZE){
				System.out.println("Error: out of field " + head.getX());
				return false;
			}
		}

		return true;
	}

	private boolean newPartAtBody(Manager controller, SnakePart head){
		
		for (SnakePart p:body){
			if (p.isNewPart()) {
				
				if (controller.getDir() == Direction.UP) {
					if ((head.getY() - 1 == p.getY())
							&& (head.getX() == p.getX()))
						return true;
				} else if (controller.getDir() == Direction.DOWN) {
					if ((head.getY() + 1 == p.getY())
							&& (head.getX() == p.getX()))
						return true;
				} else if (controller.getDir() == Direction.LEFT) {
					if ((head.getX() - 1 == p.getX())
							&& (head.getY() == p.getY()))
						return true;
				} else if (controller.getDir() == Direction.RIGHT) {
					if ((head.getX() + 1 == p.getX())
							&& (head.getY() == p.getY()))
						return true;
				}
			}
		
		}
		
		return false;
	}
	
	public boolean move(Manager controller){

		// direction
		if (controller.getDir() != controller.getDirBuf()) {
			controller.setDir(controller.getDirBuf());
		}
		// if can't move - STOP
		if (canMove(controller)){
			// clear old
			for (int i = body.size() - 1; i >= 0; i--) {
				if (!body.get(i).isNewPart()) {
					body.remove(i);
				}
			}
			
			SnakePart h = body.get(0);// head
			
			//last part mark "not new" if XY of food == XY of head
			SnakePart p = body.get(body.size() - 1);
			if (!(controller.getFood().getX() == h.getX() && controller.getFood().getY() == h.getY()))
				p.setNewPart(false);
			else{
				
				controller.getFood().destroyFood();
				//food
				Manager.controller.setFood(Manager.controller.getFood());
				
			}				
			
			// move
			SnakePart newP = null;
			if (controller.getDir() == Direction.UP) {
				newP = new SnakePart(h.getX(), h.getY() - 1);

			} else if (controller.getDir() == Direction.DOWN) {
				newP = new SnakePart(h.getX(), h.getY() + 1);

			} else if (controller.getDir() == Direction.LEFT) {
				newP = new SnakePart(h.getX() - 1, h.getY());

			} else if (controller.getDir() == Direction.RIGHT) {
				newP = new SnakePart(h.getX() + 1, h.getY());
			}
			
			// add new part
			if (newP != null) {
				body.add(0, newP);
				
				return true;
			}
			else 
				return false;
			
		}
		else{
			controller.setStatus(Status.STOP);  
			return false;
		}
		
	}
		
	public ArrayList<SnakePart> getBody() {
		return body;
	}
	
	public void paint(Graphics2D g2d){
	
		for (SnakePart p : getBody()) {
			p.paint(g2d, p.isNewPart() ? Color.GRAY : Color.black);
		}
	}
}
