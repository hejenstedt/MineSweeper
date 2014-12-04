package se.lina.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TileTest {

	@Test
	public void vadReturnerarTile() {

		MineSweeperModel grid = new MineSweeperModel(2, 2, 4);
//		grid.fillGrid();
		MineSweeperTile[][] mines = grid.getGrid();
		assertEquals("_", mines[0][0].toString());
	}

	@Test
	public void vadReturnerarKlickadTile() {
		MineSweeperModel grid = new MineSweeperModel(2, 2, 4);
//		grid.fillGrid();
		MineSweeperTile[][] mines = grid.getGrid();

		mines[1][1].clickOnTile();
		assertEquals("#", mines[1][1].toString());
	}

	@Test
	public void vadReturnerarTileMedBombBredvid(){
		MineSweeperModel grid = new MineSweeperModel(2, 2, 4);
		MineSweeperTile[][] mines = grid.getGrid();
		mines[0][0]= new MineSweeperTile();
		mines[0][0].setNoOfBombsAround(2);
		mines[0][0].clickOnTile();
		assertEquals("2", mines[0][0].toString());
	}
	
}
