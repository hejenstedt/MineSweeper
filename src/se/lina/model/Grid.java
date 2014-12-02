package se.lina.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Grid implements GameEventPublisher {
	// TODO: bryt ut metoder - kanske till en minesweeper model class?
	private int rows;
	private int columns;
	private int noOfBombs;
	private Tile[][] grid;
	private Random r;
	private Collection<GameObserver> observers;
	private boolean youDied;
	private ArrayList<Tile> tileList;

	public Grid(int rows, int columns, int noOfBombs) {
		this.rows = rows;
		this.columns = columns;
		this.noOfBombs = noOfBombs;
		youDied = false;
		observers = new ArrayList<GameObserver>();
		grid = new Tile[this.rows][this.columns];
		tileList = new ArrayList<>();
		fillGrid();
	}

	public void fillGrid() {
		fillGridWithTilesWithoutBombs();
		placeBombs();
		countBombs();
		publishUpdatedBoard();
	}

	void fillGridWithTilesWithoutBombs() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new Tile();
				tileList.add(grid[i][j]);
			}
		}
	}

	void placeBombs() {
		r = new Random();
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

	private void countBombs() {
		int noOfBombsAround;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				noOfBombsAround = 0;
				if (!grid[i][j].tileHasBomb()) {
					noOfBombsAround += tileHasBomb(i - 1, j - 1);
					noOfBombsAround += tileHasBomb(i - 1, j);
					noOfBombsAround += tileHasBomb(i - 1, j + 1);
					noOfBombsAround += tileHasBomb(i, j + 1);
					noOfBombsAround += tileHasBomb(i + 1, j + 1);
					noOfBombsAround += tileHasBomb(i + 1, j);
					noOfBombsAround += tileHasBomb(i + 1, j - 1);
					noOfBombsAround += tileHasBomb(i, j - 1);
					grid[i][j].setNoOfBombsAround(noOfBombsAround);
				}
			}
		}
	}

	private int tileHasBomb(int row, int column) {
		if (insideRange(row, column)) {
			if (grid[row][column].tileHasBomb()) {
				return 1;
			}
		}
		return 0;
	}

	public boolean isAllTilesClicked() {
		for (Tile tile : tileList) {
			if (!tile.hasBeenClicked()) {
				return false;
			}
		}
		youDied = false;
		return true;
	}

	public void calculateVisibleTiles(int row, int column) {

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

		publishUpdatedBoard();
	}

	private boolean lookaround(int row, int column) {
		boolean continueLooking = true;
		boolean openedBackwards = false;
		int i = 1;

		if (insideRange(row, column)) {

			// Look left
			do {
				if (isBeforeRange(column - i)
						|| tileHasBeenClicked(row, column - i)) {
					continueLooking = false;
					break;
				}
				if (tileValueEquals0(row, column - i)) {
					setClickOnTile(row, column - i);
					openedBackwards = true;
				} else {
					setClickOnTile(row, column - i);
					continueLooking = false;
				}
				i++;
			} while (continueLooking);
			i = 1;
			continueLooking = true;
			// look right
			do {
				if (column + i >= columns
						|| tileHasBeenClicked(row, column + i)) {
					continueLooking = false;
					break;
				}
				if (tileValueEquals0(row, column + i)) {
					setClickOnTile(row, column + i);
				} else {
					setClickOnTile(row, column + i);
					continueLooking = false;
				}
				i++;
			} while (continueLooking);
			i = 1;
			continueLooking = true;
			// Look up
			do {
				if (isBeforeRange(row - i)
						|| tileHasBeenClicked(row - i, column)) {
					continueLooking = false;
					break;
				}
				if (tileValueEquals0(row - i, column)) {
					setClickOnTile(row - i, column);
					openedBackwards = true;
				} else {
					setClickOnTile(row - i, column);
					continueLooking = false;
				}
				i++;
			} while (continueLooking);
			i = 1;
			// Look down
			continueLooking = true;
			do {
				if (row + i >= rows || tileHasBeenClicked(row + i, column)) {
					continueLooking = false;
					break;
				}
				if (tileValueEquals0(row + i, column)) {
					setClickOnTile(row + i, column);
				} else {
					setClickOnTile(row + i, column);
					continueLooking = false;
				}
				i++;
			} while (continueLooking);
		}
		return openedBackwards;
	}

	private boolean insideRange(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	private boolean tileHasBeenClicked(int row, int column) {
		return grid[row][column].hasBeenClicked();
	}

	private boolean isBeforeRange(int column) {
		return column < 0;
	}

	private void setClickOnTile(int row, int column) {
		grid[row][column].clickOnTile();
	}

	private boolean tileValueEquals0(int row, int column) {
		return grid[row][column].getNoOfBombsAround() == 0;
	}

	@Override
	public void publishUpdatedBoard() {
		observers.forEach(t -> t.updateBoard(grid));
	}

	@Override
	public void register(GameObserver gameObserver) {
		observers.add(gameObserver);
	}

	public Tile[][] getGrid() {
		return grid;
	}

	public void clickTileToCheckValue(int row, int column) {

		if (!grid[row][column].hasBeenClicked()) {
			if (grid[row][column].tileHasBomb()) {
				openAllTiles();
				youDied = true;
				publishYouDied();
			} else {

				setClickOnTile(row, column);

				if (tileValueEquals0(row, column)) {
					calculateVisibleTiles(row, column);
				}

				if (isAllTilesClicked()) {
					publishYouWon();
				}

				publishUpdatedBoard();
			}
		}
	}

	private void openAllTiles() {
		for (Tile tile : tileList) {
			if (!tile.hasBeenClicked()) {
				tile.clickOnTile();
			}
		}
	}

	private void publishYouWon() {
		observers.forEach(t -> t.gameOver(grid, youDied));
	}

	private void publishYouDied() {
		observers.forEach(t -> t.gameOver(grid, youDied));
	}

	public void markTileWithBomb(int row, int column) {
		if (!grid[row][column].hasBeenClicked()) {
			grid[row][column].markTileAsBomb();
			publishUpdatedBoard();
		}
	}

	public void newGame() {
		for (Tile tile : tileList) {
			tile.resetTile();
		}

		Collections.shuffle(tileList, r);

		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = tileList.get(count);
				count++;
			}
		}

		countBombs();
		publishUpdatedBoard();
	}

	void showGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void changeSizeOfBoard(int rows, int columns, int noOfBombs) {
		this.rows = rows;
		this.columns = columns;
		this.noOfBombs = noOfBombs;
		grid = new Tile[rows][columns];
		fillGridWithTilesWithoutBombs();
		placeBombs();
		countBombs();
		publishNewBoardSize();
	}

	private void publishNewBoardSize() {
		observers.forEach(t -> t.newBoardSize(grid));
	}
}
