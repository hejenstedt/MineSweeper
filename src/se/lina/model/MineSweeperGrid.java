package se.lina.model;

import java.util.ArrayList;

public class MineSweeperGrid {

	private int rows;
	private int columns;
	private MineSweeperTile[][] grid;
	private ArrayList<MineSweeperTile> tileList;
	private GridFactory gf;

	public MineSweeperGrid(int rows, int columns, int noOfBombs) {
		super();
		this.rows = rows;
		this.columns = columns;
		gf = new GridFactory(rows, columns, noOfBombs);
		this.tileList = gf.getTileList();
		this.grid = gf.getGrid();
	}

	MineSweeperTile[][] getGrid() {
		return grid;
	}

	public static void main(String[] args) {
		MineSweeperGrid mineSweeperGrid = new MineSweeperGrid(10, 10, 10);

		mineSweeperGrid.showGrid();
	}

	boolean isAllTilesClicked() {
		for (MineSweeperTile tile : tileList) {
			if (!tile.hasBeenClicked()) {
				return false;
			}
		}
		return true;
	}

	void openAllTiles() {
		for (MineSweeperTile tile : tileList) {
			if (!tile.hasBeenClicked()) {
				tile.clickOnTile();
			}
		}
	}

	void changeSizeOfBoard(int rows, int columns, int noOfBombs) {
		this.rows = rows;
		this.columns = columns;
		gf = new GridFactory(rows, columns, noOfBombs);
		grid = gf.getGrid();
		tileList = gf.getTileList();
	}

	void newGame() {
		gf.newGame();
		grid = gf.getGrid();
		tileList = gf.getTileList();
	}

	void calculateVisibleTiles(int row, int column) {

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].hasBeenClicked()
						&& grid[i][j].toString().equals("0")) {
					if (lookaround(i, j)) {
						i = 0;
						j = 0;
					}
				}
			}
		}

	}

	private void showGrid() {

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	private boolean insideRange(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	private boolean tileHasBeenClicked(int[] coordinates) {
		return grid[coordinates[0]][coordinates[1]].hasBeenClicked();
	}

	private boolean isOutsideRange(int[] coordinates) {
		int row = coordinates[0];
		int column = coordinates[1];
		return !(row >= 0 && row < rows && column >= 0 && column < columns);

	}

	private boolean lookaround(int row, int column) {
		boolean openedBackwards = false;

		if (insideRange(row, column)) {

			int[] direction = coordinatesForDirection(DirectionsInAGrid.LEFT,
					row, column);
			openedBackwards = lookeInOneDirection(direction, openedBackwards);
			direction = coordinatesForDirection(DirectionsInAGrid.UPLEFT, row,
					column);
			openedBackwards = lookeInOneDirection(direction, openedBackwards);
			direction = coordinatesForDirection(DirectionsInAGrid.UP, row,
					column);
			openedBackwards = lookeInOneDirection(direction, openedBackwards);
			direction = coordinatesForDirection(DirectionsInAGrid.UPRIGHT, row,
					column);
			openedBackwards = lookeInOneDirection(direction, openedBackwards);
			direction = coordinatesForDirection(DirectionsInAGrid.RIGHT, row,
					column);
			openedBackwards = lookeInOneDirection(direction, openedBackwards);
			direction = coordinatesForDirection(DirectionsInAGrid.DOWNRIGHT,
					row, column);
			openedBackwards = lookeInOneDirection(direction, openedBackwards);
			direction = coordinatesForDirection(DirectionsInAGrid.DOWN, row,
					column);
			openedBackwards = lookeInOneDirection(direction, openedBackwards);
			direction = coordinatesForDirection(DirectionsInAGrid.DOWNLEFT,
					row, column);
			openedBackwards = lookeInOneDirection(direction, openedBackwards);

		}
		return openedBackwards;
	}

	private boolean lookeInOneDirection(int[] direction, boolean openedBackwards) {

		boolean continueLooking = true;

		do {

			if (isOutsideRange(direction) || tileHasBeenClicked(direction)) {
				break;
			}
			if (tileValueEquals0(direction)) {
				setClickOnTile(direction);
				openedBackwards = true;
			} else {
				setClickOnTile(direction);
				continueLooking = false;
			}
		} while (continueLooking);
		return openedBackwards;
	}

	private int[] coordinatesForDirection(DirectionsInAGrid direction, int row,
			int column) {
		int[] left = DirectionsInAGrid.getCoordinates(direction, row, column);
		return left;
	}

	private void setClickOnTile(int[] coordinates) {
		grid[coordinates[0]][coordinates[1]].clickOnTile();
	}

	private boolean tileValueEquals0(int[] coordinates) {
		return grid[coordinates[0]][coordinates[1]].getNoOfBombsAround() == 0;
	}

}
