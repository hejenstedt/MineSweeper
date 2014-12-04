package se.lina.model;

public interface GameObserver {

	void updateBoard(MineSweeperTile[][] grid);

	void gameOver(MineSweeperTile[][] grid, boolean youDied);

	void newBoardSize(MineSweeperTile[][] grid);
}
