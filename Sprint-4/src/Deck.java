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
		//Add extra 2 wild cards.
		for (cardId = GameData.NUM_COUNTRIES; cardId < GameData.NUM_COUNTRIES+2; cardId++) {
			cards.add(new Card(cardId, "Wild Card", GameData.cardType[cardId]));
		}
		return;
	}

	public Card getCard() {
		int index = (int) (Math.random() * cards.size());
		Card card = cards.remove(index);
		return card;
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	public Card getCard(int countryId) {
		Card card = cards.remove(countryId);
		return card;
	}

	private int Dice() {
		return (int) (Math.random() * cards.size());
	}
	
	public Card draw() {
		int cardNum = Dice(); // roll dice to decide which card is drawn
		Card removed = cards.remove(cardNum); // remove the card being drawn from the deck

		// display the card in another frame
		JFrame frame = new JFrame();
		frame.setSize(250, 450);
		frame.setTitle("Your Card");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		// information of the card
		JLabel cName = new JLabel(GameData.COUNTRY_NAMES[cardNum]);
		JLabel cType = new JLabel(GameData.cardType[cardNum].toString());

		// panel holds information
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.add(cName, BorderLayout.SOUTH);
		panel2.add(cType, BorderLayout.NORTH);

		ImageIcon icon = new ImageIcon(GameData.path[cardNum]);

		// put photo into lable
		JLabel label = new JLabel(icon);

		// set label size
		label.setBounds(5, 25, icon.getIconWidth(), icon.getIconHeight());

		// Gets the second layer of the window and places the Label in.
		frame.getLayeredPane().add(label, Integer.MIN_VALUE);

		// Gets the top-level container of the Frame and sets it to transparent.
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);

		// end

		// add information panel to the frame
		frame.add(panel1, BorderLayout.SOUTH);
		frame.add(panel2, BorderLayout.NORTH);
		frame.setVisible(true);

		return removed;
	}

	// Enable players to see what cards they own in another frame.
	public void display(UI ui, ArrayList<Card> cardSet) {
		JFrame frame = new JFrame();
		frame.setSize(1300, 450);
		frame.setTitle("Your Cards");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		for (int i = 0; i < cardSet.size(); i++) {
			// Add Card Photo Part as above
			int cardNo = cardSet.get(i).getCountryId();

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

			// end
		}

		frame.setVisible(true);
	}

	public void drawCard(Board board, UI ui, int playerId, Player currPlayer, ArrayList<Card> p1_cardset,
			ArrayList<Card> p2_cardset) {

		ui.displayString(
				"\nEnter \"draw\" to draw a card, anything else to place armies.(player " + (playerId + 1) + ")\n");
		String mode = ui.inputDrawChoose();

		if (mode.equals("draw")) {
			if (playerId == 0) {
				p1_cardset.add(draw());
			} else {
				p2_cardset.add(draw());
			}
		}

		ui.displayString("\nEnter \"trade\" to check and trade your cards, or skip to skip.\n");
		String mode2 = ui.inputTradeChoose();
		if (mode2.equals("trade")) {
			if (playerId == 0) {
				display(ui, p1_cardset);
				ui.displayString("Player 1 has: ");
				for (int i = 0; i < p1_cardset.size(); i++) {
					ui.displayString(p1_cardset.get(i).toString());
				}
				ui.displayString("Please enter \"trade\" to automatically trade your cards,or skip to skip.");
				String trade = ui.inputTradeChoose();
				if (trade.equals("trade")) {
					trade(ui, board, playerId, currPlayer, p1_cardset);
				}
			} else {
				display(ui, p2_cardset);
				ui.displayString("Player 2 has: ");
				for (int i = 0; i < p2_cardset.size(); i++) {
					ui.displayString(p2_cardset.get(i).toString());
				}
				ui.displayString("Please enter \"trade\" to automatically trade your cards,or skip to skip.");
				String trade = ui.inputTradeChoose();
				if (trade.equals("trade")) {
					trade(ui, board, playerId, currPlayer, p2_cardset);
				}
			}
		} else if (p1_cardset.size() == 5) {
			ui.displayString("You could only have at most 5 cards. Auto-trade is forced to process now.");
			trade(ui, board, playerId, currPlayer, p1_cardset);
		} else if (p2_cardset.size() == 5) {
			ui.displayString("You could only have at most 5 cards. Auto-trade is forced to process now.");
			trade(ui, board, playerId, currPlayer, p2_cardset);
		}
	}

	public ArrayList<Card> trade(UI ui, Board board, int playerId, Player currPlayer, ArrayList<Card> cardSet) {
		int inf, art, cav;
		inf = art = cav = 0; // inf for type Infantry, art for type Artillery, cav for type Cavalry

		// count the number of each type of cards player own
		for (int i = 0; i < cardSet.size(); i++) {
			switch (cardSet.get(i).getType()) {
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

		if (inf >= 3 || art >= 3 || cav >= 3 || (inf > 0 && art > 0 && cav > 0)) { // when player have a set of cards
			int armiesGet = 0; // the number of extra armies players get from trading cards

			// calculate the extra armies players get
			if (tradeNumber < 6) {
				armiesGet = tradeNumber * 2 + 2;
			} else {
				armiesGet = (tradeNumber - 6) * 5 + 15;
			}

			if (inf >= 3) { // 3 infantry set
				int count = 0; // count whether 3 cards have been removed from players' inventory
				for (int i = 0; i < cardSet.size(); i++) {
					if (cardSet.get(i).getType().equals(Card.type.Infantry) && count < 3) {
						// when card type is Infantry, and no more than 3 Infantry cards have been
						// removed,
						// remove this card from inventory.
						cards.add(cardSet.remove(i)); // remove the card from user inventory and add it back to game
															// deck
						i--; // remove a card decreases the index
						count++; // one more card has been removed
					}
				}
			} else if (art >= 3) { // 3 artillery set, similar to infantry
				int count = 0;
				for (int i = 0; i < cardSet.size(); i++) {
					if (cardSet.get(i).getType().equals(Card.type.Artillery) && count < 3) {
						cards.add(cardSet.remove(i));
						i--;
						count++;
					}
				}
			} else if (cav >= 3) { // 3 cavalry set, similar to infantry
				int count = 0;
				for (int i = 0; i < cardSet.size(); i++) {
					if (cardSet.get(i).getType().equals(Card.type.Cavalry) && count < 3) {
						cards.add(cardSet.remove(i));
						i--;
						count++;
					}
				}
			} else { // one card of each type also form a set
				int first_i = 0; // to record the deletion of first infantry card
				int first_c = 0; // to record the deletion of first cavalry card
				int first_a = 0; // to record the deletion of first artillery card
				for (int i = 0; i < cardSet.size(); i++) {
					if (first_i == 0 && cardSet.get(i).getType().equals(Card.type.Infantry)) {
						// remove the first infantry card found
						cards.add(cardSet.remove(i));
						i--;
						first_i++; // first infantry is found, no more infantry cards need to be delete
					} else if (first_c == 0 && cardSet.get(i).getType().equals(Card.type.Cavalry)) {
						// remove the first cavalry card found
						cards.add(cardSet.remove(i));
						i--;
						first_c++;
					} else if (first_a == 0 && cardSet.get(i).getType().equals(Card.type.Artillery)) {
						// remove the first artillery card found
						cards.add(cardSet.remove(i));
						i--;
						first_a++;
					}
				}
			}
			// inform players that trade is successful and the extra army number players get
			// from the trade
			ui.displayString("Trade Success. The " + tradeNumber + " set is traded. You earn " + armiesGet
					+ " armies to place. Traded cards are removed.");
			tradeNumber += 1; // one trade has been done; next trade would be more valuable
			ui.displayString("Place your extra armies:"); // inform players to place extra armies they get from trading
															// cards
			// put extra army
			currPlayer.addUnits(armiesGet);
			ui.displayReinforcements(currPlayer, armiesGet);
			do {
				ui.inputReinforcement(currPlayer);
				currPlayer.subtractUnits(ui.getNumUnits());
				board.addUnits(ui.getCountryId(), currPlayer, ui.getNumUnits());
				ui.displayMap();
			} while (currPlayer.getNumUnits() > 0);

		} else {
			ui.displayString("Trade false. No set fount in your cards."); // if trade fails, do nothing
		}
		return cardSet;
	}
}
