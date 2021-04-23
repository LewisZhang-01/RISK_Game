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

	// Get position info of own country
	public void getOwnCountryOnBoard(int[] own) {
		for(int id=0; id<GameData.NUM_COUNTRIES; id++) {
			if(board.getOccupier(id)==player.getId()) {
				own[id] = id;
			}else {
				own[id] = -1;
			}
		}
	}
	
	// Get non-neutral player country.
	public void getOppCountryOnBoard(int[] opp) {
		for(int id=0; id<GameData.NUM_COUNTRIES; id++) {
			if(board.getOccupier(id)!=player.getId() && board.getOccupier(id)<GameData.NUM_PLAYERS){
				opp[id] = id;
			}else {
				opp[id] = -1;
			}
		}
	}
	
	private int calcWeightForOwn(int countryId) {
		// Add strategy here:
		int weight = 0;
		boolean skip = false;
		/* Own feature */
		// 1. Check if it is a critical country. 
		boolean isCrirical = false;
		for (int check: criticalCountry) {
			if (check == countryId) isCrirical = true;
		}
		
		// 2. Check how many units it has.
		int numUnits = board.getNumUnits(countryId);
		
		/* Around feature */
		// 2. Check if it in SA. Find out all the countries in SA that belong to me.
		boolean inSA = false;
		int countryInSA = 0;
		for (int i=0; i<4; i++) {
			if (GameData.CONTINENT_COUNTRIES[4][i] == countryId) inSA = true;
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[4][i]) == player.getId()) countryInSA++;
		}

		// 3. Check if it in AU. Find out all the countries in AU that belong to me.
		boolean inAU = false;
		int countryInAU = 0;
		for (int i=0; i<4; i++) {
			if (GameData.CONTINENT_COUNTRIES[3][i] == countryId) inAU = true;
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[3][i]) == player.getId()) countryInAU++;
		}

		/* Calculate weight */
		weight += numUnits; 
		
		weight += countryInSA + countryInAU;
		
		if(inSA == true && isCrirical == true) {
			weight += 10;
		}else if(inSA == true || isCrirical == true) {
			weight += 5;
		}
		
		if(inAU == true && isCrirical == true) {
			weight += 10;
		}else if(inAU == true || isCrirical == true) {
			weight += 5;
		}
		
		// skip condition:
		//if(weight <= 5) {
		//	skip = true;
		//}

		if(skip==true) {
			return -1;
		}else {
			return weight;
		}
	}
	
	private int calcWeightForOpp(int countryId) {
		// Add strategy here:
		int weight = 100;
		int oppId = board.getOccupier(countryId);
		//System.out.println("oppId="+oppId);
		// 1. Check if it is a critical country. 
		boolean isCrirical = false;
		for (int check: criticalCountry) {
			if (check == countryId) isCrirical = true;
		}
		
		// 2. Check how many units it has.
		int numUnits = board.getNumUnits(countryId);
		
		/* Around feature */
		// 2. Check if it in SA. Find out all the countries in SA that belong to opp.
		boolean inSA = false;
		int countryInSA = 0;
		for (int i=0; i<4; i++) {
			if (GameData.CONTINENT_COUNTRIES[4][i] == countryId) inSA = true;
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[4][i]) == oppId) countryInSA++;
		}

		// 3. Check if it in AU. Find out all the countries in AU that belong to me.
		boolean inAU = false;
		int countryInAU = 0;
		for (int i=0; i<4; i++) {
			if (GameData.CONTINENT_COUNTRIES[3][i] == countryId) inAU = true;
			if (board.getOccupier(GameData.CONTINENT_COUNTRIES[3][i]) == oppId) countryInAU++;
		}
		
		/* Calculate weight */
		// if it is a neutral player.
		if(oppId > GameData.NUM_PLAYERS) {
			weight += 5;
		}
		
		weight -= numUnits; 
		
		weight -= countryInSA + countryInAU;
		
		if(inSA == true && isCrirical == true) {
			weight -= 10;
		}else if(inSA == true || isCrirical == true) {
			weight -= 5;
		}
		
		if(inAU == true && isCrirical == true) {
			weight -= 10;
		}else if(inAU == true || isCrirical == true) {
			weight -= 5;
		}
		
		return weight;
	}
	
	private int calcUnitAttak(int countryFrom) {
		int num = 0;
		int max = board.getNumUnits(countryFrom);
		if(max > 3) {
			num = 3;
		}else if(max == 3 || max == 2) {
			num = max-1;
		}else if(max == 1) {
			num = -1;
		}
		return num;
	}
	
	public String getBattle () {
		System.out.println("once");
		String command = "";
		// put your code here
		int[] own = new int[GameData.NUM_COUNTRIES];
		int[] opp = new int[GameData.NUM_COUNTRIES];
		int[] to = new int[GameData.NUM_COUNTRIES];
		int[][] ans = new int[GameData.NUM_COUNTRIES][3];
		// Country belong to us:
		getOwnCountryOnBoard(own);
		// Country belong to non-neutral player:
		getOppCountryOnBoard(opp);
		// neutral is the rest country
		
		int countryFrom = 0;
		int countryTo = 0;
		boolean skip = false;
		// Traverse and calculate the weights of all the countries that belong to us.
		for(int id=0;id<GameData.NUM_COUNTRIES;id++) {
			if(own[id]!=-1) {
				System.out.println("id="+id);
				own[id] = calcWeightForOwn(id);// Store weight in the corresponding position.
				if(own[id] == -1) {// Skip command.
					skip = true;
					break;
				}
				
				/* Find around country attribute */
				int[] around = new int[6];
				int[] around_weight = new int[6];
				int max_ar_weight = 0;
				int index = 0;
				for(int i=0; i<GameData.NUM_COUNTRIES; i++) {
					if(board.isAdjacent(id, i)) {
						
						// if adjacent is also our country , just skip.
						if(board.getOccupier(i) != player.getId()) {
//							System.out.println("from: "+id+" to: "+i);
							around[index] = i;
							around_weight[index] = calcWeightForOpp(i);
							// find max weight of opp
							if(around_weight[index] >= max_ar_weight) {
								max_ar_weight = around_weight[index];
								countryTo = around[index];
							}
							index++;
						}
					}
				}
				to[id] = countryTo;
				ans[id][0] = own[id];//weight
				ans[id][1] = id;//from
				ans[id][2] = countryTo;//to
			}
		}
//		System.out.println("decide from: "+countryFrom+" to: "+countryTo);
		if(skip == true) {
			return "skip";
		}
		
		int maxWeight = 0;
		for(int i=0; i<GameData.NUM_COUNTRIES; i++) {
//			System.out.println("find: "+ans[i][0]);
			if(maxWeight <= ans[i][0]) {
				maxWeight = ans[i][0];
				countryFrom = ans[i][1];
				countryTo = ans[i][2];
			}
		}
		//System.out.println("final: "+maxWeight);
		System.out.println("final from: "+countryFrom+" to: "+countryTo);
		
		// Calculate the number of unit to attack
		int numToAttack = calcUnitAttak(countryFrom);
		if(numToAttack == -1) {
			return "skip";
		}
		// Form a command
		command = GameData.COUNTRY_NAMES[countryFrom].replaceAll("\\s", "") + " " + GameData.COUNTRY_NAMES[countryTo].replaceAll("\\s", "") + " " + numToAttack;
		return command;
	}

	public String getDefence (int countryId) {
		String command = "";
		// put your code here
		command = "1";
		return(command);
	}

	public String getMoveIn (int attackCountryId) {
		String command = "";
		int units = board.getNumUnits(attackCountryId);
		boolean surrounded = true;
		int a[] = GameData.ADJACENT[attackCountryId];
		for(int i = 0; i < a.length; i++) {
			if(board.getOccupier(attackCountryId) != board.getOccupier(a[i])) {
				surrounded = false;
				break;
			}
		}
		
		if(!surrounded) {
			int move = units/2;
			command = "" + move;
		}else {
			int move = units - 1;
			command = "" + move;
		}
		
		return(command);
	}

	public String getFortify () {
		String command = "";
		// put code here
		command = "skip";
		return(command);
	}

}
