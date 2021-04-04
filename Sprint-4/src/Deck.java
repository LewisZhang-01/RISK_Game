/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 */

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Deck {

	// record how many sets have been traded in this game
	private int tradeNumber = 1;

	private ArrayList<Card> cards;

	Deck() {
		int cardId;
		cards = new ArrayList<Card>();
		for (cardId = 0; cardId < GameData.NUM_COUNTRIES; cardId++) {
			cards.add(new Card(cardId, GameData.COUNTRY_NAMES[cardId], GameData.cardType[cardId]));
		}
		// Add extra 2 wild cards.

		cards.add(new Card(42, "Wild Card", GameData.cardType[42]));
		cards.add(new Card(43, "Wild Card", GameData.cardType[43]));

		return;
	}

	public Card getCard() {
		int index = (int) (Math.random() * (cards.size()-2));
		Card card = cards.remove(index);
		return card;
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	// Get card based on country id
	public Card getCard(int countryId) {
		Card card = cards.remove(countryId);
		return card;
	}

	// Set picture to a label
	public void getBackgroundPicture(JLabel bglabel, JPanel contentPane, String path) {
		ImageIcon background = new ImageIcon(path);
		bglabel.setIcon(background);
		bglabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		contentPane.setOpaque(false);
	}

	// Dice for random draw a card
	private int Dice() {
		return (int) (Math.random() * cards.size());
	}

	// Draw a card
	public Card draw(ArrayList<Card> p1_cardset, ArrayList<Card> p2_cardset) {
		int cardNum = 0;
		boolean repeat = true;
		while (repeat) { 
			repeat = false;
			// roll dice to decide which card is drawn
			cardNum = Dice();

			// Make sure there are no duplicate cards
			if(p1_cardset.size() != 0) {
				for (int i = 0; i < p1_cardset.size(); i++) {
					if (p1_cardset.get(i).getCountryId() == cardNum) {
						repeat = true;
					}
				}
			}
			if(p2_cardset.size() != 0) {
				for (int i = 0; i < p2_cardset.size(); i++) {
					if (p1_cardset.get(i).getCountryId() == cardNum) {
					repeat = true;
					}
				}
			}
		}

		// Display the card in another frame
		JFrame frame = new JFrame();
		frame.setSize(250, 450);
		frame.setTitle("Your Card");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		// Set background label
		JLabel bglabel = new JLabel();
		JPanel contentPane = (JPanel) frame.getContentPane();
		getBackgroundPicture(bglabel, contentPane, GameData.CardImage);
		frame.getLayeredPane().add(bglabel, new Integer(Integer.MIN_VALUE));

		// Information of the card
		JLabel cName = new JLabel(GameData.COUNTRY_NAMES[cardNum]);
		JLabel cType = new JLabel(GameData.cardType[cardNum].toString());
		cName.setHorizontalAlignment(JLabel.CENTER);
		cType.setHorizontalAlignment(JLabel.CENTER);

		// Get card photo
		ImageIcon icon = new ImageIcon(GameData.path[cardNum]);
		// Put photo into lable
		JLabel cPhoto = new JLabel(icon);
		// Set label size
		cPhoto.setBounds(5, 25, icon.getIconWidth(), icon.getIconHeight());
		// Add labels into frame.
		frame.add(cType, BorderLayout.NORTH);
		frame.add(cPhoto, BorderLayout.CENTER);
		frame.add(cName, BorderLayout.SOUTH);
		// Gets the top-level container of the Frame and sets it to transparent.
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);
		frame.setVisible(true);
		return cards.get(cardNum);
	}

	// Enable players to see what cards they own in another frame (Card Sets).
	public void display(UI ui, ArrayList<Card> cardSet) {
		JFrame frame = new JFrame();
		frame.setSize(1300, 450);
		frame.setTitle("Your Cards");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		// set background label
		JLabel bglabel = new JLabel();
		JPanel contentPane = (JPanel) frame.getContentPane();
		getBackgroundPicture(bglabel, contentPane, GameData.CardSetsImage);
		frame.getLayeredPane().add(bglabel, new Integer(Integer.MIN_VALUE));

		// Display all cards in a card sets
		for (int i = 0; i < cardSet.size(); i++) {
			// Add Card Photo Part as above
			int cardNo = cardSet.get(i).getCountryId();
			System.out.println("trade: " + GameData.COUNTRY_NAMES[cardNo]);
			ImageIcon icon = new ImageIcon(GameData.path[cardNo]);
			// put photo into lable
			JLabel label = new JLabel(icon);
			// set label size
			int xcor = 5 + i * 250; // make sure that there is enough space for each card.(maximum 5 cards)
			label.setBounds(xcor, 25, icon.getIconWidth(), icon.getIconHeight());
			// Gets the second layer of the window and places the Label in.
			frame.getLayeredPane().add(label, Integer.MIN_VALUE);
			// Gets the top-level container of the Frame and sets it to transparent.
			JPanel j = (JPanel) frame.getContentPane();
			j.setOpaque(false);
		}
		frame.setVisible(true);
	}

	// Player can draw a card if player conquered at lease 1 territory.
	public void drawCard(Board board, UI ui, int playerId, int numUnits, Player currPlayer, ArrayList<Card> p1_cardset, ArrayList<Card> p2_cardset) {
		String message = ui.makeLongName(currPlayer) + ": Enter \"draw\" to draw a card, anything else to skip.";
		ui.displayString(message);
		String mode = ui.inputChoose();

		if (mode.equals("draw")) {
			// Each player has a unique card sets.
			if (playerId == 0) {
				p1_cardset.add(draw(p1_cardset, p2_cardset));
			} else {
				p2_cardset.add(draw(p1_cardset, p2_cardset));
			}
		}
	}

	// Display card sets of specific player
	public void showCardSet(Board board, UI ui, int playerId, int numUnits, Player currPlayer, ArrayList<Card> p1_cardset, ArrayList<Card> p2_cardset) {
		String message = ui.makeLongName(currPlayer) + ": Enter \"show\" to check your card sets, anything else to skip.";
		ui.displayString(message);
		String input = ui.inputChoose();
		if (input.equals("show")) {
			ui.displayString(currPlayer.getName()+" has: ");
			if (playerId == 0) {
				display(ui, p1_cardset);
				for (int i = 0; i < p1_cardset.size(); i++) {
					ui.displayString(p1_cardset.get(i).toString());
				}
			} else {
				display(ui, p2_cardset);
				for (int i = 0; i < p2_cardset.size(); i++) {
					ui.displayString(p2_cardset.get(i).toString());
				}
			}
		} 
	}
	
	public void tradeCard(Board board, UI ui, int playerId, int numUnits, Player currPlayer, ArrayList<Card> p1_cardset, ArrayList<Card> p2_cardset) {
		String message = ui.makeLongName(currPlayer) + ": Please enter \"trade\" to trade your cards, anything else to skip.";
		ui.displayString(message);
		String tradeOrNot = ui.inputChoose();
		
		if(tradeOrNot.equals("trade")) {
			message = ui.makeLongName(currPlayer) + ": Please enter the first letter of insignias you wish to exchange,"
					  							  + "or enter \"trade\" to automatically trade your cards,"
					  							  + "or enter \"skip\" to skip.";
			ui.displayString(message);
			String input = ui.inputChoose();
			if (playerId == 0) {
				trade(ui, board, playerId, playerId, currPlayer, p1_cardset, input);
			} else {
				trade(ui, board, playerId, numUnits, currPlayer, p2_cardset, input);
			}
		}else if (p1_cardset.size() == 5 || p2_cardset.size() == 5) {
			ui.displayString("You could only have at most 5 cards. Auto-trade is forced to process now.");
			if(p1_cardset.size() == 5) {
				trade(ui, board, playerId, numUnits, currPlayer, p1_cardset, "trade");
			}else {
				trade(ui, board, playerId, numUnits, currPlayer, p2_cardset, "trade");
			}	
		} 
	}

	public ArrayList<Card> trade(UI ui, Board board, int playerId, int numUnits, Player currPlayer, ArrayList<Card> cardSet, String input) {
		display(ui, cardSet);
		
		// inf for type Infantry, 
		// art for type Artillery, 
		// cav for type Cavalry, 
		// wild for wild card.
		int inf=0, art=0, cav=0, wild=0;

		// count the number of each type of cards player own
		for (int i = 0; i < cardSet.size(); i++) {
			switch (cardSet.get(i).getType()) {
			case Infantry:
				inf++;
				break;
			case Artillery:
				art++;
				break;
			case Cavalry:
				cav++;
				break;
			default:
				wild++;
				break;
			}
		}
		
		switch(input) {
		case "iii":
			if(inf + wild >= 3) {
				tradeSameCard(ui, board, playerId, currPlayer, cardSet, Card.type.Infantry);
			} else {
				// Enter another card set you wish to trade, or enter "skip" to skip.
				tradeFalseAndNew(ui, board, playerId, numUnits, currPlayer, cardSet, input);
			}
			break;
		case "ccc":
			if(cav + wild >= 3) {
				tradeSameCard(ui, board, playerId, currPlayer, cardSet, Card.type.Cavalry);
			} else {
				tradeFalseAndNew(ui, board, playerId, numUnits, currPlayer, cardSet, input);
			}
			break;
		case "aaa":
			if(art + wild >= 3) {
				tradeSameCard(ui, board, playerId, currPlayer, cardSet, Card.type.Artillery);
			} else {
				tradeFalseAndNew(ui, board, playerId, numUnits, currPlayer, cardSet, input);
			}
			break;
		case "ica":
			if((inf + wild > 0 && art + wild > 0 && cav + wild > 0)) {
				// the number of extra armies players get from trading cards
				int armiesGet = 0; 
				// calculate the extra armies players get
				armiesGet = calcExtraArmies(armiesGet);
				cardRemover(cardSet);
				tradeSuccess(ui, currPlayer,armiesGet);
			} else {
				tradeFalseAndNew(ui, board, playerId, numUnits, currPlayer, cardSet, input);
			}
			break;
		case "trade":
			if (inf + wild >= 3 || art + wild >= 3 || cav + wild >= 3
				|| (inf + wild > 0 && art + wild > 0 && cav + wild > 0)) { // when player have a set of cards
				int armiesGet = 0; // the number of extra armies players get from trading cards

				// calculate the extra armies players get
				armiesGet = calcExtraArmies(armiesGet);

				if (inf + wild >= 3) { // 3 infantry set
					int count = 0; // count whether 3 cards have been removed from players' inventory
					for (int i = 0; i < cardSet.size(); i++) {
						if (cardSet.get(i).getType().equals(Card.type.Infantry) && count < 3) {
							// when card type is Infantry, and no more than 3 Infantry cards have been removed,
							// remove this card from inventory.
							cardSet.remove(i); // remove the card from user inventory
							i--; // remove a card decreases the index
							count++; // one more card has been removed
						}
					}
				} else if (art + wild >= 3) { // 3 artillery set, similar to infantry
					int count = 0;
					for (int i = 0; i < cardSet.size(); i++) {
						if (cardSet.get(i).getType().equals(Card.type.Artillery) && count < 3) {
							cardSet.remove(i);
							i--;
							count++;
						}
					}
				} else if (cav + wild >= 3) { // 3 cavalry set, similar to infantry
					int count = 0;
					for (int i = 0; i < cardSet.size(); i++) {
						if (cardSet.get(i).getType().equals(Card.type.Cavalry) && count < 3) {
							cardSet.remove(i);
							i--;
							count++;
						}
					}
				} else { // one card of each type also form a set
					cardRemover(cardSet);
				}
				// Inform players that trade is successful and the extra army number players get from the trade
				tradeSuccess(ui, currPlayer,armiesGet);
			} else {
				ui.displayString("Trade false. No set fount in your cards."); // if trade fails, do nothing
			}
			break;
		case "skip":
			break;
		default:
			ui.displayString("Error command. Please enter \"iii\" or \"ccc\" or \"aaa\" or \"ica\" or \"trade\" to trade, or \"skip\" to skip");
			input = ui.inputChoose();
			cardSet = trade(ui, board, playerId, numUnits, currPlayer, cardSet, input);
		}
		return cardSet;
	}
	
	// Inform players that trade is successful and the extra army number players get from the trade
	public void tradeSuccess(UI ui, Player currPlayer, int armiesGet) {
		ui.displayString("Trade Success. The " + tradeNumber + " set is traded. You earn " + armiesGet
				+ " armies to place. Traded cards are removed.");
		tradeNumber += 1; // one trade has been done; next trade would be more valuable
		currPlayer.addUnits(armiesGet);
		ui.displayTradeReinforcements(currPlayer, armiesGet);// inform players to place extra armies they get from trading
		ui.displayTotalReinforcements(currPlayer);
	}
	
	// Enter another card set you wish to trade, or enter "skip" to skip.
	public void tradeFalseAndNew(UI ui, Board board, int playerId, int numUnits, Player currPlayer, ArrayList<Card> cardSet, String input) {
		ui.displayString("Trade false. No set fount in your cards."); // if trade fails, do nothing
		ui.displayString("Enter another card set you wish to trade, or enter \"skip\" to skip.");
		input = ui.inputChoose();
		cardSet = trade(ui, board, playerId, numUnits, currPlayer, cardSet, input);
	}
	
	public void tradeSameCard(UI ui, Board board, int playerId, Player currPlayer, ArrayList<Card> cardSet, Card.type type) {
		int armiesGet = 0; // the number of extra armies players get from trading cards

		// calculate the extra armies players get
		armiesGet = calcExtraArmies(armiesGet);
		int count = 0; // count whether 3 cards have been removed from players' inventory
		for (int i = 0; i < cardSet.size(); i++) {
			if (cardSet.get(i).getType().equals(type) && count < 3) {
				// when card type is Infantry, and no more than 3 Infantry cards have been
				// removed,
				// remove this card from inventory.
				cardSet.remove(i); // remove the card from user inventory
				i--; // remove a card decreases the index
				count++; // one more card has been removed
			}
		}
		
		// Inform players that trade is successful and the extra army number players get from the trade
		tradeSuccess(ui, currPlayer,armiesGet);
	}
	
	// calculate the extra armies players get
	public int calcExtraArmies(int armiesGet) {
		if (tradeNumber < 6) {
			armiesGet = tradeNumber * 2 + 2;
		} else {
			armiesGet = (tradeNumber - 6) * 5 + 15;
		}
		return armiesGet;
	}
	
	public void cardRemover(ArrayList<Card> cardSet) {
		int first_i = 0; // to record the deletion of first infantry card
		int first_c = 0; // to record the deletion of first cavalry card
		int first_a = 0; // to record the deletion of first artillery card
		int first_w = 0; // to record the deletion of first wild card
		for (int i = 0; i < cardSet.size(); i++) {
			if (first_i == 0 && cardSet.get(i).getType().equals(Card.type.Infantry)) {
				// remove the first infantry card found
				cardSet.remove(i);
				i--;
				first_i++; // first infantry is found, no more infantry cards need to be delete
			} else if (first_c == 0 && cardSet.get(i).getType().equals(Card.type.Cavalry)) {
				// remove the first cavalry card found
				cardSet.remove(i);
				i--;
				first_c++;
			} else if (first_a == 0 && cardSet.get(i).getType().equals(Card.type.Artillery)) {
				// remove the first artillery card found
				cardSet.remove(i);
				i--;
				first_a++;
			} else if (first_w == 0 && cardSet.get(i).getType().equals(Card.type.Artillery)) {
				// remove the first artillery card found
				cardSet.remove(i);
				i--;
				first_w++;
			}	
		}
	}
}
