import java.util.ArrayList;

// put your code here

public class BadGuy implements Bot {
	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects
	// So you can use player.getNumUnits() but you can't use player.addUnits(10000),
	// for example

	/*
	 * Total Bonus NA 9 5 SA 4 2 EU 7 5 AF 6 3 AS 12 7 AU 4 2
	 */

	private BoardAPI board;
	private PlayerAPI player;

	private int[] criticalCountry = { 8, 4, 7, 32, 34, 14, 10, 11, 12, 37, 39, 40, 18, 16, 20, 22, 23, 31 };
	private int[] continentNum = { 9, 7, 13, 4, 4, 6 };
			
	BadGuy(BoardAPI inBoard, PlayerAPI inPlayer) {
		board = inBoard;
		player = inPlayer;
		// put your code here
		return;
	}

	public String getName() {
		String command = "";
		// put your code here
		command = "BadGuy";
		return (command);
	}

	// Get position info of own country
	public void getOwnCountryOnBoard(int[] own) {
		for (int id = 0; id < GameData.NUM_COUNTRIES; id++) {
			if (board.getOccupier(id) == player.getId()) {
				own[id] = id;
			} else {
				own[id] = -1;
			}
		}
	}

	// Get non-neutral player country.
	public void getOppCountryOnBoard(int[] opp) {
		for (int id = 0; id < GameData.NUM_COUNTRIES; id++) {
			if (board.getOccupier(id) != player.getId() && board.getOccupier(id) < GameData.NUM_PLAYERS) {
				opp[id] = id;
			} else {
				opp[id] = -1;
			}
		}
	}

	// Check if it is surrounded by its own countries.
	private boolean isSurrounded(int countryId) {
		int around_num = 0;
		int around_same_num = 0;
		boolean isSurrounded = false;// Is surrounded by own country.
		for (int i = 0; i < GameData.NUM_COUNTRIES; i++) {
			if (board.isAdjacent(countryId, i)) {
				// if adjacent.
				around_num++;
				// if adjacent is also our country.
				if (board.getOccupier(i) == player.getId()) {
					around_same_num++;
				}
			}
		}
		if(around_num == around_same_num) {
			isSurrounded = true;
		}
		return isSurrounded;
	}
	
	// Check if skip this attack round.
	private boolean isSkip(int countryId) {
		int around_opp_num = 0;
		int skip_num = 0;
		boolean isSkip = false;
		for (int i = 0; i < GameData.NUM_COUNTRIES; i++) {
			if (board.isAdjacent(countryId, i)) {				
				// if adjacent is opp country.
				if (board.getOccupier(i) != player.getId()) {
					// 
					around_opp_num++;
					// if own's unit < opp's unit
					if(board.getNumUnits(countryId) < board.getNumUnits(i)) {
						skip_num++;
					}
				}
			}
		}
		if(around_opp_num == skip_num && skip_num != 0) {
			isSkip = true;
		}
		return isSkip;
	}
	
