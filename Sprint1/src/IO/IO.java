package IO;
// Zhi Zhang

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import gameboard.BoardComponent;

public class IO extends JPanel {
	
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
		// define a Output place
		resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
		resultArea.setEditable(false);
		// define a input place(enter player info)
		createTextField();
		createButton();
		createPanel();

	}

	// input area:
	private void createTextField() {
		inputLabel = new JLabel("User input: ");
		final int FIELD_WIDTH = 23;
		inputField = new JTextField(FIELD_WIDTH);
		//it will be append to output window at the beginning.
		resultArea.append("Please enter the #1 player name:\n");
	}

	class AddInputListener implements ActionListener {
		int i = 1;
		
		public void actionPerformed(ActionEvent event) {
			//Two players are required
			if (i <= 2) {
				//Store user input(1st player's name) into player array. 
				players[i - 1] = inputField.getText();
				//For 1st player.
				if (i == 1) {
					resultArea.append("Player #" + i + " (Red): " + players[i - 1] + "\n");
					resultArea.append("Please enter the #" + (i + 1) + " player name:\n");
				}
				//For 2nd player.
				if(i==2) {
					// Call to initialize the player's territories & place 1 army.	
					BoardComponent.button_initialize.doClick();
					resultArea.append("Player #" + i + " (Blue): " + players[i - 1] + "\n");
				}
				i++;
			} else {
				// After the first two inputs, whatever the user enters, just returns the input value.
				userInput = inputField.getText();
				resultArea.append(userInput + "\n");
			}
		}	
	}

	//Create buttons
	private void createButton() {

		//Button for enter inputs.
		button_enter = new JButton("Enter");
		
		ActionListener listener = new AddInputListener();
		//Keep track of the enter button.
		button_enter.addActionListener(listener);
		//Terminate program button.
		button_close = new JButton("Close");
		//Keep track of the close button.
		button_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultArea.setBackground(Color.red);
				System.exit(0);
			}
		});
	}

	// Create panel
	private void createPanel() {

		JPanel panel = new JPanel();
		//Create an etched border effect.
		panel.setBorder(BorderFactory.createEtchedBorder()); 
		//Creates a scrollable pane.
		JScrollPane scrollPane = new JScrollPane(resultArea);
		//Add this pane into panel.
		panel.add(scrollPane);
		add(panel);
	}
}