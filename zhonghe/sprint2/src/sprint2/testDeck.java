package sprint2;

import java.util.LinkedList;

public class testDeck {

	public static void main(String[] args) {
		Board board = new Board();
		UI ui = new UI(board);
		LinkedList<Card> p1_cards = new LinkedList<Card>();
		Deck d = new Deck();
		
		ui.displayMap();
		
		p1_cards.add(d.draw());
		p1_cards.add(d.draw());
		p1_cards.add(d.draw());
		p1_cards.add(d.draw());
		p1_cards.add(d.draw());
		
		d.display(ui, p1_cards);
		for(int i = 0; i<p1_cards.size();i++) {
			ui.displayString(p1_cards.get(i).toString());
		}
		
		d.trade(ui,board,0,p1_cards);
		
		
		d.display(ui, p1_cards);
		for(int i = 0; i<p1_cards.size();i++) {
			ui.displayString(p1_cards.get(i).toString());
		}

	}

}
