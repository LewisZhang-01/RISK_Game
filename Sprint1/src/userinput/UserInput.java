package userinput;
//Yunlong Cheng

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import IO.IO;

public class UserInput extends JPanel{//JFrame{//
	
	private static final long serialVersionUID = 1L;
	
	public UserInput() {

		createUI();
		
	}
   public void createUI(/*final JFrame frame*/){  
	   
      JPanel panel = new JPanel();
      
      JButton button = new JButton("Menu");
     
      final JLabel label = new JLabel();
      
      button.addActionListener(new ActionListener() {
    	  
         @Override
         public void actionPerformed(ActionEvent e) {
           String[] options = {"Attack country", "Add soldiers", "Transfer forces", "Exchange cards", "End this round"}; 

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
            	IO.resultArea.append("You choose:" + result+"\n");
            	// choose end.
            	if(result == "End this round") {
    				System.exit(0);
            	}            	
            }else {
            	IO.resultArea.append("You do nothing\n");
            }
         }
      });

      
	  
      panel.add(button);
      panel.add(label);
      
      panel.add(IO.inputLabel);
	  panel.add(IO.inputField);
	  panel.add(IO.button_enter);
	  panel.add(IO.button_close);
      add(panel);
     
   }  
}

