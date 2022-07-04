/*
 * 
 * This class is for testing only
 * 
 */

public class SillyBot implements Bot {
	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects
	// So you can use player.getNumUnits() but you can't use player.addUnits(10000), for example
	
	private BoardAPI board;
	private PlayerAPI player;
	
	SillyBot (BoardAPI inBoard, PlayerAPI inPlayer) {
		board = inBoard;	
		player = inPlayer;
		// put your code here
		return;
	}
	
	public String getName () {
		String command = "";
		// put your code here
		command = "SillyBot";
		return(command);
	}

	public String getReinforcement () {
		String command = "";
		// put your code here
		int[] own = new int[GameData.NUM_COUNTRIES];
		int index = 0;
		for(int id=0; id<GameData.NUM_COUNTRIES; id++) {
			if(board.getOccupier(id)==player.getId()) {
				own[index] = id;
				index++;
			}
		}
		command = GameData.COUNTRY_NAMES[own[(int)(Math.random() * index)]];
		command = command.replaceAll("\\s", "");
		command += " 1";
		return(command);
	}
	
	public String getPlacement (int forPlayer) {
		String command = "";
		// put your code here
		int[] own = new int[GameData.NUM_COUNTRIES];
		int index = 0;
		for(int id=0; id<GameData.NUM_COUNTRIES; id++) {
			if(board.getOccupier(id)==forPlayer) {
				own[index] = id;
				index++;
			}
		}
		
		command = GameData.COUNTRY_NAMES[own[(int)(Math.random() * index)]];
		command = command.replaceAll("\\s", "");
		return(command);
	}
	
	public String getCardExchange () {
		String command = "";
		// put your code here
		command = "skip";
		return(command);
	}

	public String getBattle () {
		String command = "";
		// put your code here
		command = "skip";
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