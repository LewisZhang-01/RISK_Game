
public class Sprint2 {

	public static void main (String args[]) {	   
		Board board = new Board();
		UI ui = new UI(board);
		int playerId, countryId;
		String name;
		String territory;
		int unit_num = 0;
		
		// display blank board
		ui.displayMap();
		
		// get player names
		for (playerId=0; playerId<GameData.NUM_PLAYERS; playerId++) {
			ui.displayString("Enter the name of player " + (playerId+1));
			name = ui.getCommand();
			ui.displayString("> " + name);
		}
	
		// add units
		countryId = 0;
		for (playerId=0; playerId<GameData.NUM_PLAYERS; playerId++) {
			for (int i=0; i<GameData.INIT_COUNTRIES_PLAYER; i++) {
				board.addUnits(countryId, playerId, 1);
				countryId++;
			}
		}
		for (; playerId<GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
			for (int i=0; i<GameData.INIT_COUNTRIES_NEUTRAL; i++) {
				board.addUnits(countryId, playerId, 1);
				countryId++;
			}
		}		
		
		// display map
		ui.displayMap();
		
		/*
		Players take it in turns to place 3 units at a time on a territory that they control and
		then one unit for each neutral. The user types in the name of the territories. 
		The user should be allowed to enter a shortened version of the name, 
		so long as it is unambiguous. After each selection, update the map.
		*/

		//For normal players.
		for (playerId=0; playerId<GameData.NUM_PLAYERS; playerId++) {
			
			int times=3;//Players take it in turns to place 3 units at a time on a territory that they control.
			while(times!=0) {
				
				ui.displayString("Enter the name of territory which you want place unit. (player " + (playerId+1)+")");
				territory = ui.getCommand();
				
				//invalid input handle: Letters only allowed.
				while(!territory.matches("[a-zA-Z]+")) {//check if user not enter letters.
					ui.displayString("*You can only enter letters.\n*Please enter again: ");
					territory = ui.getCommand();
				}
				
				ui.displayString("> " + territory + "\nHow many units you want place.");
				
				//invalid input handle: Integer only allowed.
				boolean done = false;
				while (!done) {
				    try {
				        unit_num = Integer.parseInt(ui.getCommand());
				        done = true;
				    } catch (Exception e) {
				    	ui.displayString("*You can only enter numbers.\n*Please enter again: ");
				    }
				}
				//Lock variables in a range.
				while(unit_num <= 0 || unit_num > times) {
					ui.displayString("*You can only place 1(min) - "+times+"(max) units at this time.\n*Please enter again: ");
					unit_num = Integer.parseInt(ui.getCommand());
				}
	
				if(unit_num == 2) {
					times--;
				}else if(unit_num == 3) {
					times = times-2;
				}
				
				Boolean bool = board.placeUnits(territory, playerId, unit_num);//place n unit to this country.
				if(bool != true) {//Check if territory name is matched.
					ui.displayString("*No territory matched!");
					times=4;//back to start.
				}
				ui.displayMap();//refresh map.	
				times--;
			}
			
		}
		
		//For neutral players.
		for (playerId=0; playerId<GameData.NUM_NEUTRALS; playerId++) {
			int times = 1;
			while(times!=0) {
				ui.displayString("Enter the name of territory which you want place unit. (neutral player " + (playerId+1)+")");
				territory = ui.getCommand();
				//invalid input handle: Letters only allowed.
				while(!territory.matches("[a-zA-Z]+")) {//check if user not enter letters.
					ui.displayString("*You can only enter letters.\n*Please enter again: ");
					territory = ui.getCommand();
				}
				ui.displayString("> " + territory);
				unit_num = 1;
				Boolean bool = board.placeUnits(territory, playerId, unit_num);//place n unit to this country.
				if(bool != true) {//Check if territory name is matched.
					ui.displayString("No territory matched!");
					times=1;//back to start.
				}else {
					times--;
				}
				ui.displayMap();//refresh map.	
			}
		}
		
		// display map
		ui.displayMap();
		
		return;
	}
}

