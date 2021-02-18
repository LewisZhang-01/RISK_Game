import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.*;


public class CommandPanel extends JPanel  {
	
	private static final long serialVersionUID = 1L;
	private static final int FONT_SIZE = 14;
	
	private JTextField commandField = new JTextField(); 
	private LinkedList<String> commandBuffer = new LinkedList<String>();
	
	CommandPanel () {
		class AddActionListener implements ActionListener {
			   public void actionPerformed(ActionEvent event)	{
				   synchronized (commandBuffer) {
					   commandBuffer.add(commandField.getText());
					   commandField.setText("");
					   commandBuffer.notify();
				   }
		           return;
			   }
		   }
		ActionListener listener = new AddActionListener();
		commandField.addActionListener(listener);
		commandField.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
		setLayout(new BorderLayout());
		add(commandField, BorderLayout.CENTER);
	
		//Terminate program button.
		JButton button_close = new JButton("Close");
		//Keep track of the close button.
		button_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_close.setBackground(Color.red);
				System.exit(0);
			}
		});
		add(button_close, BorderLayout.EAST);
		return;
	}

	public String getCommand() {
		String command;
		synchronized (commandBuffer) {
			while (commandBuffer.isEmpty()) {
				try {
					commandBuffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			command = commandBuffer.pop();
		}
		return command;
	}
	
}
