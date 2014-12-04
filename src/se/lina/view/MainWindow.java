package se.lina.view;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import se.lina.controller.MineSweeperController;
import se.lina.model.GameObserver;
import se.lina.model.MineSweeperTile;
import se.lina.model.TimeObserver;

public class MainWindow implements GameObserver, TimeObserver {

	int rows;
	int columns;
	private JFrame mainFrame;
	private MyJMenuBar menubar;
	private GameJPanel gamePanel;
	private MineSweeperController controller;

	
	public MainWindow(int rows, int columns, MineSweeperController controller) {
		this.rows = rows;
		this.columns = columns;
		this.controller = controller;
		initiateMainFrame();
	}

	private void initiateMainFrame() {
		mainFrame = new JFrame();
		mainFrame.setLayout(new FlowLayout());
		menubar = new MyJMenuBar(controller);
		mainFrame.setJMenuBar(menubar);
		gamePanel = new GameJPanel(rows, columns, controller);
		mainFrame.add(gamePanel);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	@Override
	public void updateBoard(MineSweeperTile[][] grid) {
		gamePanel.updateValuesOnBoard(grid);
	}

	@Override
	public void gameOver(MineSweeperTile[][] grid, boolean youDied) {
		gamePanel.updateValuesOnBoard(grid);
		controller.gameOver(youDied);
		// TODO: do something
	}

	@Override
	public void newBoardSize(MineSweeperTile[][] grid) {
		this.rows = grid.length;
		this.columns= grid[0].length;
//		buttonSize = windowHeight/noOfRows;

		mainFrame.remove(gamePanel);
		gamePanel = new GameJPanel(rows, columns, controller);
		mainFrame.add(gamePanel);
		
		mainFrame.pack();

		
		
	}

	@Override
	public void showEndTime(String timeAsString) {
		JOptionPane.showMessageDialog(null, timeAsString); 
	}

	@Override
	public void showTimer() {
		// TODO Auto-generated method stub
		
	}

}
