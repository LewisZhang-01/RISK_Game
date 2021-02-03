package gameboard;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.*;

import IO.IO;
import userinput.UserInput;

public class BoardViewer {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1200,1200);
		frame.setTitle("Risk Game Board");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoardComponent component = new BoardComponent();
		frame.add(component);
		frame.setResizable(true);
		frame.setVisible(true);
		
		
		IO io = new IO();
		frame.add(io,BorderLayout.SOUTH);
		frame.setResizable(true);
		frame.setVisible(true);
		
		UserInput ui = new UserInput();
		//UserInput.createUI(frame);
		frame.add(ui,BorderLayout.EAST);
		frame.setResizable(true);
		frame.setVisible(true);
		
	}

}
