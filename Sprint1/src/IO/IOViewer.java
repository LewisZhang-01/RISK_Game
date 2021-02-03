package IO;
//Zhi Zhang

import javax.swing.JFrame;

public class IOViewer {
	public static void main(String[] args){		
		// add JFrame
		
		JFrame frame = new IO();
		frame.setSize(400, 300);
		frame.setTitle("RISK Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
	}
}
