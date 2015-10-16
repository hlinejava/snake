package ua.al.snake.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import ua.al.snake.controller.Manager;
import ua.al.snake.model.Consts;
import ua.al.snake.model.Direction;
import ua.al.snake.model.Status;


public class Field extends JPanel {


	private int k;

	Field() {
		this.k = Consts.WIDTH;
		// addKeyListener(new FieldListener()); // add listener
		createGame();
		setFocusable(true); // set focus at JPanel for key listener
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (Manager.controller.getStatus() != Status.STOP) {
					
					Manager.controller.setStatus(Status.RUN);
					
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						Manager.controller.setDirBuf(Direction.UP);
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						Manager.controller.setDirBuf(Direction.DOWN);
					} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						Manager.controller.setDirBuf(Direction.LEFT);
					} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						Manager.controller.setDirBuf(Direction.RIGHT);
					} else if (e.getKeyCode() == 32) {

						if (Manager.controller.getStatus() == Status.RUN)
							Manager.controller.setStatus(Status.PAUSE);
						else if (Manager.controller.getStatus() == Status.PAUSE)
							Manager.controller.setStatus(Status.RUN);
					}
			
				}

			}
		}
				
		); // add listener
	} // Field

	private void createGame(){
		new Manager();
		Manager.controller.setField(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		setBackground(Color.BLACK);
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// paint field
		g.setColor(Color.RED);
		for (int i = 1; i <= Consts.SIZE; i++) {
			for (int j = 1; j <= Consts.SIZE; j++) {

				int x = k + i * k;
				int y = k + j * k;

				g.drawRect(x, y, k, k);// x,y,width,height
			}
		}

		// repaint snake
		Manager.controller.paintFood(g2d);
		Manager.controller.paintSnake(g2d);
	}
	

}
