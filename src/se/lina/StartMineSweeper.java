package se.lina;

import javax.swing.SwingUtilities;

import se.lina.controller.MineSweeperController;
import se.lina.model.Grid;
import se.lina.model.TimeAdministrator;
import se.lina.view.MainWindow;

public class StartMineSweeper {
	private Grid grid;
	private TimeAdministrator timeAdministrator;

	public StartMineSweeper() {
	}

	public static void main(String[] args) {
		StartMineSweeper mineSweeper = new StartMineSweeper();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				mineSweeper.startGame();
			}
		});
	}

	private void startGame() {
		int rows = 10;
		int columns = 10;
		int noOfBombs = 5;
		grid = new Grid(rows, columns, noOfBombs);
		timeAdministrator = new TimeAdministrator();
		MineSweeperController controller = new MineSweeperController(grid, timeAdministrator);
		MainWindow mainWindow = new MainWindow(rows, columns, controller);
		grid.register(mainWindow);
		timeAdministrator.register(mainWindow);
	}

}
