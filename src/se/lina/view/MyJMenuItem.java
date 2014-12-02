package se.lina.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

import se.lina.controller.MineSweeperController;

public class MyJMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;
	private MineSweeperController controller;
	private int value;

	/**
	 * Value 1 = new game 
	 * Value 2 = exit game
	 * Value 3 = small size 10*10 10 bombs
	 * Value 4 = medium size 20*20 40 bombs
	 * Value 5 = large size 30*30 100 bombs
	 * @param text
	 * @param controller
	 * @param value
	 */
	public MyJMenuItem(String text, MineSweeperController controller, int value) {
		super(text, KeyEvent.VK_T);
		this.controller = controller;
		this.value = value;
		this.setFont(new Font("Arial", Font.BOLD, 14));
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onClick();
			}
		});

	}

	protected void onClick() {
		switch (value) {
		case 1:
			controller.newGame();
			break;
		case 2:
			System.exit(0);
			break;
		case 3:
			controller.changeSize(10, 10, 10);
			break;
		case 4:
			controller.changeSize(20, 20, 40);
			break;
		case 5:
			controller.changeSize(30, 30, 100);
			break;

		default:
			break;
		}
	}

}
