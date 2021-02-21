package sprint2;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Deck {
		//A list of cards in the deck
		private LinkedList<Card> cardList = new LinkedList<Card>();
		
		//record how many sets have been traded in this game
		private int tradeNumber = 1;
		
		//Name of each card
		private String[] cardName = GameData.COUNTRY_NAMES;
		
		//Type of each card
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
		
		//the path of the graphs of cards
		private String[] path = {"cards/1.jpg","cards/2.jpg","cards/3.jpg","cards/4.jpg","cards/5.jpg","cards/6.jpg","cards/7.jpg","cards/8.jpg","cards/9.jpg",
								 "cards/10.jpg","cards/11.jpg","cards/12.jpg","cards/13.jpg","cards/14.jpg","cards/15.jpg","cards/16.jpg","cards/17.jpg","cards/18.jpg",
								 "cards/19.jpg","cards/20.jpg","cards/21.jpg","cards/22.jpg","cards/23.jpg","cards/24.jpg","cards/25.jpg","cards/26.jpg","cards/27.jpg",
								 "cards/28.jpg","cards/29.jpg","cards/30.jpg","cards/31.jpg","cards/32.jpg","cards/33.jpg","cards/34.jpg","cards/35.jpg","cards/36.jpg",
								 "cards/37.jpg","cards/38.jpg","cards/39.jpg","cards/40.jpg","cards/41.jpg","cards/42.jpg"};

		
		public Deck() {
			//prepare the Deck
			for(int i = 0; i < cardName.length; i++) {
				cardList.add(new Card(cardName[i],cardType[i],i));
			}
		}
		
		private int Dice() {
			return (int) (Math.random() * cardList.size());
		}
		
		public Card draw() {
			int cardNum = Dice();
			Card removed = cardList.remove(cardNum);
			
			JFrame frame = new JFrame();
		    frame.setSize(250, 450);
		    frame.setTitle("Your Card");
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    frame.setResizable(false);
		    
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
			
			return removed;
		}
		
		public void display(UI ui, LinkedList<Card> cardSet) {
			JFrame frame = new JFrame();
		    frame.setSize(1300, 450);
		    frame.setTitle("Your Cards");
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    frame.setResizable(false);
		    
		    for(int i = 0; i < cardSet.size(); i++) {
		    	// Add Background Photo Part:
		    	int cardNo = cardSet.get(i).getCardNo();
		    	
		    	ImageIcon icon=new ImageIcon(path[cardNo]);
			
		    	// put photo into lable
		    	JLabel label=new JLabel(icon);
		
		    	// set label size
		    	int xcor = 5 + i*250;
		    	label.setBounds(xcor,25,icon.getIconWidth(),icon.getIconHeight());
			
		    	//Gets the second layer of the window and places the Label in.
		    	frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
			
		    	//Gets the top-level container of the Frame and sets it to transparent.
		   		JPanel j=(JPanel)frame.getContentPane();
		    	j.setOpaque(false);
			
		    	// end
		   	}

			frame.setVisible(true);
		}
		
		public LinkedList<Card> trade(UI ui, Board board, int playerId, LinkedList<Card> cardSet) {
			int inf,art,cav;
			inf = art = cav = 0;
		
			for(int i = 0; i < cardSet.size(); i++) {
				switch(cardSet.get(i).getType()) {
					case Infantry:
						inf++;
						break;
					case Artillery:
						art++;
						break;
					default:
						cav++;
						break;
				}
			}
			
			if(inf >= 3 || art >= 3 || cav >= 3 || (inf>0 && art>0 && cav>0)) {
				int armiesGet = 0;
				if(tradeNumber < 6) {
					armiesGet = tradeNumber * 2 + 2;
				}else {
					armiesGet = (tradeNumber - 6)*5 + 15;
				}
				
				if(inf >= 3) {
					int count = 0;
					for(int i = 0; i < cardSet.size(); i++) {
						if(cardSet.get(i).getType().equals(Card.type.Infantry) && count < 3) {
							cardList.add(cardSet.remove(i));
							count++;
						}
					}
				}else if(art >= 3) {
					int count = 0;
					for(int i = 0; i < cardSet.size(); i++) {
						if(cardSet.get(i).getType().equals(Card.type.Artillery) && count < 3) {
							cardList.add(cardSet.remove(i));
							count++;
						}
					}
				}else if(cav >= 3) {
					int count = 0;
					for(int i = 0; i < cardSet.size(); i++) {
						if(cardSet.get(i).getType().equals(Card.type.Cavalry) && count < 3) {
							cardList.add(cardSet.remove(i));
							count++;
						}
					}
				}else {
					int first_i = 0;
					int first_c = 0;
					int first_a = 0;
					for(int i = 0; i < cardSet.size(); i++) {
						if(first_i == 0 && cardSet.get(i).getType().equals(Card.type.Infantry)) {
							cardList.add(cardSet.remove(i));
							first_i++;
						}else if(first_c == 0 && cardSet.get(i).getType().equals(Card.type.Cavalry)) {
							cardList.add(cardSet.remove(i));
							first_c++;
						}else if(first_a == 0 && cardSet.get(i).getType().equals(Card.type.Artillery)) {
							cardList.add(cardSet.remove(i));
							first_a++;
						}
					}
				}
				ui.displayString("Trade Success. The " + tradeNumber + " set is traded. You earn " + armiesGet + " armies to place. Traded cards are removed.");
				tradeNumber += 1;
				ui.displayString("Place your extra armies:");
				Sprint2.realPlayerPlace(ui, board, playerId, armiesGet);

			}else {
				ui.displayString("Trade false. No set fount in your cards.");
			}
			
			return cardSet;
		}
		
}