	private int calcWeightForOwn(int countryId) {
		int weight = 1;
		
		/* Own feature */
		// 1. Check if it's unit is less than around opp country, just skip.
		if(isSkip(countryId) == true) {
			return -1;
		}
		
		// 2. Check if it is a critical country.
		boolean isCrirical = false;
		for (int check : criticalCountry) {
			if (check == countryId)
				isCrirical = true;
		}

		// 3. Check how many units it has.
		int numUnits = board.getNumUnits(countryId);
				
		/* Around feature */
		// 1. Check if it is surrounded by its own countries.
		boolean surroundedStatus = isSurrounded(countryId);
		// 2. Check if it in SA. Find out all the countries in SA that belong to me.
		boolean inSA = false;
		int countryInSA = 0;
		for (int i = 0; i < 4; i++) {
			if (GameData.CONTINENT_COUNTRIES[4][i] == countryId)
				inSA = true;
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[4][i]) == player.getId())
				countryInSA++;
		}

		// 3. Check if it in AU. Find out all the countries in AU that belong to me.
		boolean inAU = false;
		int countryInAU = 0;
		for (int i = 0; i < 4; i++) {
			if (GameData.CONTINENT_COUNTRIES[3][i] == countryId)
				inAU = true;
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[3][i]) == player.getId())
				countryInAU++;
		}

		/* Calculate weight */
		weight += numUnits;

		weight += countryInSA + countryInAU;
		
		if (inSA == true && isCrirical == true) {
			weight += 10;
		} else if (inSA == true || isCrirical == true) {
			weight += 5;
		}

		if (inAU == true && isCrirical == true) {
			weight += 10;
		} else if (inAU == true || isCrirical == true) {
			weight += 5;
		}
		
		if(surroundedStatus == true) {
			weight = 0;
		}

		return weight;
	}

	private int calcLeftOnContinent(int countryId) {
		int remain = 0;
		// Get continent id about current country.
		int continentId = GameData.CONTINENT_IDS[countryId];
		// Traverse all country in this continent.
		for(int i=0; i<continentNum[continentId]; i++) {
			// If current country is not occupier by us.
			if( board.getOccupier(GameData.CONTINENT_COUNTRIES[continentId][i]) != board.getOccupier(player.getId())) {
				remain++;
			}
		}
		return remain;
	}
	
	private int calcWeightForOpp(int countryId) {
		int weight = 100;
		int oppId = board.getOccupier(countryId);

		/* Own feature */
		// 1. Check if it is a critical country.
		boolean isCrirical = false;
		for (int check : criticalCountry) {
			if (check == countryId) {
				isCrirical = true;
			}
		}

		// 2. Check how many units it has.
		int numUnits = board.getNumUnits(countryId);

		// 3. Check if it is a non-neutral player's country.
		boolean isTarget = false;
		for(int id=0; id < GameData.NUM_PLAYERS; id++) {
			if(id == oppId && id != player.getId()) {
				isTarget = true;
			}
		}
				
		/* Around feature */
		// 1. Check if it in SA. Find out all the countries in SA that belong to opp.
		boolean inSA = false;
		int countryInSA = 0;
		for (int i = 0; i < 4; i++) {
			if (GameData.CONTINENT_COUNTRIES[4][i] == countryId) {
				inSA = true;
			}
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[4][i]) == oppId) {
				countryInSA++;
			}
		}

		// 2. Check if it in AU. Find out all the countries in AU that belong to me.
		boolean inAU = false;
		int countryInAU = 0;
		for (int i = 0; i < 4; i++) {
			if (GameData.CONTINENT_COUNTRIES[3][i] == countryId) {
				inAU = true;
			}
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[3][i]) == oppId) {
				countryInAU++;
			}
		}

		// 3. Check the remaining number of country in continent.
		int remain = calcLeftOnContinent(countryId);
		if(remain == 1) {
			weight += 100;
		}else if(remain == 2) {
			weight += 80;
		}else if(remain == 3) {
			weight += 60;
		}
		
		/* Calculate weight */
		// if it is a neutral player.
		if (oppId > GameData.NUM_PLAYERS) {
			weight += 5;
		}

		// If it is non-neutral player's country.
		if (isTarget == true) {
			weight += 7;
		}
		
		weight -= numUnits;

		weight -= countryInSA + countryInAU;

		if (inSA == true && isCrirical == true) {
			weight -= 10;
		} else if (inSA == true || isCrirical == true) {
			weight -= 5;
		}

		if (inAU == true && isCrirical == true) {
			weight -= 10;
		} else if (inAU == true || isCrirical == true) {
			weight -= 5;
		}

		return weight;
	}

	private int calcWeightForReiforcement(int countryId) {
		// Add strategy here:
		int weight = 1;
		/* Own feature */
		// 1. Check if it is a critical country.
		boolean isCrirical = false;
		for (int check : criticalCountry) {
			if (check == countryId)
				isCrirical = true;
		}

		/* Around feature */
		boolean surroundedStatus = isSurrounded(countryId);
		
		// 2. Check if it in SA. Find out all the countries in SA that belong to me.
		boolean inSA = false;
		int countryInSA = 0;
		for (int i = 0; i < 4; i++) {
			if (GameData.CONTINENT_COUNTRIES[4][i] == countryId)
				inSA = true;
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[4][i]) == player.getId())
				countryInSA++;
		}

		// 3. Check if it in AU. Find out all the countries in AU that belong to me.
		boolean inAU = false;
		int countryInAU = 0;
		for (int i = 0; i < 4; i++) {
			if (GameData.CONTINENT_COUNTRIES[3][i] == countryId)
				inAU = true;
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[3][i]) == player.getId())
				countryInAU++;
		}

		weight += countryInSA + countryInAU;
		
		if (inSA == true && isCrirical == true) {
			weight += 10;
		} else if (inSA == true || isCrirical == true) {
			weight += 5;
		}

		if (inAU == true && isCrirical == true) {
			weight += 10;
		} else if (inAU == true || isCrirical == true) {
            weight += 5;
		}

		if(surroundedStatus == true) {
			weight = 0;
		}

		return weight;	
	}
	
	public String getReinforcement() {
		String command = "";
		int[] own = new int[GameData.NUM_COUNTRIES];
		getOwnCountryOnBoard(own);
		int maxWeight = 0;
		int maxWeightid = 0;
		// compare every countries' weight and get the most important one.
		for (int id = 0; id < GameData.NUM_COUNTRIES; id++) {
			if (own[id]!=-1) {
				if ( calcWeightForReiforcement(id) > maxWeight) {
					maxWeight=calcWeightForReiforcement(id);
					maxWeightid = id;
				}
			}
		}
		command = GameData.COUNTRY_NAMES[own[maxWeightid]];
		command = command.replaceAll("\\s", "");
		command += " 1";
		return (command);
	}

	private int calcWeightForPlace(int countryId) {
		// Add strategy here:
		int weight = 100;
		int oppId = board.getOccupier(countryId);
		// System.out.println("oppId="+oppId);
		// 1. Check if it is a critical country.
		boolean isCrirical = false;
		for (int check : criticalCountry) {
			if (check == countryId)
				isCrirical = true;
		}

		// 2. Check how many units it has.
		int numUnits = board.getNumUnits(countryId);

		/* Around feature */
		// 2. Check if it in SA. Find out all the countries in SA that belong to opp.
		boolean inSA = false;
		int countryInSA = 0;
		for (int i = 0; i < 4; i++) {
			if (GameData.CONTINENT_COUNTRIES[4][i] == countryId)
				inSA = true;
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[4][i]) == oppId)
				countryInSA++;
		}

		// 3. Check if it in AU. Find out all the countries in AU that belong to me.
		boolean inAU = false;
		int countryInAU = 0;
		for (int i = 0; i < 4; i++) {
			if (GameData.CONTINENT_COUNTRIES[3][i] == countryId)
				inAU = true;
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[3][i]) == oppId)
				countryInAU++;
		}

		/* Calculate weight */
		// if it is a neutral player.
		if (oppId > GameData.NUM_PLAYERS) {
			weight += 5;
		}

		weight -= numUnits;

		weight -= countryInSA + countryInAU;

		if (inSA == true && isCrirical == true) {
			weight -= 10;
		} else if (inSA == true || isCrirical == true) {
			weight -= 5;
		}

		if (inAU == true && isCrirical == true) {
			weight -= 10;
		} else if (inAU == true || isCrirical == true) {
			weight -= 5;
		}

		return weight;
	}

	public String getPlacement(int forPlayer) {
		String command = "";
		// put your code here
		int[] opp = new int[GameData.NUM_COUNTRIES];
		int minWeight = 100;
		int minWeightid = 0;
		int index = 0;
		for (int id = 0; id < GameData.NUM_COUNTRIES; id++) {
			if(board.getOccupier(id)==forPlayer) {
				opp[index] = id;
				if ( calcWeightForPlace(id) < minWeight) {
					minWeight=calcWeightForPlace(id);
					minWeightid = index;
				}
				index++;
			}
		}
		command = GameData.COUNTRY_NAMES[opp[minWeightid]];
		command = command.replaceAll("\\s", "");
		return (command);
	}

	public String getCardExchange() {
//		System.out.println("ooooo");
		String command = "";
		// put your code here
		ArrayList<Card> cards = player.getCards();
		int i = 0, c = 0, a = 0, w = 0;
		for(int j = 0; j < cards.size(); j++) {		//{"Infantry","Cavalry","Artillary","Wild Card"};
			
//			System.out.println(cards.get(j).getInsigniaName());
			if(cards.get(j).getInsigniaName().equals("Infantry")) {
				i++;
			}else if(cards.get(j).getInsigniaName().equals("Cavalry")) {
				c++;
			}else if(cards.get(j).getInsigniaName().equals("Artillary")) {
				a++;
			}else if(cards.get(j).getInsigniaName().equals("Wild Card")) {
				w++;
			}
		}
		command = "skip";
		if(w == 0) {
			if(i >= 3) {
				command = "iii";
			}else if(c >= 3) {
				command = "ccc";
			}else if(a >= 3) {
				command = "aaa";
			}else if(i >= 1 && c >= 1 && a >= 1) {
				command = "ica";
			}
		}else if(w == 1) {
			if(i >= 2) {
				command = "iiw";
			}else if(c >= 2) {
				command = "ccw";
			}else if(a >= 2) {
				command = "aaw";
			}else if(i >= 1 && c >= 1) {
				command = "icw";
			}else if(i >= 1 && a >= 1) {
				command = "iaw";
			}else if(c >= 1 && a >= 1) {
				command = "caw";
			}
		}else if(w == 2){
			if(i >= 1) {
				command = "iww";
			}else if(c >= 1) {
				command = "cww";
			}else if(a >= 1) {
				command = "aww";
			}
		}
		return (command);
	}

	private int calcUnitAttak(int countryFrom) {
		int num = 0;
		int max = board.getNumUnits(countryFrom);
		if (max > 3) {
			num = 3;
		} else if (max == 3 || max == 2) {
			num = max - 1;
		} else if (max == 1) {
			num = -1;
		}
		return num;
	}

	public String getBattle() {
//		System.out.println("once");
		String command = "";
		int[] own = new int[GameData.NUM_COUNTRIES];
//		int[] opp = new int[GameData.NUM_COUNTRIES];
		int[][] ans = new int[GameData.NUM_COUNTRIES][3];
		// Country belong to us:
		getOwnCountryOnBoard(own);
		// Country belong to non-neutral player:
//		getOppCountryOnBoard(opp);
		// neutral is the rest country
		
		int countryFrom = 0;
		int countryTo = 0;
		int ownNum = 0;// Count the total number of own country on board.
		int skipNum = 0;// Count the total number of countries to skip.
		// Traverse and calculate the weights of all the countries that belong to us.
		for (int id = 0; id < GameData.NUM_COUNTRIES; id++) {
			if (own[id] != -1) {
//				System.out.println("from=" + id);
				ownNum++;
				own[id] = calcWeightForOwn(id);// Store weight in the corresponding position.
				if (own[id] == -1) {// Skip command.
					skipNum++;
				}

				/* Find around country attribute */
				int[] around = new int[6];
				int[] around_weight = new int[6];
				int max_ar_weight = 0;
				int index = 0;
				for (int i = 0; i < GameData.NUM_COUNTRIES; i++) {
					if (board.isAdjacent(id, i)) {
						// if adjacent is also our country , just skip.
						if (board.getOccupier(i) != player.getId()) {
//							System.out.println("from: "+id+" to: "+i);
							around[index] = i;
							around_weight[index] = calcWeightForOpp(i);
							// find max weight of opp
							if (around_weight[index] >= max_ar_weight) {
								max_ar_weight = around_weight[index];
								countryTo = around[index];
							}
							index++;
						}
					}
				}
				ans[id][0] = own[id];// weight
				ans[id][1] = id;// from
				ans[id][2] = countryTo;// to
			}
		}
		
		// If all countries want skip, then skip.
		if (ownNum == skipNum) {
			return "skip";
		}

		int maxWeight = 1;
		for (int i = 0; i < GameData.NUM_COUNTRIES; i++) {
//			System.out.println("ans "+ans[i][0]);		
			if (maxWeight <= ans[i][0]) {
				maxWeight = ans[i][0];
				countryFrom = ans[i][1];
				countryTo = ans[i][2];
			}
		}
//		System.out.println("final from: " + countryFrom + " to: " + countryTo);

		// Calculate the number of unit to attack
		int numToAttack = calcUnitAttak(countryFrom);
		if (numToAttack == -1) {
			return "skip";
		}
		// Form a command
		command = GameData.COUNTRY_NAMES[countryFrom].replaceAll("\\s", "") + " "
				+ GameData.COUNTRY_NAMES[countryTo].replaceAll("\\s", "") + " " 
				+ numToAttack;
		return command;
	}

	public String getDefence(int countryId) {
		String command = "";
		int num = board.getNumUnits(countryId);
		if (num > 3) {
			command = "2";
		} else if (num < 4 || num > 1) {
			command = "1";
		} 
		return (command);
	}

	public String getMoveIn(int attackCountryId) {
		  String command = "";
		  int units = board.getNumUnits(attackCountryId);
		   
		  int move = units - 1;
		  command = "" + move;
		 
		  return (command);
	}
