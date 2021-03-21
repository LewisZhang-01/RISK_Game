/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 * */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Sprint3Test {

	@Test
	void test_getOccupieContinent() {
		Board board = new Board();
		UI ui = new UI(board);
		Player[] players = new Player[GameData.NUM_PLAYERS_PLUS_NEUTRALS];
		int playerId, numUnits = 0, numTerrs = 0;
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
			players[playerId] = new Player(playerId, name, numUnits, numTerrs);
		}
		
		//hard code assign player to 0 (red)
		playerId = 0;
		//give player 0 (red) all countries and each country assign specific units.
		for(int i=0;i<42;i++) {
			board.addUnits(i, players[0], i);
		}
		
		ui.displayMap();

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
		int playerId, numUnits = 0, numTerrs = 0;
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
			players[playerId] = new Player(playerId, name, numUnits, numTerrs);
		}
		
		//hard code assign player to 0 (red)
		playerId = 0;
		currPlayer = players[0];
		//give player 0 (red) all countries and each country assign specific units.
		for(int i=0;i<42;i++) {
			board.addUnits(i, players[0], i);
		}
		
		ui.displayMap();
		
		//test if red player is win.
		assertTrue(board.ifWin(ui, players, winner, currPlayer, eliminatedPlayer, playerId));
	}
	
	/***The following test need to be manually checked***/
	/*** add "while(true) {}" at the end to keep the map open ***/
	@Test
	// Check if neutral can eliminate. 
	void test_neutral_eliminated() {
		Board board = new Board();
		UI ui = new UI(board);
		Player[] players = new Player[GameData.NUM_PLAYERS_PLUS_NEUTRALS];
		Player currPlayer, winner = null, eliminatedPlayer = null;
		int playerId, numUnits = 0, numTerrs = 0;
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
			players[playerId] = new Player(playerId, name, numUnits, numTerrs);
		}
		
		//hard code assign player to 0 (red)
		playerId = 0;
		currPlayer = players[0];
		//give player 0 (red) all countries and each country assign specific units.
		for(int i=0;i<41;i++) {
			board.addUnits(i, players[0], i);
		}
		board.addUnits(41, players[1], 41);
		ui.displayMap();
		
		for (int i=0;i<GameData.NUM_NEUTRALS;i++) {
			System.out.println("i="+i);
			System.out.println(board.ifWin(ui, players, winner, currPlayer, eliminatedPlayer, playerId));
		}
		//while(true) {}
	}
	
}
