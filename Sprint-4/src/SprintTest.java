/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 */

/*
 * This class is used to skip the initial part and instead use the following default values:
 * Assign player 1 to red and player 2 to blue.
 * Let player 1 as first player to play.
 * Each player is assigned territories as below.
 *   > Red - North America
 *   > Blue - Europe
 *   > Yellow - Asia (Part of)
 *   > Green - Australia (Part of)
 *   > Magnet - South America
 *   > White - Africa
 * 
 */


import java.util.ArrayList;

public class SprintTest {

	public static void main(String args[]) {
		Board board = new Board();
		UI ui = new UI(board);
		Player[] players = new Player[GameData.NUM_PLAYERS_PLUS_NEUTRALS];
		Player currPlayer, otherPlayer, defencePlayer;
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
		// Red
		for (int i = 0; i < 9; i++) {
			board.addUnits(i, players[0], 50);
		}
		// Blue
		for (int i = 9; i < 18; i++) {
			board.addUnits(i, players[1], 3);
		}
		// Yellow
		for (int i = 18; i < 24; i++) {
			board.addUnits(i, players[2], 3);
		}
		// Green
		for (int i = 24; i < 30; i++) {
			board.addUnits(i, players[3], 3);
		}
		// Magnet
		for (int i = 30; i < 36; i++) {
			board.addUnits(i, players[4], 3);
		}
		// White
		for (int i = 36; i < 42; i++) {
			board.addUnits(i, players[5], 3);
		}
		ui.displayMap();

		ui.displayString("\n>>> START TURNS <<<");
		do {
			otherPlayerId = (playerId + 1) % GameData.NUM_PLAYERS;
			otherPlayer = players[otherPlayerId];
			int conquestRecord = 0;

			// 1. Reinforcements
			ui.displayString("\n***< Reinforcements Phase >***");
			numUnits = board.calcReinforcements(currPlayer);
			currPlayer.addUnits(numUnits);
			ui.displayReinforcements(currPlayer, numUnits);
			// Show card Sets
			deck.showCardSet(board, ui, playerId, numUnits, currPlayer, p1_cardset, p2_cardset);
			// Exchanging cards
			deck.tradeCard(board, ui, playerId, numUnits, currPlayer, p1_cardset, p2_cardset);
			numUnits = currPlayer.getNumUnits();
			do {
				ui.inputReinforcement(currPlayer,numUnits);
				currPlayer.subtractUnits(ui.getNumUnits());
				board.addUnits(ui.getCountryId(), currPlayer, ui.getNumUnits());
				ui.displayMap();
			} while (currPlayer.getNumUnits() > 0);

			// 2. Combat
			ui.displayString("\n***< Combat Phase >***");
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
			ui.displayString("\n***< Fortify Phase >***");
			if (!board.isGameOver()) {
				ui.inputFortify(currPlayer);
				if (!ui.isTurnEnded()) {
					board.subtractUnits(ui.getFromCountryId(), ui.getNumUnits());
					board.addUnits(ui.getToCountryId(), currPlayer, ui.getNumUnits());
					ui.displayMap();
				}
			}

			// 4. Draw Card
			// If player conquered at lease 1 territory, then player can get one card.
			if (conquestRecord >= 1) {
				ui.displayString("\n***< Draw Card Phase >***");
				deck.drawCard(board, ui, playerId, numUnits, currPlayer, p1_cardset, p2_cardset);
				// Show card Sets
				deck.showCardSet(board, ui, playerId, numUnits, currPlayer, p1_cardset, p2_cardset);
			}

			// Exchange of players
			ui.displayString("\n***< Exchange of players >***\n");
			playerId = (playerId + 1) % GameData.NUM_PLAYERS;
			currPlayer = players[playerId];

		} while (!board.isGameOver());

		ui.displayWinner(players[board.getWinner()]);
		ui.displayString("\n>>> GAME OVER <<<");

		return;

	}
}
