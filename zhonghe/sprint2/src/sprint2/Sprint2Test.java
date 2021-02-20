import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class Sprint2Test {

	Board board = new Board();
	UI ui = new UI(board);
	
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
		territory = Sprint2.errorHandle(board, ui, playerId, territory);
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
		
}
