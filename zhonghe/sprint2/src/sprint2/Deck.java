package sprint2;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Deck {
		private LinkedList<Card> cardList = new LinkedList<Card>();
		
		
		
		
		private String[] cardName = GameData.COUNTRY_NAMES;
		
		private Card.type[] cardType = {Card.type.Cavalry,Card.type.Artillery,Card.type.Artillery,
								Card.type.Infantry,Card.type.Cavalry,Card.type.Artillery,
								Card.type.Infantry,Card.type.Cavalry,Card.type.Infantry,
								Card.type.Cavalry,Card.type.Infantry,Card.type.Cavalry,
								Card.type.Artillery,Card.type.Cavalry,Card.type.Infantry,
								Card.type.Artillery,Card.type.Infantry,Card.type.Infantry,
								Card.type.Artillery,Card.type.Infantry,Card.type.Cavalry,
								Card.type.Cavalry,Card.type.Cavalry,Card.type.Artillery,
								Card.type.Infantry,Card.type.Artillery,Card.type.Artillery,
								Card.type.Cavalry,Card.type.Infantry,Card.type.Cavalry,
								Card.type.Artillery,Card.type.Cavalry,Card.type.Artillery,
								Card.type.Cavalry,Card.type.Artillery,Card.type.Infantry,
								Card.type.Cavalry,Card.type.Infantry,Card.type.Artillery,
								Card.type.Infantry,Card.type.Artillery,Card.type.Infantry};
		
		private String[] path = {"1.jpg","2.jpg","3.jpg","4.jpg","5.jpg","6.jpg","7.jpg","8.jpg","9.jpg",
								 "10.jpg","11.jpg","12.jpg","13.jpg","14.jpg","15.jpg","16.jpg","17.jpg","18.jpg",
								 "19.jpg","20.jpg","21.jpg","22.jpg","23.jpg","24.jpg","25.jpg","26.jpg","27.jpg",
								 "28.jpg","29.jpg","30.jpg","31.jpg","32.jpg","33.jpg","34.jpg","35.jpg","36.jpg",
								 "37.jpg","38.jpg","39.jpg","40.jpg","41.jpg","42.jpg"};
		
		public Deck() {
			//prepare the Deck
			for(int i = 0; i < cardName.length; i++) {
				cardList.add(new Card(cardName[i],cardType[i]));
			}
		}
		
		private static int Dice() {
			return (int) (Math.random() * 41);
		}
		
		public void draw() {
			int cardNum = Dice();
			JFrame frame = new JFrame();
		    frame.setSize(250, 450);
		    frame.setTitle("Your Card");
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    frame.setResizable(true);
		    
		    JLabel cName = new JLabel(cardName[cardNum]);
			JLabel cType = new JLabel(cardType[cardNum].toString());
			
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			panel1.add(cName,BorderLayout.SOUTH);
			panel2.add(cType,BorderLayout.NORTH);
			
			// Add Background Photo Part:
			
			ImageIcon icon=new ImageIcon(path[cardNum]);
			
			// put photo into lable
			JLabel label=new JLabel(icon);
			
			// set label size
			label.setBounds(5,25,icon.getIconWidth(),icon.getIconHeight());
			
			//Gets the second layer of the window and places the Label in.
			frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
				
			//Gets the top-level container of the Frame and sets it to transparent.
			JPanel j=(JPanel)frame.getContentPane();
			j.setOpaque(false);
			
			// end
		    
			frame.add(panel1,BorderLayout.SOUTH);
			frame.add(panel2,BorderLayout.NORTH);
			frame.setVisible(true);
		}
		
		public void display() {
			
		}
		
		public void trade() {
			
		}
		
}
