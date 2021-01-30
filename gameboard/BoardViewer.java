package gameboard;

import javax.swing.JFrame;

public class BoardViewer {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(350,350);
		frame.setTitle("Risk Game Board");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoardComponent component = new BoardComponent();
		frame.add(component);
		
		frame.setVisible(true);

	}

}
