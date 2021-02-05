package IO;
// Zhi Zhang

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.awt.image.BufferedImage;

import javax.swing.*;

import gameboard.BoardComponent;

public class IO extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private static final int AREA_ROWS = 48;
	private static final int AREA_COLUMNS = 30;

	public static JLabel inputLabel;
	public static JTextField inputField;
	public static JButton button_enter, button_close;
	public static JTextArea resultArea;
	private String userInput;

	int PLAYERS_NUM = 2;
	String players[] = new String[PLAYERS_NUM];

	public IO() {

		resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
		
		resultArea.setEditable(false);

		createTextField();
		createButton();
		createPanel();

		//setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	
	// add input here:
	private void createTextField() {
		inputLabel = new JLabel("User input: ");
		final int FIELD_WIDTH = 23;

		inputField = new JTextField(FIELD_WIDTH);

		resultArea.append("Please enter the 1 player name:\n");

	}

	class AddInputListener implements ActionListener {
		int i = 1;
		public void actionPerformed(ActionEvent event) {
			if (i <= 2) {

				players[i - 1] = inputField.getText();
				resultArea.append("Player " + i + ": " + players[i - 1] + "\n");
				if (i == 1) {
					resultArea.append("Please enter the " + (i + 1) + " player name:\n");
				}
				i++;
			} else {
				userInput = inputField.getText();
				resultArea.append(userInput + "\n");
				// Call to initialize the player's territories
				BoardComponent.playerTerritories_Initialization();
			}
		}	
	}

	
	
	private void createButton() {

		button_enter = new JButton("Enter");
		
		ActionListener listener = new AddInputListener();
		
		button_enter.addActionListener(listener);

		button_close = new JButton("Close");
		
		button_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultArea.setBackground(Color.red);
				System.exit(0);
			}
		});
		
	}

	private void createPanel() {

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder()); 
		/*
		 * JLabel image=new JLabel(new
		 * ImageIcon("/Users/lewiszhang/Pictures/IMG_0034.jpg")); panel.add(image);
		 * image.setBounds(0, 20, 200, 200);
		 */
		
		JScrollPane scrollPane = new JScrollPane(resultArea);
		panel.add(scrollPane);
		add(panel);
	}
}
