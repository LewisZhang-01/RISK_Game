import java.util.Arrays;

public class Sprint2 {

	private static void getName(UI ui, int playerId, String name, String[] nameList) {
		for (playerId = 0; playerId < GameData.NUM_PLAYERS; playerId++) {
			ui.displayString("Enter the name of player " + (playerId + 1));
			name = ui.getCommand();
			if(playerId>0) {
				name=duplicateCheck(ui, playerId, name, nameList);
			}
			nameList[playerId] = name;// store all the name in nameList
			ui.displayString("> " + nameList[playerId]);
		}
	}
	
	private static String duplicateCheck(UI ui, int playerId, String name, String[] nameList) {
		for (int id = 0; id < playerId; id++) {
			while (nameList[id].equals(name)) {
				ui.displayString("The user name exists, please input a new one!");
				name = ui.getCommand();
			}
		}
		return name;
	}

	private static int Dice() {
		return (int) (Math.random() * 6) + 1;
	}

	private static int sequence(UI ui, int playerId) {
		int[] dice = new int[GameData.NUM_PLAYERS];
		for (playerId = 0; playerId < GameData.NUM_PLAYERS; playerId++) {
			dice[playerId] = Dice();
		}
		int max = dice[0];
		int index = 0;
		for (int i = 0; i < dice.length; i++) {
			if (max < dice[i]) {
				max = dice[i];
				index = i;
			}
		}
		Arrays.sort(dice);
		if (dice[dice.length - 1] == dice[dice.length - 2]) {
			ui.displayString("\nReroll!\n");
			sequence(ui, playerId);
		} else {
			ui.displayString("\nThe player" + (index + 1) + " play first.\n");
		}
		return index;
	}

	public static void main(String args[]) {
		Board board = new Board();
		UI ui = new UI(board);
		ArmyPlace ap = new ArmyPlace();
		int playerId = 0 , countryId;
		String name = null;
		String[] nameList = new String[GameData.NUM_PLAYERS];

		// display blank board
		ui.displayMap();

		// get player names
		getName(ui, playerId, name, nameList);
		
		// add units
		countryId = 0;
		for (playerId = 0; playerId < GameData.NUM_PLAYERS; playerId++) {
			for (int i = 0; i < GameData.INIT_COUNTRIES_PLAYER; i++) {
				board.addUnits(countryId, playerId, 1);
				countryId++;
			}
		}
		for (; playerId < GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
			for (int i = 0; i < GameData.INIT_COUNTRIES_NEUTRAL; i++) {
				board.addUnits(countryId, playerId, 1);
				countryId++;
			}
		}

		// display map
		ui.displayMap();

		// roll dice to determine the sequence
		int first = sequence(ui, playerId);

		// place army
		ap.place(board, ui, playerId, first);

		ui.displayString("\nAllocation of armies completed.\n");

		for (int id = 0; id < GameData.NUM_PLAYERS_PLUS_NEUTRALS; id++) {
			ui.displayString("Player(" + (id + 1) + ") army number: " + ap.getPlayerArmyNum(board, id));
		}

		// display map
		ui.displayMap();

		return;
	}
}
