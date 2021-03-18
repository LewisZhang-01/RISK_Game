import java.util.ArrayList;

public class Board {
	
	private boolean[] occupied = new boolean [GameData.NUM_COUNTRIES];
	private int[] occupier = new int [GameData.NUM_COUNTRIES];
	private int[] numUnits = new int [GameData.NUM_COUNTRIES];
	
	Board() {
		for (int i=0; i<GameData.NUM_COUNTRIES; i++) {
			occupied[i] = false ;
			numUnits[i] = 0;
		}
		return;
	}
	
	public void addUnits (int countryId, int player, int addNumUnits) {	
		// prerequisite: country must be unoccupied or already occupied by this player
		if (!occupied[countryId]) {
			occupied[countryId] = true;
			occupier[countryId] = player;
		}
		numUnits[countryId] = numUnits[countryId] + addNumUnits;
		return;
	}
	
	public void addUnits (Card card, Player player, int addNumUnits) {
		addUnits(card.getCountryId(), player.getId(), addNumUnits);
		return;
	}
	
	public void addUnits (int countryId, Player player, int addNumUnits) {
		addUnits(countryId, player.getId(), addNumUnits);
		return;
	}	
	
	public boolean checkOccupier (Player player, int countryId) {
		return (occupier[countryId] == player.getId());
	}
	
	public boolean isOccupied(int country) {
		return occupied[country];
	}
	
	public int getOccupier (int country) {
		return occupier[country];
	}
	
	public int getNumUnits (int country) {
		return numUnits[country];
	}
	
	public int getNumOfCountry(int player) {
		int num=0;
		for(int id=0;id<GameData.NUM_COUNTRIES;id++) {
			if(occupier[id]==player) {
				num++;
			}
		}
		return num;
	}
	
	
	// 9 7 12 4 4 6
	public int getOccupieContinent (int player) {
		int num_0=0,num_1=0,num_2=0,num_3=0,num_4=0,num_5=0;
		for(int id=0;id<GameData.NUM_COUNTRIES;id++) {
			if(occupier[id]==player) {
				
				if(GameData.CONTINENTS[id]==0) {
					if(occupier[id]==player) {
						num_0++;
					}
				}
				if(GameData.CONTINENTS[id]==1) {
					if(occupier[id]==player) {
						num_1++;
					}
				}
				if(GameData.CONTINENTS[id]==2) {
					if(occupier[id]==player) {
						num_2++;
					}
				}
				if(GameData.CONTINENTS[id]==3) {
					if(occupier[id]==player) {
						num_3++;
					}
				}
				if(GameData.CONTINENTS[id]==4) {
					if(occupier[id]==player) {
						num_4++;
					}
				}
				if(GameData.CONTINENTS[id]==5) {
					if(occupier[id]==player) {
						num_5++;
					}
				}
			}
		}
		System.out.println(num_0+" for "+GameData.CONTINENT_NAMES[0]+"\n"
						+num_1+" for "+GameData.CONTINENT_NAMES[1]+"\n"
						+num_2+" for "+GameData.CONTINENT_NAMES[2]+"\n"
						+num_3+" for "+GameData.CONTINENT_NAMES[3]+"\n"
						+num_4+" for "+GameData.CONTINENT_NAMES[4]+"\n"
						+num_5+" for "+GameData.CONTINENT_NAMES[5]+"\n");
		if(num_0==8||num_1==6||num_2==11||num_3==3||num_4==3||num_5==5)  {
			//5 5 7 2 2 3
			if(num_0 == 8) {/*ui.displayString("Continent "+GameData.CONTINENT_NAMES[0]+"is fully occupied by player "+player);*/ return 5;}
			if(num_1 == 6) {/*ui.displayString("Continent "+GameData.CONTINENT_NAMES[1]+"is fully occupied by player "+player);*/ return 5;}
			if(num_2 == 11) {/*ui.displayString("Continent "+GameData.CONTINENT_NAMES[2]+"is fully occupied by player "+player);*/ return 7;}
			if(num_3 == 3) {/*ui.displayString("Continent "+GameData.CONTINENT_NAMES[3]+"is fully occupied by player "+player);*/ return 2;}
			if(num_4 == 3) {/*ui.displayString("Continent "+GameData.CONTINENT_NAMES[4]+"is fully occupied by player "+player);*/ return 2;}
			if(num_5 == 5) {/*ui.displayString("Continent "+GameData.CONTINENT_NAMES[5]+"is fully occupied by player "+player);*/ return 3;}
		}
		return -1;
	}
	
	public void combat(UI ui, Player player) {
		int[] attack = ui.attackAction(player);
		
		int attackedTerritory = attack[0];	//defending territory must have at least 1 army
		int attackingTerritory = attack[1];	//the attackingTerritory must be greater than 1.
		int armyNum,limit;
		
		if(getNumUnits(attackingTerritory) <= 3) {
			limit = getNumUnits(attackingTerritory) - 1;
		}else {
			limit = 3;
		}
		
		ui.displayString("Enter the number of armies you want to use for this attack:");
		armyNum = ui.inputArmyNum(limit);
		
		//roll dice!
		
		player.rollDice(armyNum);
		ArrayList<Integer> p1Dice = player.getDice();
		
		if(getNumUnits(attackedTerritory) == 1) {
			player.rollDice(1);
		}else {
			player.rollDice(2);
		}
		
		ArrayList<Integer> p2Dice = player.getDice();
		
		int p1Max = 0, p1SMax = 0, p2Max = 0, p2SMax = 0;
		
		for(int i = 0; i < p1Dice.size(); i++) {
			if(p1Max < p1Dice.get(i)) {
				p1SMax = p1Max;
				p1Max = p1Dice.get(i);
			}
		}
		
		for(int i = 0; i < p2Dice.size(); i++) {
			if(p2Max < p2Dice.get(i)) {
				p2SMax = p2Max;
				p2Max = p2Dice.get(i);
			}
		}
		
		if(p1Max <= p2Max) {
			numUnits[attackingTerritory] -= 1;
		}else {
			numUnits[attackedTerritory] -= 1;
		}
		
		
		if(p1Dice.size() > 1 && p2Dice.size() > 1) {
			if(p1SMax <= p2SMax) {
				numUnits[attackingTerritory] -= 1;
			}else {
				numUnits[attackedTerritory] -= 1;
			}
		}
		
		if(numUnits[attackedTerritory] == 0) {
			int movedArmyNum = ui.inputMovedArmyNumber(p1Dice.size(), getNumUnits(attackingTerritory));
			numUnits[attackingTerritory] -= movedArmyNum;
			occupier[attackedTerritory] = player.getId();
			numUnits[attackedTerritory] = movedArmyNum;
		}
		
	}
	
	public void fortify(UI ui, Player player) {
		//输入把军队从哪个领地移动到哪个领地
		//判断领地:
		//被移动军队的领地军队数大于1
		//不能移动到别人的领地/不能操作不是自己的领地
		//两个领地必须相邻
		
		//判断失败: 错误信息
		//判断成功: 输入移动的军队数量（不能大于军队数量-1），并移动军队
	}
}
