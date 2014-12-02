package se.lina.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

import se.lina.controller.MineSweeperController;

public class TileJButton extends JButton {

	private static final long serialVersionUID = 1L;
	int row;
	int column;
	String value;
	MineSweeperController controller;

	public TileJButton(int row, int column, int size,
			MineSweeperController controller) {
		super();
		this.row = row;
		this.column = column;
		this.value = "";
		this.controller = controller;
		this.setPreferredSize(new Dimension(size, size));
		this.setText(value);
		this.setBackground(Color.LIGHT_GRAY);
		this.setFont(new Font("Arial", Font.BOLD, 13));
		setMargin(new Insets(1, 1, 1, 1));
	}

	public void setTileText(String value) {
		this.setText(value);
		this.value = value;
	}

	void setTileLayout(String value) {
		this.setText(value);

		switch (value) {
		case "X":
			this.setBackground(Color.DARK_GRAY);
			this.setForeground(Color.WHITE);
			// this.setSelected(true);
			break;
		case "":
			this.setBackground(Color.LIGHT_GRAY);
			this.setForeground(Color.BLACK);
			this.setSelected(false);
			break;
		case "#":
			this.setBackground(Color.RED);
			this.setForeground(Color.BLACK);
			// this.setSelected(true);
			break;
		case "0":
			this.setBackground(Color.CYAN);
			this.setForeground(Color.BLACK);
			// this.setSelected(true);
			break;
		default:
			this.setBackground(Color.GRAY);
			this.setForeground(Color.BLACK);
			// this.setSelected(true);

			break;
		}

	}

	public int getRow() {
		return row;
	}

	void onClick() {
		controller.tileClicked(row, column);
	}

	void markAsBomb() {
		controller.markAsBomb(row, column);
	}

	public int getColumn() {
		return column;
	}

	public String getValue() {
		return value;
	}
}
