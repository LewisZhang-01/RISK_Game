/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 * */

import java.util.ArrayList;

public class Board {

	private boolean[] occupied = new boolean[GameData.NUM_COUNTRIES];
	private int[] occupier = new int[GameData.NUM_COUNTRIES];
	private int[] numUnits = new int[GameData.NUM_COUNTRIES];

	Board() {
		for (int i = 0; i < GameData.NUM_COUNTRIES; i++) {
			occupied[i] = false;
			numUnits[i] = 0;
		}
		return;
	}

	public void addUnits(Card card, Player player, int addNumUnits) {
		addUnits(card.getCountryId(), player, addNumUnits);
		return;
	}

	public void addUnits(int countryId, Player player, int addNumUnits) {
		// prerequisite: country must be unoccupied or already occupied by this player
		if (!occupied[countryId]) {
			occupied[countryId] = true;
			occupier[countryId] = player.getId();
			player.addTerrs(1);
		}
		numUnits[countryId] = numUnits[countryId] + addNumUnits;
		return;
	}

	public boolean checkOccupier(Player player, int countryId) {
		return (occupier[countryId] == player.getId());
	}

	public boolean isOccupied(int country) {
		return occupied[country];
	}

	public int getOccupier(int country) {
		return occupier[country];
	}

	public int getNumUnits(int country) {
		return numUnits[country];
	}

	// Traversal all countries to count the total number belonging to a given player.
	public int getNumOfCountry(int player) {
		int num = 0;
		for (int id = 0; id < GameData.NUM_COUNTRIES; id++) {
			if (occupier[id] == player) {
				num++;
			}
		}
		return num;
	}

	// 9 7 12 4 4 6
	public int getOccupieContinent (int player) {
		int num_NAmerica=0,num_Europe=0,num_Asia=0,num_Australia=0,num_SAmerica=0,num_Africa=0;
		// traversal all countries to check if each owner matches the given player.
		for(int id=0; id<GameData.NUM_COUNTRIES; id++) {
			
			if(occupier[id]==player) {
				// If any of the countries within the continent found a match, increment the correspond counter.
				if(GameData.CONTINENTS[id]==0) {
					if(occupier[id]==player) {
						num_NAmerica++;
					}
				}
				if(GameData.CONTINENTS[id]==1) {
					if(occupier[id]==player) {
						num_Europe++;
					}
				}
				if(GameData.CONTINENTS[id]==2) {
					if(occupier[id]==player) {
						num_Asia++;
					}
				}
				if(GameData.CONTINENTS[id]==3) {
					if(occupier[id]==player) {
						num_Australia++;
					}
				}
				if(GameData.CONTINENTS[id]==4) {
					if(occupier[id]==player) {
						num_SAmerica++;
					}
				}
				if(GameData.CONTINENTS[id]==5) {
					if(occupier[id]==player) {
						num_Africa++;
					}
				}
			}
		}
		
		/*
		System.out.println("[ player: " + player + "]\n" 
						+num_NAmerica+" for "+GameData.CONTINENT_NAMES[0]+"\n"
						+num_Europe+" for "+GameData.CONTINENT_NAMES[1]+"\n"
						+num_Asia+" for "+GameData.CONTINENT_NAMES[2]+"\n"
						+num_Australia+" for "+GameData.CONTINENT_NAMES[3]+"\n"
						+num_SAmerica+" for "+GameData.CONTINENT_NAMES[4]+"\n"
						+num_Africa+" for "+GameData.CONTINENT_NAMES[5]+"\n");
		 */
		
		//5 5 7 2 2 3
		// Check each counter to see if they have reached their maximum value.
		int sum = 0;
		if(num_NAmerica  == 9) { sum += 5;}
		if(num_Europe    == 7) { sum += 5;}
		if(num_Asia      == 12){ sum += 7;}
		if(num_Australia == 4) { sum += 2;}
		if(num_SAmerica  == 4) { sum += 2;}
		if(num_Africa    == 6) { sum += 3;}
		
		if(sum!=0) return sum;// At least one continent was occupied.
		return -1;// No continent was occupied.
	}

