package se.lina.model;

public class Tile {

	private boolean hasBomb;
	private int noOfBombsAround;
	private boolean markedAsBomb;
	private boolean hasBeenClicked;

	public Tile() {
		this.hasBomb = false;
		this.noOfBombsAround = 0;
		markedAsBomb = false;
		hasBeenClicked = false;
	}

	public int getNoOfBombsAround() {
		return noOfBombsAround;
	}

	public boolean hasBeenClicked() {
		return hasBeenClicked;
	}

	public void resetTile() {
		noOfBombsAround = 0;
		markedAsBomb = false;
		hasBeenClicked = false;
	}

	public void setNoOfBombsAround(int noOfBombsAround) {
		this.noOfBombsAround = noOfBombsAround;
	}

	void putBombOnTile() {
		hasBomb = true;
	}

	boolean tileHasBomb() {
		return hasBomb;
	}

	void clickOnTile() {
		hasBeenClicked = true;
		if (hasBomb) {
			// TODO: game should end - your're dead
		}
	}

	void markTileAsBomb() {
		hasBeenClicked = true;
		markedAsBomb = true;
	}

	@Override
	public String toString() {
		if (hasBeenClicked) {

			if (markedAsBomb) {
				return "X";
			}
			if (hasBomb) {
				return "#";
			}
			return noOfBombsAround + "";
		}

		return "";
	}

}
