package ua.al.snake;

import javax.swing.SwingUtilities;

import ua.al.snake.view.Window;

public class Main {

	public static void main(String[] args) {
		//
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new Window();
            }
        });

	}

}
