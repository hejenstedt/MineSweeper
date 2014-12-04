package se.lina.model;

public enum DirectionsInAGrid {

	UPLEFT(-1, -1), UP(-1, 0), UPRIGHT(-1, +1), RIGHT(0, +1), DOWNRIGHT(+1, +1), DOWN(
			+1, 0), DOWNLEFT(+1, -1), LEFT(0, -1);

	private int row;
	private int column;

	private DirectionsInAGrid(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public static int[] getCoordinates(DirectionsInAGrid direction, int r, int c) {
		int[] coordinates = new int[2];

		for (DirectionsInAGrid dir : values()) {
			if (dir.equals(direction)) {
				coordinates[0] = dir.getRow() + r;
				coordinates[1] = dir.getColumn() + c;
				break;
			}
		}

		return coordinates;

	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}
/**
 * 
 WalkOut("Utforska", 1), ChangeFloor("Byt våning", 2), ChangeHouse( "Byt hus",
 * 3), ShowMe("Visa min profil och var jag är", 4), Look( "Se värld", 5),
 * Help("Hjälp", 6), Quit("Avsluta", 7), Unknown(); private String commando;
 * private int choice;
 * 
 * private Actions(String commando, int choice) { this.commando = commando;
 * this.choice = choice; }
 * 
 * public static Actions getAction(String action) { for (Actions a : values()) {
 * if (a.equals(Unknown)) { continue; } if (a.commando.equalsIgnoreCase(action))
 * { return a; }
 * 
 * } if (action.length() == 1) {
 * 
 * for (Actions a : values()) { if (a.choice == Integer.valueOf(action)) {
 * return a; } } } return Unknown; }
 * 
 * @Override public String toString() { return commando; }
 * 
 *           public int getChoice() { return choice; }
 * 
 *           public String getCommando() { return commando; }
 * 
 *           }
 */
