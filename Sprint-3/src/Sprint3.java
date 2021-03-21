
public class Sprint3 {

	public static void main(String args[]) {
		Board board = new Board();
		UI ui = new UI(board);
		Player[] players = new Player[GameData.NUM_PLAYERS_PLUS_NEUTRALS];
		Player currPlayer, winner = null, eliminatedPlayer = null;
		Card card;
		int playerId, countryId, numUnits = 0, numTerrs = 0, numCards;
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

		ui.displayString("\nDRAW TERRITORY CARDS FOR STARTING COUNTRIES");
		Deck deck = new Deck();
		for (playerId = 0; playerId < GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
			currPlayer = players[playerId];
			if (playerId < GameData.NUM_PLAYERS) {
				numCards = GameData.INIT_COUNTRIES_PLAYER;
			} else {
				numCards = GameData.INIT_COUNTRIES_NEUTRAL;
			}
			for (int i = 0; i < numCards; i++) {
				card = deck.getCard();
				ui.displayCardDraw(currPlayer, card);
				currPlayer.subtractUnits(1);
				board.addUnits(card, currPlayer, 1);
			}
		}
		
		ui.displayMap();

		ui.displayString("\nROLL DICE TO SEE WHO REINFORCES THEIR COUNTRIES FIRST");
		do {
			for (int i = 0; i < GameData.NUM_PLAYERS; i++) {
				players[i].rollDice(1);
				ui.displayDice(players[i]);
			}
		} while (players[0].getDie(0) == players[1].getDie(0));
		if (players[0].getDie(0) > players[1].getDie(0)) {
			playerId = 0;
		} else {
			playerId = 1;
		}
		
		currPlayer = players[playerId];
		ui.displayRollWinner(currPlayer);
/*
		ui.displayString("\nREINFORCE INITIAL COUNTRIES");
		while (currPlayer.getNumUnits() > 0) {
			ui.inputPlacement(currPlayer, currPlayer);
			countryId = ui.getCountryId();
			currPlayer.subtractUnits(3);
			board.addUnits(countryId, currPlayer, 3);
			ui.displayMap();
			for (int i=GameData.NUM_PLAYERS; i<GameData.NUM_PLAYERS_PLUS_NEUTRALS; i++) {
				ui.inputPlacement(currPlayer, players[i]);
				countryId = ui.getCountryId();
				currPlayer.subtractUnits(1);
				board.addUnits(countryId, currPlayer, 1);	
				ui.displayMap();
			}
			playerId = (++playerId)%GameData.NUM_PLAYERS;
			currPlayer = players[playerId];
		}
		*/
		/* Sprint3 start here: */
		
		// Roll dice first.
		ui.displayString("\nROLL DICE TO SEE WHO START FIRST");
		
		do {
			for (int i = 0; i < GameData.NUM_PLAYERS; i++) {
				players[i].rollDice(1);
				ui.displayDice(players[i]);
			}
		} while (players[0].getDie(0) == players[1].getDie(0));
		if (players[0].getDie(0) > players[1].getDie(0)) {
			playerId = 0;
		} else {
			playerId = 1;
		}
		currPlayer = players[playerId];
		ui.displayRollWinner(currPlayer);

		// Start turns here:
		ui.displayString("\n***START TURNS***\n<REINFORCEMENT PHASE>");
		while (true) {
			
			/* Reinforcement Phase */
			//System.out.println(currPlayer.getName() + " " + currPlayer.getNumTerrs() + " " + currPlayer.getNumUnits());
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
				ui.displayString("[Occupied Continent: true]");
			} else {
				ui.displayString("[Occupied Continent: false]");
			}
			ui.displayString("[num of reinforcement: " + reinforce_num + "]");
			int n = reinforce_num;
			
			while(reinforce_num!=0) {
				ui.displayString("Residual for distribute (" + reinforce_num + "/" + n + ")");
				//Ask user input country name and number of reinforcement to this country.
				int inputNum = ui.inputPlacement(currPlayer, reinforce_num);
				countryId = ui.getCountryId();
				board.addUnits(countryId, currPlayer, inputNum);
				reinforce_num -= inputNum;
				ui.displayMap();
			}
			
			
			/* Combat phase */
			ui.displayString("\n<COMBAT PHASE>");
			
			String combatChoice = ui.inputCombatChoice(currPlayer);
			while (!combatChoice.equals("skip")) {
				
				if (board.getPlayerArmyNum(currPlayer.getId()) == currPlayer.getNumTerrs()) {
					ui.displayString("You do not have extra army to combat. Combat phase ends automatically.");
					break;
				}
				ui.displayString(ui.makeLongName(currPlayer) + ": Tip: You can exit a single combat with skip whenever you want, and it will bring you back to the beginning of combat phase.");
				board.combat(ui, currPlayer, players);
				ui.displayMap();
				if (board.ifWin(ui, players, winner, currPlayer, eliminatedPlayer, playerId)==true)
					break;
				combatChoice = ui.inputCombatChoice(currPlayer);
			}
			

			/* Fortify phase */
			ui.displayString("\n<FORTIFY PHASE>");
			String fortifyChoice = ui.inputFortifyChoice(currPlayer);
			if (!fortifyChoice.equals("skip")) {
				if (board.getPlayerArmyNum(currPlayer.getId()) == currPlayer.getNumTerrs()) {
					ui.displayString("You do not have extra army to fortify. Fortify phase ends automatically.");
					break;
				}
				board.fortify(ui, currPlayer);
				ui.displayMap();
			}

			// move on to next player.
			playerId = (++playerId) % GameData.NUM_PLAYERS;
			currPlayer = players[playerId];
		}
		return;
	}

}
