/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 * */

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class UI {

	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 800;
	private static String PROMPT = "> ";

	private JFrame frame = new JFrame();
	private MapPanel mapPanel;
	private InfoPanel infoPanel = new InfoPanel();
	private CommandPanel commandPanel = new CommandPanel();
	private Parse parse = new Parse();
	private Board board;

	UI(Board inBoard) {
		board = inBoard;
		mapPanel = new MapPanel(board);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Risk");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mapPanel, BorderLayout.NORTH);
		frame.add(infoPanel, BorderLayout.CENTER);
		frame.add(commandPanel, BorderLayout.SOUTH);
		frame.setResizable(false);
		frame.setVisible(true);
		return;
	}

	public String makeLongName(Player player) {
		return player.getName() + " (" + mapPanel.getColorName(player.getId()) + ") ";
	}

	public void displayMap() {
		mapPanel.refresh();
		return;
	}

	public void displayString(String string) {
		infoPanel.addText(string);
		return;
	}

	public void displayName(int playerId, String name) {
		displayString("Neutral player " + (playerId + 1) + " is " + mapPanel.getColorName(playerId));
		return;
	}

	public void displayCardDraw(Player player, Card card) {
		displayString(makeLongName(player) + " draws the " + card.getCountryName() + " card");
		return;
	}

	public void displayDice(Player player) {
		displayString(makeLongName(player) + " rolls " + player.getDice());
		return;
	}

	public void displayRollWinner(Player player) {
		displayString(makeLongName(player) + " wins roll and goes first");
		return;
	}

	public String inputName(int playerId) {
		String response;
		displayString("Enter the name for player " + (playerId + 1) + " (" + mapPanel.getColorName(playerId) + "):");
		response = commandPanel.getCommand();
		response.trim();
		displayString(PROMPT + response);
		return response;
	}

	public void inputPlacement(Player byPlayer, Player forPlayer) {
		String response, message;
		boolean placementOK = false;
		do {
			message = makeLongName(byPlayer) + ": Enter a country to reinforce with ";
			if (byPlayer.equals(forPlayer)) {
				message += "your own units";
			} else {
				message += makeLongName(forPlayer) + "'s units";
			}
			displayString(message);
			response = commandPanel.getCommand();
			displayString(PROMPT + response);
			parse.countryId(response);
			if (parse.isError()) {
				displayString("Error: Not a country");
			} else {
				if (!board.checkOccupier(forPlayer, parse.getCountryId())) {
					displayString("Error: Cannot place the units on that country");
				} else {
					placementOK = true;
				}
			}
		} while (!placementOK);
		return;
	}

	public int getCountryId() {
		return parse.getCountryId();
	}

	public void inputPlacementP1(Player byPlayer, Player forPlayer) {
		String response, message;
		boolean placementOK = false;
		do {
			// display messages
			message = makeLongName(byPlayer) + ": Enter a country to reinforce with ";
			if (byPlayer.equals(forPlayer)) {
				message += "your own units";
			} else {
				message += makeLongName(forPlayer) + "'s units";
			}
			displayString(message);

			// get inputs & display
			response = commandPanel.getCommand();
			displayString(PROMPT + response);

			// handle error input.
			parse.countryId(response);
			if (parse.isError()) {
				displayString("Error: Not a country");
			} else {
				if (!board.checkOccupier(forPlayer, parse.getCountryId())) {
					displayString("Error: Cannot place the units on that country");
				} else {
					placementOK = true;
				}
			}

		} while (!placementOK);
		return;
	}

	public int inputPlacement_re(Player Player, int num) {
		String message, response, countryID;
		int responseNum;
		boolean placementOK = false;
		// Ask user input a country name (country to be reinforced)
		do {
			// display messages
			message = makeLongName(Player) + ": Enter a country to reinforce with your own units";
			displayString(message);

			// get inputs & display
			countryID = commandPanel.getCommand();
			displayString(PROMPT + countryID);
			
			// handle error input.
			parse.countryId(countryID);
			if (parse.isError()) {
				displayString("Error: Not a country");
			} else {
				if (!board.checkOccupier(Player, parse.getCountryId())) {
					displayString("Error: Cannot place the units on that country");
				} else {
					placementOK = true;
				}
			}
		} while (!placementOK);
		
		// Ask user input reinforced number.
		placementOK = false;
		do {
			// display messages
			message = makeLongName(Player) + ": Enter the number of units.";
			displayString(message);

			// get inputs & display
			response = commandPanel.getCommand();
			displayString(PROMPT + response);
			while (true) {
				try {
					responseNum = Integer.parseInt(response);
					break;
				} catch (NumberFormatException e) {
					displayString("Error: Input is not a number. Please enter a number:");
					response = commandPanel.getCommand();
					displayString(PROMPT+response);
				}
			}

			// handle error input.
			if (responseNum>num || responseNum<0) {
				displayString("You need choose a num between 0~"+num);
			} else {
				placementOK = true;	
			}
		} while (!placementOK);
		
		return responseNum;
	}
	
	//allow user to enter skip to skip the combat phase
	public String inputCombatChoice(Player player) {
		String response;
		displayString(makeLongName(player) + ": Enter skip to skip the combat phase, or anything else to combat.");
		response = commandPanel.getCommand();
		displayString("> "+response);
		return response;
	}

	
	//allow user to enter skip to skip the fortify phase
	public String inputFortifyChoice(Player player) {
		String response;
		displayString(makeLongName(player) + ": Enter skip to skip the fortify phase, or anything else to fortify.\n"+
					  makeLongName(player) + ": Tip: You can exit this phase with skip whenever you want.");
		response = commandPanel.getCommand();
		displayString("> "+response);
		return response;
	}

	public int inputModifiedTerritory(Player player, int mode) {    // mode 0 means the country chosen should belong to the
																	// player
																	// mode 1 means the country chosen does not belong
																	// to the player
		String response;						//used to receive user input
		response = commandPanel.getCommand();	//receive user input
		displayString("> "+response);
		if(response.equals("skip")) {			//if user enter skip, return 404 to inform other functions that action needs to be exited immediately.
			return 404;
		}
		
		parse.countryId(response);				//try to parse the user input country string to countryId
		
		while(parse.isError()) {				//if parse failed, the user input country does not exist, have user try again
			displayString("Error: Not a country. Enter again:");
			response = commandPanel.getCommand();
			displayString("> "+response);
			if(response.equals("skip")) {
				return 404;
			}
			parse.countryId(response);
		}
		
		if (mode == 1) {						//the country chosen should not belong to the current player
			while (board.checkOccupier(player, parse.getCountryId())) {		//check if the country belongs to current player, have player try again
				displayString("Error: Cannot choose your territory. Please choose another one:");
				//repeated codes below. I will try my best to avoid this in the future.
				response = commandPanel.getCommand();
				displayString("> "+response);
				if(response.equals("skip")) {	//if skip entered, 404 is a signal to exit the current action immediately
					return 404;
				}
				parse.countryId(response);
				while(parse.isError()) {
					displayString("Error: Not a country. Enter again:");
					response = commandPanel.getCommand();
					displayString("> "+response);
					if(response.equals("skip")) {
						return 404;
					}
					parse.countryId(response);
				}
			}
		} else {
			//the country chosen should belong to the current player, almost the same as the previous condition
			while (!board.checkOccupier(player, parse.getCountryId())) {
				displayString("Error: You must choose your own territory. Please choose another one:");
				response = commandPanel.getCommand();
				displayString("> "+response);
				if(response.equals("skip")) {
					return 404;
				}
				parse.countryId(response);
				while(parse.isError()) {
					displayString("Error: Not a country. Enter again:");
					response = commandPanel.getCommand();
					displayString("> "+response);
					if(response.equals("skip")) {
						return 404;
					}
					parse.countryId(response);
				}
			}
		}
		return parse.getCountryId();
	}

	public int inputArmyNum(int limit) {		//have user input number of armies they want to do a combat or fortify
		String response;						//receive user input
		response = commandPanel.getCommand();
		displayString("> "+response);
		if(response.equals("skip")) {			//if skip entered, 404 is a signal to exit the current action immediately
			return 404;
		}
		int armyNum;

		while (true) {						//handle integer type errors.
			try {
				armyNum = Integer.parseInt(response);	//only if user enter an integer, game will continue
				break;
			} catch (NumberFormatException e) {
				displayString("Error: Input is not a number. Please enter a number:");
				response = commandPanel.getCommand();
				displayString("> "+response);
				if(response.equals("skip")) {
					return 404;
				}
			}
		}

		while (armyNum > limit || armyNum < 1) {		//if the army number is not in the range, have user try again
			displayString("You must leave at least one army on your territory.");
			displayString("Please enter a correct number:");

			response = commandPanel.getCommand();
			displayString("> "+response);
			if(response.equals("skip")) {
				return 404;					//if skip entered, 404 is a signal to exit the current action immediately
			}
			
			while (true) {
				try {
					armyNum = Integer.parseInt(response);
					break;
				} catch (NumberFormatException e) {
					displayString("Error: Input is not a number. Please enter a number:");
					response = commandPanel.getCommand();
					displayString("> "+response);
					if(response.equals("skip")) {
						return 404;
					}
				}
			}
		}

		return armyNum;	//return selected army number
	}

	public int inputMovedArmyNumber(int least, int most) {		//have users input number of armies they want move to their new territory
																//similar to previous method. Only some information and limit changes.
		String response;
		response = commandPanel.getCommand();
		displayString("> "+response);
		while(response.equals("skip")) {
			displayString("This step could not be skipped. You must move armies to your new territory.");
			response = commandPanel.getCommand();
			displayString("> "+response);
		}
		int armyNum;

		while (true) {
			try {
				armyNum = Integer.parseInt(response);
				break;
			} catch (NumberFormatException e) {
				displayString("Error: Input is not a number. Please enter a number:");
				response = commandPanel.getCommand();
				displayString("> "+response);
			}
		}

		while (armyNum < least || armyNum >= most) {
			displayString(
					"You must move at least as many armies you used in the attack, and you need to leave at least one army on the current territory.");
			displayString("Please enter a correct number:");

			response = commandPanel.getCommand();
			displayString("> "+response);
			if(response.equals("skip")) {
				return 404;
			}

			while (true) {
				try {
					armyNum = Integer.parseInt(response);
					break;
				} catch (NumberFormatException e) {
					displayString("Error: Input is not a number. Please enter a number:");
					response = commandPanel.getCommand();
					displayString("> "+response);
					if(response.equals("skip")) {
						return 404;
					}
				}
			}
		}

		return armyNum;
	}

	public int[] attackAction(Player player) {			//the ready phase of an attack action, prepare the information of 
														//attacking territory and defending territory
		
		displayString(makeLongName(player) + ": Announce the territory you are attacking from:");
		int attackingTerritory = inputModifiedTerritory(player, 0);	//have user input attacking territory
		
		if(attackingTerritory == 404) {			//if 404 detected, return null and then exit the combat action
			return null;
		}
		
		displayString(makeLongName(player) + ": Announce the territory you are attacking:");
		int attackedTerritory = inputModifiedTerritory(player, 1);	//have user input defending territory
		
		if(attackedTerritory == 404) {			//if 404 detected, return null and then exit the combat action
			return null;
		}

		int numUnits = board.getNumUnits(attackingTerritory);	//check whether the attacking territory has more than one armies on it
		while (numUnits <= 1) {
			displayString(
					"Error: You must attack from a country that has more than one unit on it. Reselect the attacking territory:");
			displayString(makeLongName(player) + ": Announce the territory you are attacking from:");
			attackingTerritory = inputModifiedTerritory(player, 0);
			
			if(attackingTerritory == 404) {		//if 404 detected, return null and then exit the combat action
				return null;
			}
		}

		
		//determine whether the attacking territory is adjacent to the defending territory
		int[] adjacent = GameData.ADJACENT[attackingTerritory];
		boolean adjacentBool = false;

		for (int i = 0; i < adjacent.length; i++) {
			if (attackedTerritory == adjacent[i]) {
				adjacentBool = true;
				break;
			}
		}

		while (adjacentBool == false) {			//if not adjacent, have user input attacking country and defending country until 
												//they enter right countries, or they can skip
			displayString("Error: Cannot attack territories that are not adjacent. "
					+ "Please reselect attacking territory and attacked territory:");

			displayString(makeLongName(player) + ": Announce the territory you are attacking from:");
			attackingTerritory = inputModifiedTerritory(player, 0);
			
			if(attackingTerritory == 404) {
				return null;
			}
			
			displayString(makeLongName(player) + ": Announce the territory you are attacking:");
			attackedTerritory = inputModifiedTerritory(player, 1);

			if(attackedTerritory == 404) {
				return null;
			}
			
			for (int i = 0; i < adjacent.length; i++) {
				if (attackedTerritory == adjacent[i]) {
					adjacentBool = true;
					break;
				}
			}
		}

		int[] attack = { attackedTerritory, attackingTerritory };	
		return attack;		//return the two territory ids
	}

	public int[] fortifyAction(Player player) {				//the ready phase of an fortify action, prepare the information of 
															//territory that sends armies and territory that receives armies
		displayString("Tip: Unlike combat phase, you can only do fortify once.");

		displayString(makeLongName(player) + ": Announce the territory you are fortifying from:");
		int from = inputModifiedTerritory(player, 0);		//territory that sends armies
		
		if(from == 404) {			//404 - the exit signal, appears when "skip" is entered
			return null;
		}
		
		displayString(makeLongName(player) + ": Announce the territory you are fortifying to:");
		int to = inputModifiedTerritory(player, 0);			//territory that receives armies
		
		if(to == 404) {				//404 - the exit signal, appears when "skip" is entered
			return null;
		}

		int numUnits = board.getNumUnits(from);		//only territory having more than one armies on it can send armies to other adjacent territories 
		while (numUnits <= 1) {
			displayString(
					"Error: You must fortify from a country that has more than one unit on it. Reselect the territory:");
			displayString(makeLongName(player) + ": Announce the territory you are fortifying from:");
			from = inputModifiedTerritory(player, 0);
			if(from == 404) {
				return null;
			}
		}
		
		
		//check whether the two selected territories are adjacent
		int[] adjacent = GameData.ADJACENT[from];
		boolean adjacentBool = false;

		for (int i = 0; i < adjacent.length; i++) {
			if (to == adjacent[i]) {
				adjacentBool = true;
				break;
			}
		}
		
		//if not adjacent, select again until select correct countries, or players can skip
		while (adjacentBool == false) {
			displayString("Error: Cannot fortify territories that are not adjacent. "
					+ "Please reselect fortifyied territory and fortifing territory:");

			displayString(makeLongName(player) + ": Announce the territory you are fortifying from:");
			from = inputModifiedTerritory(player, 0);
			if(from == 404) {
				return null;
			}
			
			displayString(makeLongName(player) + ": Announce the territory you are fortifying to:");
			to = inputModifiedTerritory(player, 0);
			if(to == 404) {
				return null;
			}

			for (int i = 0; i < adjacent.length; i++) {
				if (to == adjacent[i]) {
					adjacentBool = true;
					break;
				}
			}
		}

		int[] fortify = { to, from };
		return fortify;		//send the id of two selected countries back
	}


	public void displayWinner(Player player) {
		displayString(makeLongName(player) + " wins the game.");
		displayString("GAME OVER!");
		return;
	}
	
	public void displayeliminatedPlayers(Player player) {
		displayString(makeLongName(player) + " is eliminated.");
		return;
	}
}
