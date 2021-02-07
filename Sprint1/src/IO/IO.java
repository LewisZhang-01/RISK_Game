package IO;
// Zhi Zhang

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import gameboard.BoardComponent;
import gameboard.Initializer;

public class IO extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int AREA_ROWS = 59;
	private final int AREA_COLUMNS = 30;

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
				repaint();
				players[i - 1] = inputField.getText();
				resultArea.append("Player " + i + ": " + players[i - 1] + "\n");
				if (i == 1) {
					resultArea.append("Please enter the " + (i + 1) + " player name:\n");
				}
				
				if(i==2) {
					// Call to initialize the player's territories
					
					//Initializer i = new Initializer();		
					
					//i.territoriesInitial();
					
					//repaint();	//I guess repaint should be placed here, but nothing happens --Zhonghe Chen
				}
				i++;
			} else {
				userInput = inputField.getText();
				resultArea.append(userInput + "\n");
				
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
		
		JScrollPane scrollPane = new JScrollPane(resultArea);
		panel.add(scrollPane);
		//panel.add(inputLabel);
		//panel.add(inputField);
		//panel.add(button_enter);
		add(panel);
	}
}