	public void combat(UI ui, Player player, Player[] players) {	//a single combat action
		
		int[] attack = ui.attackAction(player);		//int[] attack consists of two territory ids, one is the one that attacks and the other is being attacked.
		
		if(attack == null) {						//when user enter "skip", a null will be returned by attackAction,
													//and then we end the action immediately
			ui.displayString(ui.makeLongName(player) + ": This combat skipped.");
			return;
		}
		
		int attackedTerritory = attack[0]; 	// defending territory must have at least 1 army.
		int attackingTerritory = attack[1]; // the attackingTerritory must be greater than 1.
		int armyNum, limit;					//armyNum stores the army number used to attack; limit stores the limit of armyNum 

		if (getNumUnits(attackingTerritory) <= 3) {			//If there are more than three armies on the territory, only at most 3 can be
															//selected for a single attack action. Also at least 1 army is required to be 
															//left on the territory. 
			limit = getNumUnits(attackingTerritory) - 1;
		} else {
			limit = 3;
		}

		ui.displayString(
				ui.makeLongName(player) + ": Enter the number of armies you want to use for this attack.\nYou must leave at least 1 army on your"
						+ " territory and you can choose at most 3 armies.");
		armyNum = ui.inputArmyNum(limit);		//get user input of armyNum
		
		if(armyNum == 404) {					//when user input is "skip", 404 will be returned by inputArmyNum(), and we need to exit this 
												//attack action immediately. (I chose 404 for no reason. Only feels familiar to this number.)
			ui.displayString(ui.makeLongName(player) + ": This combat skipped.");
			return;
		}

		// roll dice
		player.rollDice(armyNum);		//attackers roll the dice as many times as the number of attacking army they choose 
		ArrayList<Integer> p1Dice = player.getDice();

		int p1Max = p1Dice.get(0), p1SMax = 0;	//p1Max store the max value of the attacker rolling dice
												//p1SMax store the second max value of the attacker rolling dice

		ui.displayString(ui.makeLongName(player) + ": The result of attacker rolling dice:");
		ui.displayString("" + player.getDice());	//display the result of dice to make the game fair

		for (int i = 0; i < p1Dice.size(); i++) {	//find the max value and second max value of the attacker rolling dice
			if (p1Max < p1Dice.get(i)) {
				p1SMax = p1Max;
				p1Max = p1Dice.get(i);
			} else {
				if (p1SMax < p1Dice.get(i)) {
					p1SMax = p1Dice.get(i);
				}
			}
		}

		//determine the number of army defender uses.
		//if only one army on the defending territory, roll dice once, else roll dice twice.
		if (getNumUnits(attackedTerritory) == 1) {
			player.rollDice(1);
		} else {
			player.rollDice(2);
		}
		//same steps as attacker rolling dice
		ArrayList<Integer> p2Dice = player.getDice();

		int p2Max = p2Dice.get(0), p2SMax = 0;

		ui.displayString(ui.makeLongName(player) + ": The result of defender rolling dice:");
		ui.displayString("" + player.getDice());

		for (int i = 0; i < p2Dice.size(); i++) {
			if (p2Max < p2Dice.get(i)) {
				p2SMax = p2Max;
				p2Max = p2Dice.get(i);
			} else {
				if (p2SMax < p2Dice.get(i)) {
					p2SMax = p2Dice.get(i);
				}
			}
		}
		//rolling dice finishes

		//determine the result of this combat:
		
		//if the max dice value of attacker is greater than the max dice value of defender, attacker wins and defender loses an army
		//if the max dice value of attacker is less than or equal to the max dice value of defender, defender wins and attacker loses an army
		
		//the same process will do again with second max dice values if both attacker and defender uses 2 or more armies in this combat.
		
		if (p1Max <= p2Max) {		
			ui.displayString(ui.makeLongName(player) + ": Defender wins the first battle. Attacker loses a unit.");
			numUnits[attackingTerritory] -= 1;
			players[occupier[attackingTerritory]].subtractUnits(1);
		} else {
			ui.displayString(ui.makeLongName(player) + ": Attacker wins the first battle. Defender loses a unit.");
			numUnits[attackedTerritory] -= 1;
			players[occupier[attackedTerritory]].subtractUnits(1);
		}

		if (p1Dice.size() > 1 && p2Dice.size() > 1) {
			if (p1SMax <= p2SMax) {
				ui.displayString(ui.makeLongName(player) + ": Defender wins the second battle. Attacker loses a unit.");
				numUnits[attackingTerritory] -= 1;
				players[occupier[attackingTerritory]].subtractUnits(1);
			} else {
				ui.displayString(ui.makeLongName(player) + ": Attacker wins the second battle. Defender loses a unit.");
				numUnits[attackedTerritory] -= 1;
				players[occupier[attackedTerritory]].subtractUnits(1);
			}
		}
		
		
		//determine whether the attacker conquers the territory:
		
		//if the army number of defending territory decreases to 0, defender loses the territory
		//and attacker wins this territory. Attacker is forced to move at least as many armies as
		//the number of armies he/she used in this combat to the new territory. Also at least one
		//army needs to be left on the original territory. This process cannot be skipped since
		//combat is finished and the result should be dealt with.
		if (numUnits[attackedTerritory] == 0) {
			players[occupier[attackedTerritory]].subtractTerrs(1);
			ui.displayString(ui.makeLongName(player) + ": Attacker wins the territory.\n"+ui.makeLongName(player) + ": Please move at least as many armies as you used "
					+ "in this attack to your new territory.\n"+ui.makeLongName(player) + ": Enter the number of armies you want to move:");
			int movedArmyNum = ui.inputMovedArmyNumber(armyNum, getNumUnits(attackingTerritory));
			
			numUnits[attackingTerritory] -= movedArmyNum;
			occupier[attackedTerritory] = player.getId();
			numUnits[attackedTerritory] = movedArmyNum;
			player.addTerrs(1);
		}

	}

