package userinput;
//Yunlong Cheng
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserInput extends JPanel{//JFrame{//
   
	public UserInput() {

		createUI();
		
	}
   public void createUI(/*final JFrame frame*/){  
      JPanel panel = new JPanel();
      LayoutManager layout = new FlowLayout();  
      panel.setLayout(layout);       

      JButton button = new JButton("ClickHere!");
     
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
               label.setText("You choose:" + result);
            }else {
               label.setText("You do nothing");
            }
         }
      });

      panel.add(button);
      panel.add(label);
      add(panel);
     
   }  
}

