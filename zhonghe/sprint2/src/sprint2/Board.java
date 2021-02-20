package sprint2;

public class Board {
	
	private boolean[] occupied = new boolean [GameData.NUM_COUNTRIES];
	private int[] occupier = new int [GameData.NUM_COUNTRIES];
	private int[] numUnits = new int [GameData.NUM_COUNTRIES];
	
	Board() {
		for (int i=0; i<GameData.NUM_COUNTRIES; i++) {
			occupied[i] = false ;
			numUnits[i] = 0;
		}
		return;
	}
	
	public void addUnits (int country, int player, int addNumUnits) {	
		// prerequisite: country must be unoccupied or already occupied by this player
		if (!occupied[country]) {
			occupied[country] = true;
			occupier[country] = player;
		}
		numUnits[country] = numUnits[country] + addNumUnits;
		return;
	}
	
	public boolean isOccupied(int country) {
		return occupied[country];
	}
	
	public int getOccupier (int country) {
		if(country==-1) {
			return -1;
		}
		return occupier[country];
	}
	
	public int getNumUnits (int country) {
		return numUnits[country];
	}

	public void placeUnits(String territory, int playerId, int unit_num) {
		for(int id=0;id<GameData.COUNTRY_NAMES.length;id++) {
			if(GameData.COUNTRY_NAMES[id].equals(territory)||GameData.abbr_COUNTRY_NAMES[id].equals(territory)) {
				addUnits(id, playerId, unit_num);
			}
		}
	}
	
	public int getCountry(String territory) {
		for(int id=0;id<GameData.COUNTRY_NAMES.length;id++) {
			if(GameData.COUNTRY_NAMES[id].equals(territory)||GameData.abbr_COUNTRY_NAMES[id].equals(territory)) {
				return id;
			}
		}
		return -1;
	}
}
