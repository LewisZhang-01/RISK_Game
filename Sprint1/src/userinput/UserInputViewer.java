package userinput;

import javax.swing.JFrame;

import IO.IO;

public class UserInputViewer {
	public static void main(String[] args){		
		// add JFrame
		
		
		JFrame frame = new UserInput();
		frame.setSize(900, 900);
		frame.setTitle("RISK Game");
		//frame.getContentPane(frame);
		//UserInput.createUI(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		
	}
}