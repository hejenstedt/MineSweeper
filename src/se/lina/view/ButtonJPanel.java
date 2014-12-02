package se.lina.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import se.lina.controller.MineSweeperController;

public class ButtonJPanel extends JPanel {

	private MineSweeperController controller;

	public ButtonJPanel(MineSweeperController controller) {
		super();
		this.controller = controller;
		JButton startGame = new JButton();
		startGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onClick();
				
			}
		});
		startGame.setText("START");
		startGame.setBackground(Color.YELLOW);
		this.add(startGame);
		
	}

	protected void onClick() {
		
		controller.startGame();
			
	}
	
}
