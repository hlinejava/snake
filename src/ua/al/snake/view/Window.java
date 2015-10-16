package ua.al.snake.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.al.snake.model.Consts;

public class Window extends JFrame {

	public Window() {

		super("Game -SNAKE-");
		
		// add JPanel "Field"
		JPanel cp = new JPanel(new BorderLayout());
		setContentPane(cp);
		Field f = new Field();
		
		cp.add(f);
		cp.setBackground(Color.black);

		setSize(Consts.SIZE*Consts.WIDTH + 100, Consts.SIZE*Consts.WIDTH+100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setVisible(true);

	}
}
