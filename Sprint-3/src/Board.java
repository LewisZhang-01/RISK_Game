
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
	
	public void combat(UI ui) {
		ui.displayString("Announce the territory you are attacking:");
		String attackedTerritory = ui.inputModifiedTerritory();
		ui.displayString("Announce the territory you are attacking from:");
		String attackingTerritory = ui.inputModifiedTerritory();
		//判断领地:
		//不能自己打自己
		//不能打不相邻
		//不能操作不是自己的领地
		//攻打方军队数大于1.
		
		//判断失败: 错误信息
		//判断成功: 输入攻打的军队数量（不能大于军队数量-1）
		
		//roll点决定攻打是否成功
		//若不成功攻打方损失攻打的军队
		//若成功被攻打方减少攻打方数量的军队
		//若被攻打方军队减少至零或以下
		//攻打方占领该领地，该领地军队数为攻打的军队数
		//combat 结束
		
		
	}
	
	public void fortify(UI ui) {
		//输入把军队从哪个领地移动到哪个领地
		//判断领地:
		//被移动军队的领地军队数大于1
		//不能移动到别人的领地/不能操作不是自己的领地
		//两个领地必须相邻
		
		//判断失败: 错误信息
		//判断成功: 输入移动的军队数量（不能大于军队数量-1），并移动军队
	}
}
