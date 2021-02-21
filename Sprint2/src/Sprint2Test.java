import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class Sprint2Test {

	Board board = new Board();
	UI ui = new UI(board);
	ArmyPlace ap = new ArmyPlace();
	/*@Test
	void test_Dice() {
	}
	
	@Test
	void test_sequence() {
		int playerId=0;
		for (playerId = 0; playerId < GameData.NUM_PLAYERS; playerId++) {
		
		}		
	}*/
	
	
	@Test
	void test_word_PLAYER_COLORS() {
		assertEquals("[color: Red ]","[color: "+MapPanel.word_PLAYER_COLORS[0]+" ]");
		String COLORS[] = {"Red","Blue","Yellow","Green","Magenta","White"};
		for(int i=0;i<6;i++) {
			assertEquals(COLORS[i],MapPanel.word_PLAYER_COLORS[i]);
		}
	}
	
	@Test
	void test_abbr_COUNTRY_NAMES() {
		for(int i=0;i<GameData.NUM_COUNTRIES;i++) {
			String territory_abbr = GameData.abbr_COUNTRY_NAMES[i];
			int num_abbr = board.getCountry(territory_abbr);
			String territory_whole = GameData.COUNTRY_NAMES[i];
			int num_whole = board.getCountry(territory_whole);
			assertEquals(num_abbr,num_whole);
		}
	}
	

	@Test
	void test_errorHandle() {
		int playerId=0;
		String territory = "CA";
		territory = ArmyPlace.errorHandle(board, ui, playerId, territory);
		assertEquals("CA",territory);
	}
	
	@Test
	void test_placeUnits() {
		int playerId=0;
		String territory = "China";
		int num = board.getCountry(territory);
		board.placeUnits(territory, playerId, 3);//place 3 unit to this country.
		assertEquals(3,board.getNumUnits(num));
	}
	
	@Test
	void test_getCountry() {
		String territory = "N Europe";
		int num = board.getCountry(territory);
		assertEquals(territory,GameData.COUNTRY_NAMES[num]);
	}
	
	@Test
	void test_playerPlace() {
		int playerId=0;
		String territory = "CA";
		board.placeUnits(territory, playerId, 6);
		int num = board.getCountry(territory);
		assertEquals(6,board.getNumUnits(num));
	}
	
	/*
	@Test
	void test_player_1_GoFirst() {
		int playerId = 0;
		int first = 0;
		if (first == 0) {
			// For normal players.
			for (playerId = first; playerId < GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
				if (playerId < 2) {
					if (ap.getPlayerArmyNum(board, playerId) == 36) {
						if (ap.checkPlayerArmyNum(board) == true) {
							break;
						}
					} else {
						ap.realPlayerPlace(ui, board, playerId);
					}
				} else {
					// For neutral players.
					if (ap.getPlayerArmyNum(board, playerId) == 24) {
						if (ap.checkPlayerArmyNum(board) == true) {
							break;
						}
					} else {
						ap.neutralPlayerPlace(ui, board, playerId);
					}
				}
			}
		}
	}
		
	
	@Test
	void test() {
		int playerId = 0;
		int first = 1;
		if (first > 0) {
			// For normal players.
			for (playerId = first; playerId < GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
				if (playerId < 2) {
					// For 1st player.
					if (ap.getPlayerArmyNum(board, playerId) == 36) {
						if (ap.checkPlayerArmyNum(board) == true) {
							break;
						}
					} else {
						ap.realPlayerPlace(ui, board, playerId);
					}
				} else {
					// For neutral players.
					if (ap.getPlayerArmyNum(board, playerId) == 24) {
						if (ap.checkPlayerArmyNum(board) == true) {
							break;
						}
					} else {
						ap.neutralPlayerPlace(ui, board, playerId);
					}
				}
			}
			for (playerId = 0; playerId < first; playerId++) {
				if (ap.getPlayerArmyNum(board, playerId) == 36) {
					if (ap.checkPlayerArmyNum(board) == true) {
						break;
					}
				} else {
					ap.realPlayerPlace(ui, board, playerId);
				}
			}
		}
	}
	*/
	
}
