package se.lina.controller;

import se.lina.model.MineSweeperModel;
import se.lina.model.TimeAdministrator;

public class MineSweeperController {

	private MineSweeperModel grid;
	private int noOfClicks;
//	private long start;
//	private long stop;
	private long time;
	private TimeAdministrator timeAdministrator;

	public MineSweeperController(MineSweeperModel grid, TimeAdministrator timeAdministrator) {
		this.grid = grid;
		this.timeAdministrator= timeAdministrator;
		noOfClicks = 0;
	}

	public void tileClicked(int row, int column) {
		grid.clickTileToCheckValue(row, column);
		noOfClicks++;
		if (noOfClicks == 1) {
			startTimer();
		}
	}

	private void startTimer() {
		timeAdministrator.startTimer();
//		start = System.currentTimeMillis();
	}

	public void gameOver(boolean youDied) {
		timeAdministrator.stopTime();
//		stop = System.currentTimeMillis();
//		time = stop - start;
//		String timeAsString;
//		long rest = time % 60000;
//		long min = (time - rest) / 60000;
//		long sec = Math.round(rest / 1000);
//		timeAsString = min + " min " + sec + " sec";

	}

	public long getTime() {
		return time;
	}

	public void startGame() {
		grid.publishUpdatedBoard();
	}

	public void markAsBomb(int row, int column) {
		grid.markTileWithBomb(row, column);
	}

	public void newGame() {
		timeAdministrator.resetValues();
		grid.newGame();
	}

	public void changeSize(int row, int column, int noOfBombs) {
		timeAdministrator.resetValues();
		grid.changeSizeOfBoard(row, column, noOfBombs);
	}

}
