package se.lina.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GridFactory {

	MineSweeperTile[][] grid;
	int noOfBombs;
	private int columns;
	private int rows;
	private ArrayList<MineSweeperTile> tileList;

	public GridFactory(int rows, int columns, int noOfBombs) {
		grid = new MineSweeperTile[rows][columns];
		this.noOfBombs = noOfBombs;
		this.rows = rows;
		this.columns = columns;
		tileList = new ArrayList<>();
		createGrid();
	}

	MineSweeperTile[][] getGrid() {
		return grid;
	}

	ArrayList<MineSweeperTile> getTileList() {
		return tileList;
	}

	void newGame() {
		for (MineSweeperTile tile : tileList) {
			tile.resetTile();
		}

		Collections.shuffle(tileList);

		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = tileList.get(count);
				count++;
			}
		}
		countBombs();

	}

	void countBombs() {
		int noOfBombsAround;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				noOfBombsAround = 0;
				if (!grid[i][j].tileHasBomb()) {
					noOfBombsAround += tileHasBomb(DirectionsInAGrid
							.getCoordinates(DirectionsInAGrid.UPLEFT, i, j));
					noOfBombsAround += tileHasBomb(DirectionsInAGrid
							.getCoordinates(DirectionsInAGrid.UP, i, j));
					noOfBombsAround += tileHasBomb(DirectionsInAGrid
							.getCoordinates(DirectionsInAGrid.UPRIGHT, i, j));
					noOfBombsAround += tileHasBomb(DirectionsInAGrid
							.getCoordinates(DirectionsInAGrid.RIGHT, i, j));
					noOfBombsAround += tileHasBomb(DirectionsInAGrid
							.getCoordinates(DirectionsInAGrid.DOWNRIGHT, i, j));
					noOfBombsAround += tileHasBomb(DirectionsInAGrid
							.getCoordinates(DirectionsInAGrid.DOWN, i, j));
					noOfBombsAround += tileHasBomb(DirectionsInAGrid
							.getCoordinates(DirectionsInAGrid.DOWNLEFT, i, j));
					noOfBombsAround += tileHasBomb(DirectionsInAGrid
							.getCoordinates(DirectionsInAGrid.LEFT, i, j));
					grid[i][j].setNoOfBombsAround(noOfBombsAround);
				}
			}
		}
	}

	private void createGrid() {
		fillGridWithTilesWithoutBombs();
		placeBombs();
		// placeBombsWithPurpose();
		countBombs();
	}

	private void fillGridWithTilesWithoutBombs() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new MineSweeperTile();
				tileList.add(grid[i][j]);
			}
		}
	}

	private int tileHasBomb(int[] coordinates) {
		int row = coordinates[0];
		int column = coordinates[1];
		if (row >= 0 && row < rows && column >= 0 && column < columns) {
			if (grid[row][column].tileHasBomb()) {
				return 1;
			}
		}
		return 0;
	}

	private void placeBombs() {
		Random r = new Random();
		int row;
		int column;
		for (int i = 0; i < noOfBombs; i++) {
			do {
				row = r.nextInt(rows);
				column = r.nextInt(columns);
			} while (grid[row][column].tileHasBomb());
			grid[row][column].putBombOnTile();
		}
	}

	// private void placeBombsWithPurpose() {
	// grid[1][1].putBombOnTile();
	// grid[1][8].putBombOnTile();
	// grid[2][2].putBombOnTile();
	// grid[8][1].putBombOnTile();
	// }

}
