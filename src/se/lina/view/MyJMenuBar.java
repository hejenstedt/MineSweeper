package se.lina.view;

import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import se.lina.controller.MineSweeperController;

public class MyJMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private MineSweeperController controller;
	private JMenu menu;
	private MyJMenuItem menuItemNewGame;
	private MyJMenuItem menuItemExit;
	private MyJMenuItem menuItemSmallSize;
	private JMenu menuChangeSize;
	private MyJMenuItem menuItemMediumSize;
	private MyJMenuItem menuItemLargeSize;

	public MyJMenuBar(MineSweeperController controller) {
		super();
		this.controller = controller;
		addMenuItems();
	}

	private void addMenuItems() {
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.setFont(new Font("Arial", Font.BOLD, 14));
		this.add(menu);
		
		menuItemNewGame = new MyJMenuItem("Start new game", controller, 1);
		menu.add(menuItemNewGame);

		menuChangeSize = new JMenu("Change Boardsize");
		menuChangeSize.setMnemonic(KeyEvent.VK_A);
		menuChangeSize.setFont(new Font("Arial", Font.BOLD, 14));
		menu.add(menuChangeSize);
		
		menuItemSmallSize = new MyJMenuItem("Small", controller, 3);
		menuChangeSize.add(menuItemSmallSize);
		
		menuItemMediumSize = new MyJMenuItem("Medium", controller, 4);
		menuChangeSize.add(menuItemMediumSize);
		
		menuItemLargeSize = new MyJMenuItem("Large", controller, 5);
		menuChangeSize.add(menuItemLargeSize);

		menuItemExit = new MyJMenuItem("Exit game", controller, 2);
		menu.add(menuItemExit);
	}

}
