package userinput;
//Yunlong Cheng

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import IO.IO;

public class UserInput extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public UserInput() {
		createUI();
	}
	
	public void createUI(){  
	   
      JPanel panel = new JPanel();
      //Crate menu button.
      JButton button = new JButton("Menu");
     
      final JLabel label = new JLabel();
      //Keep track of the menu button.
      button.addActionListener(new ActionListener() {
    	  
         @Override
         public void actionPerformed(ActionEvent e) {
           String[] options = {"Attack country", "Add soldiers", "Transfer forces", "Exchange cards", "End this round"}; 
           //Crate JOptionPane to let user choose.
           String result = (String)JOptionPane.showInputDialog(
               panel,
               "Choose your command", 
               "Choose from the list",            
               JOptionPane.PLAIN_MESSAGE,
               null,            
               options, 
               options[0] 
            );
            if(result == "Attack country" || result == "Add soldiers" || result == "Transfer forces" || result == "Exchange cards" || result == "End this round"){
            	//Append result into output pane.
            	IO.resultArea.append("You choose:" + result+"\n");
            	//if choose end then terminate the program.
            	if(result == "End this round") {
    				System.exit(0);
            	}            	
            }else {
            	//Append result "do nothing" into output pane.
            	IO.resultArea.append("You do nothing\n");
            }
         }
      });

	  //Add menu button to panel.
      panel.add(button);
      //Add label to panel.
      panel.add(label);
      //Add component form IO class into panel.
      panel.add(IO.inputLabel);
	  panel.add(IO.inputField);
	  panel.add(IO.button_enter);
	  panel.add(IO.button_close);
      add(panel);
   }  
}