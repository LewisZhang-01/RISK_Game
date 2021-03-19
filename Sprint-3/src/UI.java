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
	
	UI (Board inBoard) {
		board = inBoard;
		mapPanel = new MapPanel(board);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Risk");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mapPanel, BorderLayout.NORTH);
		frame.add(infoPanel, BorderLayout.CENTER);
		frame.add(commandPanel,BorderLayout.SOUTH);
		frame.setResizable(false);
		frame.setVisible(true);
		return;
	}
	
	public String makeLongName (Player player) {
		return player.getName() + " (" + mapPanel.getColorName(player.getId()) + ") ";
	}
	
	public void displayMap () {
		mapPanel.refresh();
		return;
	}

	public void displayString (String string) {
		infoPanel.addText(string);
		return;
	}
	
	public void displayName (int playerId, String name) {
		displayString("Neutral player " + (playerId+1) + " is " + mapPanel.getColorName(playerId));
		return;		
	}

	public void displayCardDraw (Player player, Card card) {
		displayString(makeLongName(player) + " draws the " + card.getCountryName() + " card");
		return;
	}
	
	public void displayDice (Player player) {
		displayString(makeLongName(player) + " rolls " + player.getDice() );
		return;
	}
	
	public void displayRollWinner (Player player) {
		displayString(makeLongName(player)  + " wins roll and goes first");
		return;
	}
	
	public String inputName (int playerId) {
		String response;
		displayString("Enter the name for player " + (playerId+1) + " (" + mapPanel.getColorName(playerId) + "):");
		response = commandPanel.getCommand();
		response.trim();
		displayString(PROMPT + response);
		return response;		
	}
		
	public void inputPlacement (Player byPlayer, Player forPlayer) {
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
	
	public int getCountryId () {
		return parse.getCountryId();
	}
	
	public void inputPlacementP1(Player byPlayer, Player forPlayer) {
		String response, message;
		boolean placementOK = false;
		do {
			//display messages
			message = makeLongName(byPlayer) + ": Enter a country to reinforce with ";
			if (byPlayer.equals(forPlayer)) {
				message += "your own units";				
			} else {
				message += makeLongName(forPlayer) + "'s units";
			}
			displayString(message);
			
			//get inputs & display
			response = commandPanel.getCommand();
			displayString(PROMPT + response);
			
			//handle error input.
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
	
	public String inputCombatChoice() {
		String response;
		displayString("Enter skip to skip the combat phase, or anything else to combat.");
		response = commandPanel.getCommand();
		return response;
	}
	
	public String inputFortifyChoice() {
		String response;
		displayString("Enter skip to skip the fortify phase, or anything else to fortify.");
		response = commandPanel.getCommand();
		return response;
	}
	
	public int inputModifiedTerritory(Player player, int mode) {	//mode 0 means the country chosen should belong to the player
																	//mode 1 means the country chosen does not belong to the player
		String response;
		response = commandPanel.getCommand();
		parse.countryId(response);
		if(mode == 1) {
			while(board.checkOccupier(player, parse.getCountryId())) {
				displayString("Error: Cannot choose your territory. Please choose another one:");
				response = commandPanel.getCommand();
				parse.countryId(response);
			}
		}else {
			while(!board.checkOccupier(player, parse.getCountryId())) {
				displayString("Error: You must choose your own territory. Please choose another one:");
				response = commandPanel.getCommand();
				parse.countryId(response);
			}
		}
		return parse.getCountryId();
	}
	
	public int inputArmyNum(int limit) {
		String response;
		response = commandPanel.getCommand();
		int armyNum;
		
		while(true) {
			try {
	        	armyNum = Integer.parseInt(response);
	            break;
	        }catch (NumberFormatException e) {
	        	displayString("Error: Input is not a number. Please enter a number:");
	        	response = commandPanel.getCommand();
	        }
	    }
		
		while(armyNum > limit || armyNum < 1) {
			displayString("You must leave at least one army on your territory.");
			displayString("Please enter a correct number:");
			
			response = commandPanel.getCommand();
			
			while(true) {
				try {
		        	armyNum = Integer.parseInt(response);
		            break;
		        }catch (NumberFormatException e) {
		        	displayString("Error: Input is not a number. Please enter a number:");
		        	response = commandPanel.getCommand();
		        }
		    }
		}
		
		return armyNum;
	}
	
	public int inputMovedArmyNumber(int least, int most) {
		String response;
		response = commandPanel.getCommand();
		int armyNum;
		
		while(true) {
			try {
	        	armyNum = Integer.parseInt(response);
	            break;
	        }catch (NumberFormatException e) {
	        	displayString("Error: Input is not a number. Please enter a number:");
	        	response = commandPanel.getCommand();
	        }
	    }
		
		while(armyNum < least || armyNum >= most) {
			displayString("You must move at least as many armies you used in the attack, and you need to leave at least one army on the current territory.");
			displayString("Please enter a correct number:");
			
			response = commandPanel.getCommand();
			
			while(true) {
				try {
		        	armyNum = Integer.parseInt(response);
		            break;
		        }catch (NumberFormatException e) {
		        	displayString("Error: Input is not a number. Please enter a number:");
		        	response = commandPanel.getCommand();
		        }
		    }
		}
		
		return armyNum;
	}
	
	public int[] attackAction(Player player) {
		
		displayString("Announce the territory you are attacking from:");
		int attackingTerritory = inputModifiedTerritory(player, 0);
		displayString("Announce the territory you are attacking:");
		int attackedTerritory = inputModifiedTerritory(player, 1);
		
		int numUnits = board.getNumUnits(attackingTerritory);
		while(numUnits <= 1) {
			displayString("Error: You must attack from a country that has more than one unit on it. Reselect the attacking territory:");
			displayString("Announce the territory you are attacking from:");
			attackingTerritory = inputModifiedTerritory(player, 0);
		}
		
		int[] adjacent = GameData.ADJACENT[attackingTerritory];
		boolean adjacentBool = false;
		
		for(int i = 0; i < adjacent.length; i++) {
			if(attackedTerritory == adjacent[i]) {
				adjacentBool = true;
				break;
			}
		}
		
		while(adjacentBool == false) {
			displayString("Error: Cannot attack territories that are not adjacent. "
							+ "Please reselect attacking territory and attacked territory:");
			
			displayString("Announce the territory you are attacking:");
			attackedTerritory = inputModifiedTerritory(player, 1);
			displayString("Announce the territory you are attacking from:");
			attackingTerritory = inputModifiedTerritory(player, 0);
			
			for(int i = 0; i < adjacent.length; i++) {
				if(attackedTerritory == adjacent[i]) {
					adjacentBool = true;
					break;
				}
			}
		}
		
		int[] attack = {attackedTerritory, attackingTerritory};
		return attack;
	}
	
public int[] fortifyAction(Player player) {
		displayString("Tip: Unlike combat phase, you can only do fortify once.");
		
		displayString("Announce the territory you are fortifying from:");
		int from = inputModifiedTerritory(player, 0);
		displayString("Announce the territory you are fortifying to:");
		int to = inputModifiedTerritory(player, 0);
		
		
		int numUnits = board.getNumUnits(from);
		while(numUnits <= 1) {
			displayString("Error: You must fortify from a country that has more than one unit on it. Reselect the territory:");
			displayString("Announce the territory you are fortifying from:");
			from = inputModifiedTerritory(player, 0);
		}
		
		int[] adjacent = GameData.ADJACENT[from];
		boolean adjacentBool = false;
		
		for(int i = 0; i < adjacent.length; i++) {
			if(to == adjacent[i]) {
				adjacentBool = true;
				break;
			}
		}
		
		while(adjacentBool == false) {
			displayString("Error: Cannot fortify territories that are not adjacent. "
							+ "Please reselect fortifyied territory and fortifing territory:");
			
			displayString("Announce the territory you are fortifying to:");
			to = inputModifiedTerritory(player, 0);
			displayString("Announce the territory you are fortifying from:");
			from = inputModifiedTerritory(player, 0);
			
			for(int i = 0; i < adjacent.length; i++) {
				if(to == adjacent[i]) {
					adjacentBool = true;
					break;
				}
			}
		}
		
		int[] fortify = {to, from};
		return fortify;
	}
}
