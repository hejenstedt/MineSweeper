package se.lina.model;

import java.util.ArrayList;
import java.util.Collection;

public class MineSweeperModel implements GameEventPublisher {

	private MineSweeperTile[][] grid;
	private Collection<GameObserver> observers;
	private boolean youDied;
	private MineSweeperGrid mineSweeperGrid;

	public MineSweeperModel(int rows, int columns, int noOfBombs) {
		youDied = false;
		observers = new ArrayList<GameObserver>();
		mineSweeperGrid = new MineSweeperGrid(rows, columns, noOfBombs);
		initateGrid();
	}

	public MineSweeperTile[][] getGrid() {
		return grid;
	}

	public void clickTileToCheckValue(int row, int column) {

		if (!grid[row][column].hasBeenClicked()) {
			if (grid[row][column].tileHasBomb()) {
				mineSweeperGrid.openAllTiles();
				youDied = true;
				publishYouDied();
			} else {

				setClickOnTile(row, column);

				if (tileValueEquals0(row, column)) {
					calculateVisibleTiles(row, column);
				}

				if (mineSweeperGrid.isAllTilesClicked()) {
					publishYouWon();
				}

				publishUpdatedBoard();
			}
		}
	}

	public void markTileWithBomb(int row, int column) {
		if (!grid[row][column].hasBeenClicked()) {
			grid[row][column].markTileAsBomb();
			publishUpdatedBoard();
		}
	}

	public void newGame() {
		mineSweeperGrid.newGame();
		publishUpdatedBoard();
	}

	public void changeSizeOfBoard(int rows, int columns, int noOfBombs) {
		mineSweeperGrid.changeSizeOfBoard(rows, columns, noOfBombs);
		grid = mineSweeperGrid.getGrid();
		publishNewBoardSize();
	}

	private void calculateVisibleTiles(int row, int column) {
		mineSweeperGrid.calculateVisibleTiles(row, column);
		publishUpdatedBoard();
	}

	private void initateGrid() {
		grid = mineSweeperGrid.getGrid();
		publishUpdatedBoard();
	}

	private void setClickOnTile(int row, int column) {
		grid[row][column].clickOnTile();
	}

	private boolean tileValueEquals0(int row, int column) {
		return grid[row][column].getNoOfBombsAround() == 0;
	}

	private void publishYouWon() {
		System.out.println("publishYouWon");
		observers.forEach(t -> t.gameOver(grid, youDied));
	}

	private void publishYouDied() {
		observers.forEach(t -> t.gameOver(grid, youDied));
	}

	private void publishNewBoardSize() {
		observers.forEach(t -> t.newBoardSize(grid));
	}

	@Override
	public void publishUpdatedBoard() {
		observers.forEach(t -> t.updateBoard(grid));
	}

	@Override
	public void register(GameObserver gameObserver) {
		observers.add(gameObserver);
	}
}
