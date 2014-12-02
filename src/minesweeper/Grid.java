package minesweeper;

import java.util.Random;

public class Grid {

	char[][] layout;
	boolean[][] viewedGrid;
	int noOfBombs;
	int height;
	int width;

	public Grid(int width, int height, int noOfBombs) {
		this.height = height;
		this.width = width;
		this.noOfBombs = noOfBombs;
		layout = new char[this.height][this.width];
		initiateGrid();
	}

	public Grid(int width, int noOfBombs) {
		this.height = width;
		this.width = width;
		this.noOfBombs = noOfBombs;
		layout = new char[this.height][this.width];
		initiateGrid();
	}

	public Grid() {
		this.height = 5;
		this.width = 5;
		noOfBombs = 2;
		layout = new char[this.height][this.width];
		initiateGrid();
	}

	private void initiateGrid() {
		setViewedGrid();
		fillGrid();
		placeBombs();
		setNumbers();
	}

	private void setViewedGrid() {
		viewedGrid = new boolean[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				viewedGrid[i][j] = false;
			}
		}
	}

	private void fillGrid() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				layout[i][j] = '0';
			}
		}
	}

	public void gridViewer() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (viewedGrid[i][j]) {
					System.out.print(layout[i][j] + " ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public void viewGrid() {

		for (char[] cs : layout) {
			for (char c : cs) {
				System.out.print(c + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

	// antal bomber

	// placera bomber

	private void placeBombs() {
		Random r = new Random();
		int tempRow;
		int tempColumn;

		for (int i = 0; i < noOfBombs; i++) {

			tempRow = r.nextInt(height);
			tempColumn = r.nextInt(width);
			if (layout[tempRow][tempColumn] == '0') {
				layout[tempRow][tempColumn] = Ruta.Bomb.getCharFill();
			} else {
				i--;
			}
		}
	}

	public void viewSquare(int[] coordinates) {
		int i = coordinates[0];
		int j = coordinates[1];
		viewedGrid[i][j] = true;
		if (isBomb(i, j)) {
		}
		ifEmpty(i, j);
		gridViewer();
	}

	private void ifEmpty(int i, int j) {
		if (isEmpty(i, j)) {
			viewSquaresAround(i, j);

			char[] temp = squaresAround(i, j);
			
			
			
			//TODO: Just nu oändlig loop som ger stack overflow...
		}
	}

	private boolean isBomb(int i, int j) {
		if (layout[i][j] == 'X') {
			viewGrid();
			return true;
		}
		return false;
	}

	private char[] squaresAround(int i, int j) {
		char[] squaresAround = new char[8];
		int count = 0;

		/******************************
		 * ROW ABOVE
		 ******************************/
		if (i > 0) {
			squaresAround[count] = layout[i - 1][j];
			count++;
			if (j < width - 1) {
				squaresAround[count] = layout[i - 1][j + 1];
				count++;
			}
			if (j > 0) {
				squaresAround[count] = layout[i - 1][j - 1];
				count++;
			}
		}

		/******************************
		 * SAME ROW
		 ******************************/
		if (j > 0) {
			squaresAround[count] = layout[i][j - 1];
			count++;
		}

		if (j < width - 1) {
			squaresAround[count] = layout[i][j + 1];
			count++;
		}

		/******************************
		 * ROW BELOW
		 ******************************/
		if (i < height - 1) {
			if (j > 0) {
				squaresAround[count] = layout[i + 1][j - 1];
				count++;
			}
			squaresAround[count] = layout[i + 1][j];
			count++;
			if (j < width - 1) {
				squaresAround[count] = layout[i + 1][j + 1];
				count++;
			}
		}

		return squaresAround;

	}

	private void viewSquaresAround(int i, int j) {

		/******************************
		 * ROW ABOVE
		 ******************************/
		if (i > 0) {
			viewedGrid[i - 1][j] = true;
			
			if (j < width - 1) {
				viewedGrid[i - 1][j + 1] = true;

			}
			if (j > 0) {
				viewedGrid[i - 1][j - 1] = true;

			}
		}

		/******************************
		 * SAME ROW
		 ******************************/
		if (j > 0) {
			viewedGrid[i][j - 1] = true;

		}

		if (j < width - 1) {
			viewedGrid[i][j + 1] = true;

		}

		/******************************
		 * ROW BELOW
		 ******************************/
		if (i < height - 1) {
			if (j > 0) {
				viewedGrid[i + 1][j - 1] = true;

			}

			viewedGrid[i + 1][j] = true;

			if (j < width - 1) {
				viewedGrid[i + 1][j + 1] = true;

			}
		}

	}

	private void setNumbers() {
		int noOfBombsAround = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (layout[i][j] == '0') {

					char[] temp = squaresAround(i, j);
					for (char c : temp) {
						if (c == 'X') {
							noOfBombsAround++;
						}
					}

					char setNumber = Integer.toString(noOfBombsAround)
							.charAt(0);
					if (setNumber == '0') {
						setNumber = '-';
					}

					layout[i][j] = setNumber;
				}
				noOfBombsAround = 0;
			}
		}
	}

	private boolean isEmpty(int i, int j) {

		if (layout[i][j] == '-') {
			return true;
		}
		return false;
	}

}
