package minesweeper;

enum Ruta {

	Empty('_'), Bomb('X'), One('1'), Two('2'), Three('3'), Four('4');

	private char charFill;

	private Ruta(char charFill) {
		this.setCharFill(charFill);
	}

	public char getCharFill() {
		return charFill;
	}

	private void setCharFill(char charFill) {
		this.charFill = charFill;
	}
}
