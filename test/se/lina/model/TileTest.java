package se.lina.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TileTest {

	@Test
	public void vadReturnerarTile() {

		Grid grid = new Grid(2, 2, 4);
		grid.fillGrid();
		Tile[][] mines = grid.getGrid();
		assertEquals("_", mines[0][0].toString());
	}

	@Test
	public void vadReturnerarKlickadTile() {
		Grid grid = new Grid(2, 2, 4);
		grid.fillGrid();
		Tile[][] mines = grid.getGrid();

		mines[1][1].clickOnTile();
		assertEquals("#", mines[1][1].toString());
	}

	@Test
	public void vadReturnerarTileMedBombBredvid(){
		Grid grid = new Grid(2, 2, 4);
		Tile[][] mines = grid.getGrid();
		mines[0][0]= new Tile();
		mines[0][0].setNoOfBombsAround(2);
		mines[0][0].clickOnTile();
		assertEquals("2", mines[0][0].toString());
	}
	
}
