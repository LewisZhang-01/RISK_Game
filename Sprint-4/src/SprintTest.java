import java.util.ArrayList;

public class SprintTest {

	public static void main(String args[]) {
		Board board = new Board();
		UI ui = new UI(board);
		Player[] players = new Player[GameData.NUM_PLAYERS_PLUS_NEUTRALS];
		Player currPlayer, otherPlayer, defencePlayer;
		Deck armiesGet;
		int playerId, otherPlayerId, numUnits, attackUnits, defenceUnits, attackCountryId, defenceCountryId;
		String name;
		Deck deck = new Deck();
		ArrayList<Card> p1_cardset = new ArrayList<Card>();
		ArrayList<Card> p2_cardset = new ArrayList<Card>();

		ui.displayString("***********TEST***********");
		ui.displayString("ENTER PLAYER NAMES");
		ui.displayMap();
		for (playerId = 0; playerId < GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
			if (playerId < GameData.NUM_PLAYERS) {
				name = ui.inputName(playerId);
			} else {
				name = "Neutral " + (playerId - GameData.NUM_PLAYERS + 1);
				ui.displayName(playerId, name);
			}
			players[playerId] = new Player(playerId, name, 0);
		}

		// hard code assign player to 0 (red)
		playerId = 0;
		currPlayer = players[0];
		// give player 0 (red) all countries and each country assign specific units.
		// red
		for (int i = 0; i < 9; i++) {
			board.addUnits(i, players[0], 50);
		}
		// blue
		for (int i = 9; i < 18; i++) {
			board.addUnits(i, players[1], 3);
		}
		// yello
		for (int i = 18; i < 24; i++) {
			board.addUnits(i, players[2], 3);
		}
		// green
		for (int i = 24; i < 30; i++) {
			board.addUnits(i, players[3], 3);
		}
		// magent
		for (int i = 30; i < 36; i++) {
			board.addUnits(i, players[4], 3);
		}
		// white
		for (int i = 36; i < 42; i++) {
			board.addUnits(i, players[5], 3);
		}
		ui.displayMap();

		ui.displayString("\nSTART TURNS");
		do {
			otherPlayerId = (playerId + 1) % GameData.NUM_PLAYERS;
			otherPlayer = players[otherPlayerId];
			int conquestRecord = 0;

			// 1. Reinforcements
			numUnits = board.calcReinforcements(currPlayer);
			currPlayer.addUnits(numUnits);
			ui.displayReinforcements(currPlayer, numUnits);
			// Exchanging cards
			ui.displayString("Player [" + currPlayer.getName() + "] TRADE TERRITORY CARDS");
			deck.tradeCard(board, ui, playerId, numUnits, currPlayer, p1_cardset, p2_cardset);
			do {
				ui.inputReinforcement(currPlayer);
				currPlayer.subtractUnits(ui.getNumUnits());
				board.addUnits(ui.getCountryId(), currPlayer, ui.getNumUnits());
				ui.displayMap();
			} while (currPlayer.getNumUnits() > 0);

			// 2. Combat
			do {
				ui.inputBattle(currPlayer);
				if (!ui.isTurnEnded()) {
					attackUnits = ui.getNumUnits();
					attackCountryId = ui.getFromCountryId();
					defenceCountryId = ui.getToCountryId();
					defencePlayer = players[board.getOccupier(defenceCountryId)];
					if (board.getNumUnits(defenceCountryId) > 1) {
						ui.inputDefence(otherPlayer, defenceCountryId);
						defenceUnits = ui.getNumUnits();
					} else {
						defenceUnits = 1;
					}
					board.calcBattle(currPlayer, defencePlayer, attackCountryId, defenceCountryId, attackUnits,
							defenceUnits);
					ui.displayBattle(currPlayer, defencePlayer);
					ui.displayMap();

					if (board.isInvasionSuccess() && (board.getNumUnits(attackCountryId) > 1)) {
						ui.inputMoveIn(currPlayer, attackCountryId);
						board.subtractUnits(attackCountryId, ui.getNumUnits());
						board.addUnits(defenceCountryId, currPlayer, ui.getNumUnits());
						ui.displayMap();

						conquestRecord++;
					}
				}

			} while (!ui.isTurnEnded() && !board.isGameOver());

			// 3. Fortify
			if (!board.isGameOver()) {
				ui.inputFortify(currPlayer);
				if (!ui.isTurnEnded()) {
					board.subtractUnits(ui.getFromCountryId(), ui.getNumUnits());
					board.addUnits(ui.getToCountryId(), currPlayer, ui.getNumUnits());
					ui.displayMap();
				}
			}

			/* Card part */
			// if player conquered at lease 1 territory, then player can get one card.
			if (conquestRecord >= 1) {
				ui.displayString("Player [" + currPlayer.getName() + "] DRAW TERRITORY CARDS");
				deck.drawCard(board, ui, playerId, numUnits, currPlayer, p1_cardset, p2_cardset);
			}

			playerId = (playerId + 1) % GameData.NUM_PLAYERS;
			currPlayer = players[playerId];

		} while (!board.isGameOver());

		ui.displayWinner(players[board.getWinner()]);
		ui.displayString("GAME OVER");

		return;

	}

}
