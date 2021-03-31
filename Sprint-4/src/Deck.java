import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Deck {

	private ArrayList<Card> cards;
	
	Deck () {
		int cardId;
		cards = new ArrayList<Card>();
		for (cardId=0; cardId<GameData.NUM_COUNTRIES; cardId++) {
			cards.add(new Card(cardId, GameData.COUNTRY_NAMES[cardId],GameData.cardType[cardId]));
		}
		return;
	}
	
	private int Dice() {
		return (int) (Math.random() * cards.size());
	}
	
	public Card getCard () {
		int index = (int)(Math.random() * cards.size());  
		Card card = cards.remove(index);
		return card;
	}
	
	public boolean isEmpty () {
		return cards.isEmpty();
	}

	public Card getCard (int countryId) {
		Card card = cards.remove(countryId);
		return card;
	}
	

	public Card draw() {
		int cardNum = Dice();		//roll dice to decide which card is drawn
		Card removed = cards.remove(cardNum);	//remove the card being drawn from the deck
		
		//display the card in another frame
		JFrame frame = new JFrame();
	    frame.setSize(250, 450);
	    frame.setTitle("Your Card");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setResizable(false);
	    
	    //information of the card
	    JLabel cName = new JLabel(GameData.COUNTRY_NAMES[cardNum]);
		JLabel cType = new JLabel(GameData.cardType[cardNum].toString());
		
		//panel holds information
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.add(cName,BorderLayout.SOUTH);
		panel2.add(cType,BorderLayout.NORTH);
		
		// Add Card Photo Part: (Done by Zhi Zhang in Sprint 1. I just copied it and modified a little.)
		
		ImageIcon icon=new ImageIcon(GameData.path[cardNum]);
		
		// put photo into lable
		JLabel label=new JLabel(icon);
		
		// set label size
		label.setBounds(5,25,icon.getIconWidth(),icon.getIconHeight());
		
		//Gets the second layer of the window and places the Label in.
		frame.getLayeredPane().add(label,Integer.MIN_VALUE);
			
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
	public void display(UI ui, ArrayList<Card> cardSet) {
		JFrame frame = new JFrame();
	    frame.setSize(1300, 450);
	    frame.setTitle("Your Cards");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setResizable(false);
	    
	    for(int i = 0; i < cardSet.size(); i++) {
	    	// Add Card Photo Part as above
	    	int cardNo = cardSet.get(i).getCountryId();
	    	
	    	ImageIcon icon=new ImageIcon(GameData.path[cardNo]);
		
	    	// put photo into lable
	    	JLabel label=new JLabel(icon);
	
	    	// set label size
	    	int xcor = 5 + i*250;		//make sure that there is enough space for each card.(maximum 5 cards)
	    	label.setBounds(xcor,25,icon.getIconWidth(),icon.getIconHeight());
		
	    	//Gets the second layer of the window and places the Label in.
	    	frame.getLayeredPane().add(label,Integer.MIN_VALUE);
		
	    	//Gets the top-level container of the Frame and sets it to transparent.
	   		JPanel j=(JPanel)frame.getContentPane();
	    	j.setOpaque(false);
		
	    	// end
	   	}

		frame.setVisible(true);
	}
	
	public void drawCard(Board board,UI ui,int playerId, ArrayList<Card> p1_cardset, ArrayList<Card> p2_cardset) {

		ui.displayString("\nEnter \"draw\" to draw a card, anything else to place armies.(player "+ (playerId + 1) + ")\n");
		String mode = ui.inputDrawChoose();
		
		if (mode.equals("draw")) {
			if (playerId == 0) {
				p1_cardset.add(draw());
			} else {
				p2_cardset.add(draw());
			}
		}
		
		
		ui.displayString("\nEnter \"trade\" to check and trade your cards, anything else to do nothing.\n");
		String mode2 = ui.inputTradeChoose();
		if (mode2.equals("trade")) {
			if (playerId == 0) {
				display(ui, p1_cardset);
				ui.displayString("Player 1 has: ");
				for (int i = 0; i < p1_cardset.size(); i++) {
					ui.displayString(p1_cardset.get(i).toString());
				}
				ui.displayString("Please enter \"trade\" to automatically trade your cards, and anything else to skip.");
				String trade = ui.inputTradeChoose();
				if (trade.equals("trade")) {
					trade(ui, board, playerId, p1_cardset);
				}
			} else {
				display(ui, p2_cardset);
				ui.displayString("Player 2 has: ");
				for (int i = 0; i < p2_cardset.size(); i++) {
					ui.displayString(p2_cardset.get(i).toString());
				}
				ui.displayString("Please enter \"trade\" to automatically trade your cards, and anything else to skip.");
				String trade = ui.inputTradeChoose();
				if (trade.equals("trade")) {
					trade(ui, board, playerId, p2_cardset);
				}
			}
		}else if(p1_cardset.size() == 5) {
			ui.displayString("You could only have at most 5 cards. Auto-trade is forced to process now.");
			trade(ui, board, playerId, p1_cardset);
		}else if(p2_cardset.size() == 5) {
			ui.displayString("You could only have at most 5 cards. Auto-trade is forced to process now.");
			trade(ui, board, playerId, p2_cardset);
		}
	}
	
	public ArrayList<Card> trade(UI ui, Board board, int playerId, ArrayList<Card> cardSet) {
		
		return cardSet;
	}
}
