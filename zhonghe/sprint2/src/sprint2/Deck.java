package sprint2;

/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 * */
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
		private String[] path = {"images/cards/1.jpg","images/cards/2.jpg","images/cards/3.jpg","images/cards/4.jpg","images/cards/5.jpg","images/cards/6.jpg","images/cards/7.jpg","images/cards/8.jpg","images/cards/9.jpg",
				 "images/cards/10.jpg","images/cards/11.jpg","images/cards/12.jpg","images/cards/13.jpg","images/cards/14.jpg","images/cards/15.jpg","images/cards/16.jpg","images/cards/17.jpg","images/cards/18.jpg",
				 "images/cards/19.jpg","images/cards/20.jpg","images/cards/21.jpg","images/cards/22.jpg","images/cards/23.jpg","images/cards/24.jpg","images/cards/25.jpg","images/cards/26.jpg","images/cards/27.jpg",
				 "images/cards/28.jpg","images/cards/29.jpg","images/cards/30.jpg","images/cards/31.jpg","images/cards/32.jpg","images/cards/33.jpg","images/cards/34.jpg","images/cards/35.jpg","images/cards/36.jpg",
				 "images/cards/37.jpg","images/cards/38.jpg","images/cards/39.jpg","images/cards/40.jpg","images/cards/41.jpg","images/cards/42.jpg"};

		
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
			int cardNum = Dice();		//roll dice to decide which card is drawn
			Card removed = cardList.remove(cardNum);	//remove the card being drawn from the deck
			
			//display the card in another frame
			JFrame frame = new JFrame();
		    frame.setSize(250, 450);
		    frame.setTitle("Your Card");
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    frame.setResizable(false);
		    
		    //information of the card
		    JLabel cName = new JLabel(cardName[cardNum]);
			JLabel cType = new JLabel(cardType[cardNum].toString());
			
			//panel holds information
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			panel1.add(cName,BorderLayout.SOUTH);
			panel2.add(cType,BorderLayout.NORTH);
			
			// Add Card Photo Part: (Done by Zhi Zhang in Sprint 1. I just copied it and modified a little.)
			
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
		    
			//add information panel to the frame
			frame.add(panel1,BorderLayout.SOUTH);
			frame.add(panel2,BorderLayout.NORTH);
			frame.setVisible(true);
			
			return removed;
		}
		
		//Enable players to see what cards they own in another frame.
		public void display(UI ui, LinkedList<Card> cardSet) {
			JFrame frame = new JFrame();
		    frame.setSize(1300, 450);
		    frame.setTitle("Your Cards");
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    frame.setResizable(false);
		    
		    for(int i = 0; i < cardSet.size(); i++) {
		    	// Add Card Photo Part as above
		    	int cardNo = cardSet.get(i).getCardNo();
		    	
		    	ImageIcon icon=new ImageIcon(path[cardNo]);
			
		    	// put photo into lable
		    	JLabel label=new JLabel(icon);
		
		    	// set label size
		    	int xcor = 5 + i*250;		//make sure that there is enough space for each card.(maximum 5 cards)
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
		
		ArmyPlace ap = new ArmyPlace();
		
		public LinkedList<Card> trade(UI ui, Board board, int playerId, LinkedList<Card> cardSet) {
			int inf,art,cav;
			inf = art = cav = 0;	//inf for type Infantry, art for type Artillery, cav for type Cavalry
		
			//count the number of each type of cards player own
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
			
			
			if(inf >= 3 || art >= 3 || cav >= 3 || (inf>0 && art>0 && cav>0)) {	//when player have a set of cards
				int armiesGet = 0;			//the number of extra armies players get from trading cards
				
				//calculate the extra armies players get
				if(tradeNumber < 6) {
					armiesGet = tradeNumber * 2 + 2;
				}else {
					armiesGet = (tradeNumber - 6)*5 + 15;
				}
				
				if(inf >= 3) {			//3 infantry set
					int count = 0;		//count whether 3 cards have been removed from players' inventory
					for(int i = 0; i < cardSet.size(); i++) {
						if(cardSet.get(i).getType().equals(Card.type.Infantry) && count < 3) {
							//when card type is Infantry, and no more than 3 Infantry cards have been removed,
							//remove this card from inventory.
							cardList.add(cardSet.remove(i));	//remove the card from user inventory and add it back to game deck
							i--;		//remove a card decreases the index
							count++;	//one more card has been removed
						}
					}
				}else if(art >= 3) {	//3 artillery set, similar to infantry
					int count = 0;
					for(int i = 0; i < cardSet.size(); i++) {
						if(cardSet.get(i).getType().equals(Card.type.Artillery) && count < 3) {
							cardList.add(cardSet.remove(i));
							i--;
							count++;
						}
					}
				}else if(cav >= 3) {	//3 cavalry set, similar to infantry
					int count = 0;
					for(int i = 0; i < cardSet.size(); i++) {
						if(cardSet.get(i).getType().equals(Card.type.Cavalry) && count < 3) {
							cardList.add(cardSet.remove(i));
							i--;
							count++;
						}
					}
				}else {		// one card of each type also form a set
					int first_i = 0;	//to record the deletion of first infantry card
					int first_c = 0;	//to record the deletion of first cavalry card
					int first_a = 0;	//to record the deletion of first artillery card
					for(int i = 0; i < cardSet.size(); i++) {
						if(first_i == 0 && cardSet.get(i).getType().equals(Card.type.Infantry)) {
							//remove the first infantry card found
							cardList.add(cardSet.remove(i));
							i--;
							first_i++;	//first infantry is found, no more infantry cards need to be delete
						}else if(first_c == 0 && cardSet.get(i).getType().equals(Card.type.Cavalry)) {
							//remove the first cavalry card found
							cardList.add(cardSet.remove(i));
							i--;
							first_c++;
						}else if(first_a == 0 && cardSet.get(i).getType().equals(Card.type.Artillery)) {
							//remove the first artillery card found
							cardList.add(cardSet.remove(i));
							i--;
							first_a++;
						}
					}
				}
				//inform players that trade is successful and the extra army number players get from the trade
				ui.displayString("Trade Success. The " + tradeNumber + " set is traded. You earn " + armiesGet + " armies to place. Traded cards are removed.");
				tradeNumber += 1;	//one trade has been done; next trade would be more valuable
				ui.displayString("Place your extra armies:");	//inform players to place extra armies they get from the trade
				ap.realPlayerPlace(ui, board, playerId, armiesGet); //place armies

			}else {
				ui.displayString("Trade false. No set fount in your cards."); //if trade fails, do nothing
			}
			
			return cardSet;	//return the modified card inventory back to players
		}
		
}
