import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class Test_Sprint3 {

	
	
	@Test
	void test_getOccupieContinent() {
		Board board = new Board();
		UI ui = new UI(board);
		Player[] players = new Player[GameData.NUM_PLAYERS_PLUS_NEUTRALS];
		int playerId, numUnits = 0;
		String name;
		
		ui.displayString("ENTER PLAYER NAMES");
		ui.displayMap();
		for (playerId = 0; playerId < GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
			if (playerId < GameData.NUM_PLAYERS) {
				name = ui.inputName(playerId);
				numUnits = GameData.INIT_UNITS_PLAYER;
			} else {
				name = "Neutral " + (playerId - GameData.NUM_PLAYERS + 1);
				ui.displayName(playerId, name);
				numUnits = GameData.INIT_UNITS_NEUTRAL;
			}
			players[playerId] = new Player(playerId, name, numUnits);
		}
		
		//hard code assign player to 0 (red)
		playerId = 0;
		//give player 0 (red) all countries and each country assign specific units.
		for(int i=0;i<42;i++) {
			board.addUnits(i, 0, i);
		}
		
		ui.displayMap();
			
		/* Reinforcement Phase */

		// Calculate number of reinforcements
		int reinforce_num = board.getNumOfCountry(playerId);
		if (reinforce_num / 3 < 3) {
			reinforce_num = 3;
		} else {
			reinforce_num = reinforce_num / 3;
		}
		int num = board.getOccupieContinent(playerId);
		if (num != -1) {
			reinforce_num += num;
		} 
		
		//42/3 + (5+5+7+2+2+3) = 14 + 24 = 38
		assertEquals(38,reinforce_num);
	}

	@Test
	void test_ifWin() {
		Board board = new Board();
		UI ui = new UI(board);
		Player[] players = new Player[GameData.NUM_PLAYERS_PLUS_NEUTRALS];
		Player currPlayer, winner = null, eliminatedPlayer = null;
		int playerId, numUnits = 0;
		String name;
		ui.displayString("ENTER PLAYER NAMES");
		ui.displayMap();
		for (playerId = 0; playerId < GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
			if (playerId < GameData.NUM_PLAYERS) {
				name = ui.inputName(playerId);
				numUnits = GameData.INIT_UNITS_PLAYER;
			} else {
				name = "Neutral " + (playerId - GameData.NUM_PLAYERS + 1);
				ui.displayName(playerId, name);
				numUnits = GameData.INIT_UNITS_NEUTRAL;
			}
			players[playerId] = new Player(playerId, name, numUnits);
		}
		
		//hard code assign player to 0 (red)
		playerId = 0;
		currPlayer = players[0];
		//give player 0 (red) all countries and each country assign specific units.
		for(int i=0;i<42;i++) {
			board.addUnits(i, 0, i);
		}
		
		ui.displayMap();
		
		//test if red player is win.
		assertTrue(board.ifWin(ui, players, winner, currPlayer, eliminatedPlayer, playerId));
	}
}
