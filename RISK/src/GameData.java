/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 */

public final class GameData {

	public static final int NUM_PLAYERS = 2;
	public static final int NUM_NEUTRALS = 4;
	public static final int NUM_PLAYERS_PLUS_NEUTRALS = NUM_PLAYERS + NUM_NEUTRALS;
	public static final int NUM_COUNTRIES = 42;
	public static final int INIT_COUNTRIES_PLAYER = 9;
	public static final int INIT_COUNTRIES_NEUTRAL = 6;
	public static final int NUM_REINFORCE_ROUNDS = 9;
	public static final int ATTACK_MIN_IN_COUNTRY = 2;
	public static final int ATTACK_MAX = 3;
	public static final int DEFEND_MAX = 2;
	public static final String[] COUNTRY_NAMES = {
		"Ontario","Quebec","NW Territory","Alberta","Greenland","E United States","W United States","Central America","Alaska",
		"Great Britain","W Europe","S Europe","Ukraine","N Europe","Iceland","Scandinavia",
		"Afghanistan","India","Middle East","Japan","Ural","Yakutsk","Kamchatka","Siam","Irkutsk","Siberia","Mongolia","China",
		"E Australia","New Guinea","W Australia","Indonesia",
		"Venezuela","Peru","Brazil","Argentina",
		"Congo","N Africa","S Africa","Egypt","E Africa","Madagascar"};  // for reference
	public static final int[][] ADJACENT = { 
		{4,1,5,6,3,2},    // 0
		{4,5,0},
		{4,0,3,8},
		{2,0,6,8},
		{14,1,0,2},
		{0,1,7,6}, 
		{3,0,5,7},
		{6,5,32},
		{2,3,22},
		{14,15,13,10},    
		{9,13,11,37},     // 10
		{13,12,18,39,10},
		{20,16,18,11,13,15},
		{15,12,11,10,9},
		{15,9,4},
		{9,12,13,14},
		{20,27,17,18,12}, 
		{16,27,23,18},
		{12,16,17,40,39,11},
		{26,22},
		{25,27,16,12},    // 20
		{22,24,25},        
		{8,19,26,24,21},
		{27,31,17},
		{21,22,26,25},
		{21,24,26,27,20},
		{24,22,19,27,25},
		{26,23,17,16,20,25},
		{29,30},          
		{28,30,31},
		{29,28,31},      // 30
		{23,29,30},
		{7,34,33},       
		{32,34,35},
		{32,37,35,33},
		{33,34},
		{37,40,38},      
		{10,11,39,40,36,34},
		{36,40,41},
		{11,18,40,37},
		{39,18,41,38,36,37},  //40
		{38,40}
	};
	public static final int NUM_CONTINENTS = 6;
	public static final String[] CONTINENT_NAMES = {"North America","Europe","Asia","Australia","South America","Africa"};  // for reference 
	public static final int[] CONTINENT_IDS = {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,5,5};
	public static final int[][] CONTINENT_COUNTRIES = {
		{0,1,2,3,4,5,6,7,8},
		{9,10,11,12,13,14,15},
		{16,17,18,19,20,21,22,23,24,25,26,26,27},
		{28,29,30,31},
		{32,33,34,35},
		{36,37,38,39,40,41}
	};
	public static final int[] CONTINENT_VALUES = {5,5,7,2,2,3};
	
    private GameData() {
	    //this prevents even the native class from calling this constructor
	    throw new AssertionError();
    }
    

	//Type of each card
    public static final Card.type[] cardType = {Card.type.Cavalry,Card.type.Artillery,Card.type.Artillery,
    											Card.type.Infantry,Card.type.Cavalry,Card.type.Artillery,
    											Card.type.Infantry,Card.type.Cavalry,Card.type.Infantry,
    											Card.type.Cavalry,Card.type.Infantry,Card.type.Cavalry,
    											Card.type.Artillery,Card.type.Cavalry,Card.type.Infantry,
    											Card.type.Artillery,Card.type.Infantry,Card.type.Infantry,
    											Card.type.Artillery,Card.type.Infantry,Card.type.Cavalry,
    											Card.type.Cavalry,Card.type.Cavalry,Card.type.Artillery,
    											Card.type.Infantry,Card.type.Artillery,Card.type.Artillery,
    											Card.type.Cavalry,Card.type.Infantry,Card.type.Cavalry,
    											Card.type.Artillery,Card.type.Cavalry,Card.type.Artillery,
    											Card.type.Cavalry,Card.type.Artillery,Card.type.Infantry,
    											Card.type.Cavalry,Card.type.Infantry,Card.type.Artillery,
    											Card.type.Infantry,Card.type.Artillery,Card.type.Infantry,
    											Card.type.Wild,Card.type.Wild};
	
    //the path of the graphs of cards
    public static final String[] path = {"images/cards/1.jpg","images/cards/2.jpg","images/cards/3.jpg","images/cards/4.jpg","images/cards/5.jpg","images/cards/6.jpg","images/cards/7.jpg","images/cards/8.jpg","images/cards/9.jpg",
  			 "images/cards/10.jpg","images/cards/11.jpg","images/cards/12.jpg","images/cards/13.jpg","images/cards/14.jpg","images/cards/15.jpg","images/cards/16.jpg","images/cards/17.jpg","images/cards/18.jpg",
  			 "images/cards/19.jpg","images/cards/20.jpg","images/cards/21.jpg","images/cards/22.jpg","images/cards/23.jpg","images/cards/24.jpg","images/cards/25.jpg","images/cards/26.jpg","images/cards/27.jpg",
  			 "images/cards/28.jpg","images/cards/29.jpg","images/cards/30.jpg","images/cards/31.jpg","images/cards/32.jpg","images/cards/33.jpg","images/cards/34.jpg","images/cards/35.jpg","images/cards/36.jpg",
  			 "images/cards/37.jpg","images/cards/38.jpg","images/cards/39.jpg","images/cards/40.jpg","images/cards/41.jpg","images/cards/42.jpg","images/cards/43.jpg","images/cards/44.jpg"};
    
    public static final String MapPanelImage = "images/MapPanelImage.jpg";
    public static final String InfoPanelImage = "images/InfoPanelImage.jpg";
    public static final String CommandPanelImage = "images/CommandPanelImage.jpg";
    public static final String CardSetsImage = "images/CardSetsImage.jpg";
    public static final String CardImage = "images/CardImage.png";
}
