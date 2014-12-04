package se.lina.view;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import se.lina.controller.MineSweeperController;
import se.lina.model.MineSweeperTile;

public class GameJPanel extends JPanel implements MouseInputListener {

	private static final long serialVersionUID = 1L;
	private static final int windowHeight = 750;
	private int rows;
	private int columns;
	private MineSweeperController controller;
	private int tileSize;
	private ArrayList<TileJButton> tileList;

	public GameJPanel(int rows, int columns, MineSweeperController controller) {
		super();
		this.rows = rows;
		this.columns = columns;
		this.controller = controller;
		int marginals = 2;
		this.setLayout(new GridLayout(rows, columns, marginals, marginals));
		tileList = new ArrayList<>();
		tileSize = windowHeight / rows;
		this.setSize(windowHeight, windowHeight);
		fillGamePanel();
	}

	private void fillGamePanel() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				TileJButton tilePanel = new TileJButton(i, j, tileSize,
						controller);
				tilePanel.addMouseListener(this);
				tileList.add(tilePanel);
				this.add(tilePanel);
			}
		}
	}

	void updateValuesOnBoard(MineSweeperTile[][] grid) {

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				for (TileJButton tile : tileList) {
					if (tile.getRow() == i && tile.getColumn() == j) {
						tile.setTileLayout(grid[i][j].toString());
					}
				}
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object tile = e.getSource();

		if (e.getButton() == MouseEvent.BUTTON1) {
			for (TileJButton tileJPanel : tileList) {
				if (tile.equals(tileJPanel)) {
					tileJPanel.onClick();
				}
			}
		}else if (e.getButton() == MouseEvent.BUTTON3) {
			for (TileJButton tileJPanel : tileList) {
				if (tile.equals(tileJPanel)) {
					tileJPanel.markAsBomb();
				}
			}
		}
	}

	void onClick(int row, int column) {
		controller.tileClicked(row, column);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
