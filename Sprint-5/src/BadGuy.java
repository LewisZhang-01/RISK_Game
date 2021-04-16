// put your code here

public class BadGuy implements Bot {
	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects
	// So you can use player.getNumUnits() but you can't use player.addUnits(10000), for example
	
	/*    Total Bonus
	 * NA   9     5
	 * SA   4     2
	 * EU   7     5
	 * AF   6     3
	 * AS   12    7
	 * AU   4     2
	 */
	
	private BoardAPI board;
	private PlayerAPI player;
	
	int[] criticalCountry = {8,4,7,32,34,14,10,11,12,37,39,40,18,16,20,22,23,31};
	
	BadGuy (BoardAPI inBoard, PlayerAPI inPlayer) {
		board = inBoard;	
		player = inPlayer;
		// put your code here
		return;
	}
	
	public String getName () {
		String command = "";
		// put your code here
		command = "BadGuy";
		return(command);
	}

	public String getReinforcement () {
		String command = "";
		// put your code here
		command = GameData.COUNTRY_NAMES[(int)(Math.random() * GameData.NUM_COUNTRIES)];
		command = command.replaceAll("\\s", "");
		command += " 1";
		return(command);
	}
	
	public String getPlacement (int forPlayer) {
		String command = "";
		// put your code here
		command = GameData.COUNTRY_NAMES[(int)(Math.random() * GameData.NUM_COUNTRIES)];
		command = command.replaceAll("\\s", "");
		return(command);
	}
	
	public String getCardExchange () {
		String command = "";
		// put your code here
		command = "skip";
		return(command);
	}

	public int calcWeight(int countryId) {
		// Add strategy here:
		return 0;
	}
	
	// Get position info of us & opponent
	public void getCountryOnBoard(int[] own, int[] opp) {
		for(int id=0; id<GameData.NUM_COUNTRIES; id++) {
			if(board.getOccupier(id)==player.getId()) {
				own[id] = id;
			}else if(board.getOccupier(id)<GameData.NUM_PLAYERS){
				opp[id] = id;
			}else {
				own[id] = opp[id] = -1;
			}
		}
		
	}
	
	public String getBattle () {
		String command = "";
		// put your code here
		int[] own = new int[GameData.NUM_COUNTRIES];
		int[] opp = new int[GameData.NUM_COUNTRIES];
		getCountryOnBoard(own,opp);
		int maxWeight=0;
		int countryFrom=0;
		int countryTo=0;
		// Traverse and calculate the weights of all the countries that belong to us.
		for(int id=0;id<GameData.NUM_COUNTRIES;id++) {
			if(own[id]!=-1) {
				own[id] = calcWeight(id);//Store weight in the corresponding position.
				if(maxWeight <= own[id]) {
					maxWeight = own[id];
					countryFrom = id;
				}
			}
		}
		// Form a command
		if(maxWeight==-1) {
			command = "skip";
		}else {
			command = GameData.COUNTRY_NAMES[countryFrom] + " " + GameData.COUNTRY_NAMES[countryTo];
		}
		return(command);
	}

	public String getDefence (int countryId) {
		String command = "";
		// put your code here
		command = "1";
		return(command);
	}

	public String getMoveIn (int attackCountryId) {
		String command = "";
		// put your code here
		command = "0";
		return(command);
	}

	public String getFortify () {
		String command = "";
		// put code here
		command = "skip";
		return(command);
	}

}
