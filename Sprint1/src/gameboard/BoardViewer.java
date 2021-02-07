package gameboard;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;

import IO.IO;
import userinput.UserInput;

public class BoardViewer {
	
	private static final int FRAME_WIDTH = 1300;    // must be even
	private static final int FRAME_HEIGHT = 1050;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		frame.setTitle("Risk Game Board");
		
// Add Background Photo Part:
		
		ImageIcon icon=new ImageIcon("RISK.jpg");
		
		// put photo into lable
		JLabel label=new JLabel(icon);
		
		// set lable size
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		
		//Gets the second layer of the window and places the Label in.
		frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
			
		//Gets the top-level container of the Frame and sets it to transparent.
		JPanel j=(JPanel)frame.getContentPane();
		j.setOpaque(false);
		
// end
		
		 
		BoardComponent component = new BoardComponent();
		component.setBorder(BorderFactory.createEtchedBorder()); 
		frame.add(component,BorderLayout.CENTER);
		
		
		IO io = new IO();
		io.setBorder(BorderFactory.createEtchedBorder()); 
		frame.add(io,BorderLayout.EAST);
		
		BoardComponent component1 = new BoardComponent();
		component.setBorder(BorderFactory.createEtchedBorder()); 
		frame.add(component1,BorderLayout.CENTER);
		
		UserInput ui = new UserInput();
		ui.setBorder(BorderFactory.createEtchedBorder()); 
		frame.add(ui,BorderLayout.SOUTH);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);	
		frame.setVisible(true);
		
	}

}