	public void fortify(UI ui, Player player) {			//in my understanding of the rule of RISK GAME, only one fortify action is allowed in
														//a fortify phase, so here I only allowed one fortify to be done.

		int[] fortify = ui.fortifyAction(player);		//int[] fortify stores the territory that receives armies and the territory that sends armies.
		
		if(fortify == null) {
			ui.displayString(ui.makeLongName(player) + ": Fortify skipped.");
			return;
		}

		int to = fortify[0];			//to is the territory id of the territory that receives armies
		int from = fortify[1];			//from is the territory id of the territory that sends armies

		int armyNum, limit;				//similar to combat action. armyNum represents the number of armies being sent and limit is the limit of armyNum
		limit = getNumUnits(from) - 1;

		ui.displayString(
				ui.makeLongName(player) + ": Enter the number of armies you want to use for this fortify.\n"+ui.makeLongName(player) + 
				": You must leave at least 1 army on your territory.");
		armyNum = ui.inputArmyNum(limit);		//user input armyNum under limit
		
		if(armyNum == 404) {		//if user enter "skip", fortify action needs to be exited immediately, and fortify phase will end since the
									//only fortify action allowed is exited.
			ui.displayString(ui.makeLongName(player) + ": Fortify skipped.");
			return;
		}

		numUnits[to] += armyNum;		//receives armies
		numUnits[from] -= armyNum;		//sends armies
	}

	public boolean ifWin(UI ui, Player[] players, Player winner, Player currPlayer, Player eliminatedPlayer, int playerId) {
		
		for (playerId = 0; playerId < GameData.NUM_PLAYERS; playerId++) {
			if (getPlayerArmyNum(playerId) == 0) {
				if (playerId == 0)
					winner = players[1];
				else
					winner = players[0];
				ui.displayWinner(winner);
				return true;
			}
		}

		// Deal with neutral player armies are eliminated
		for (playerId = 2; playerId < 6; playerId++) {
			if (GameData.eliminatedPlayers[playerId] == 1) {
				if (getPlayerArmyNum(playerId) == 0) {
					GameData.eliminatedPlayers[playerId] = -1;
					eliminatedPlayer = players[playerId];
					ui.displayeliminatedPlayers(eliminatedPlayer);
					return false;
				}
			}
		}
		return false;
	}
	
	// get total army number of player through player id.
	public int getPlayerArmyNum(int playerId) {
		int armyNum = 0;
		for (int id = 0; id < GameData.COUNTRY_NAMES.length; id++) {
			if (getOccupier(id) == playerId) {
				armyNum += getNumUnits(id);
			}
		}
		return armyNum;
	}

}
