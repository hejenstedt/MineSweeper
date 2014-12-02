package minesweeper;

public class PlayMineSweeper {

	Grid grid;
	UserInteractions userInteractions;

	public static void main(String[] args) {

		PlayMineSweeper playMineSweeper = new PlayMineSweeper();
		playMineSweeper.player();

	}

	public PlayMineSweeper() {
		grid = new Grid(10, 10, 10);
		userInteractions = new UserInteractions();
	}

	public void player() {

		grid.gridViewer();
		int[] coordinates = new int[2];
		// coordinates = userInteractions.chooseSquare();

		for (int i = 0; i < 10; i++) {
			coordinates[0] = i;
			for (int j = 0; j < 10; j++) {
				coordinates[1] = j;
				grid.viewSquare(coordinates);
			}
		}

	}

}