/*
	public String getMoveIn(int attackCountryId) {
		String command = "";
		int units = board.getNumUnits(attackCountryId);
		boolean surrounded = true;
		int a[] = GameData.ADJACENT[attackCountryId];
		for (int i = 0; i < a.length; i++) {
			if (board.getOccupier(attackCountryId) != board.getOccupier(a[i])) {
				surrounded = false;
				break;
			}
		}

		if (!surrounded) {
			int move = units / 2;
			command = "" + move;
		} else {
			int move = units - 1;
			command = "" + move;
		}

		return (command);
	}
*/
	public String getFortify() {
		String command = "";
		int i = 0, j = 0;
		// put code here
		int own[] = new int[42];
		getOwnCountryOnBoard(own);
		int count = 0;
		int maxunit = 0;
		for(i = 0; i < 42; i++) {
			if(own[i] != -1) {
				count++;				
				if(maxunit < board.getNumUnits(own[i])) {
					maxunit = i;
				}
			}
		}
		int weight[] = new int[count];
		int id[] = new int[count];
		for(i = 0; i < 42; i++) {
			if(own[i] != -1) {
				weight[j] = calcWeightForOwn(own[i]);
				id[j++] = own[i];
			}
		}

		sort(weight, id);
		
		
		if(board.getNumUnits(maxunit) > 1) {
			command = GameData.COUNTRY_NAMES[own[maxunit]].replaceAll("\\s", "");
			int funits = board.getNumUnits(own[maxunit]) - 1;
			for(i = weight.length - 1 ; i >= 0; i--) {
				if(id[i] == maxunit) {
					command = "skip";
					break;
				}
				if(board.isConnected(own[maxunit], own[id[i]])) {
					command += " " + GameData.COUNTRY_NAMES[own[id[i]]].replaceAll("\\s", "") + " " + funits;
				}
			}
		}else {
			command = "skip";
		}
		
		return (command);
	}
	
	private void sort(int a[], int b[]) {
		int N = a.length;
		for(int i = 0; i<N; i++) {
			for(int j = i; j>0; j--) {
				if(a[j] < a[j-1]) {
					int temp = a[j];
					a[j] = a[j-1];
					a[j-1] = temp;
					
					temp = b[j];
					b[j] = b[j - 1];
					b[j-1] = temp;
				}else {
					break;
				}
			}
		}
	}

}
