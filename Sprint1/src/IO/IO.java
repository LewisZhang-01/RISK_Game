package IO;
// Zhi Zhang

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class IO extends JPanel {
	/*
	* 
	*/
	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 500;

	private static final int AREA_ROWS = 10;
	private static final int AREA_COLUMNS = 30;

	private JLabel inputLabel;
	private JTextField inputField;
	private JButton button_enter, button_close;
	private JTextArea resultArea;
	private String userInput;

	int PLAYERS_NUM = 2;
	String players[] = new String[PLAYERS_NUM];

	public IO() {

		resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);

		resultArea.setEditable(false);

		createTextField();
		createButton();
		createPanel();

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	// add input here:
	private void createTextField() {
		inputLabel = new JLabel("User input: ");

		final int FIELD_WIDTH = 23;

		inputField = new JTextField(FIELD_WIDTH);

		resultArea.append("Please enter the " + i + " player name:\n");

	}

	int i = 1;

	class AddInputListener implements ActionListener {
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
			}
		}
	}

	private void createButton() {

		button_enter = new JButton("Enter");
		button_enter.setBounds(80, 150, 80, 30);
		button_close = new JButton("Close");
		button_close.setBounds(120, 150, 80, 30);

		ActionListener listener = new AddInputListener();

		button_enter.addActionListener(listener);

		button_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputField.setBackground(Color.cyan);
				System.exit(0);
			}
		});

	}

	private void createPanel() {

		JPanel panel = new JPanel();
		
		/*
		 * JLabel image=new JLabel(new
		 * ImageIcon("/Users/lewiszhang/Pictures/IMG_0034.jpg")); panel.add(image);
		 * image.setBounds(0, 20, 200, 200);
		 */
		
		JScrollPane scrollPane = new JScrollPane(resultArea);
		panel.add(scrollPane);
		add(panel);
		panel.add(inputLabel);
		panel.add(inputField);
		panel.add(button_enter);
		panel.add(button_close);
	}
}
