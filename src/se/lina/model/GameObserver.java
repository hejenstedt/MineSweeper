package se.lina.model;

public interface GameObserver {

	void updateBoard(Tile[][] grid);

	void gameOver(Tile[][] grid, boolean youDied);

	void newBoardSize(Tile[][] grid);
}
