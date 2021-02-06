package gameboard;

public class Constants {
	
	
		//country coordinates
		private double[][] N_America = { { 75 , 25 } , { 15 , 95 } , { 100 , 120 } , { 55 , 170 } , { 0 , 220 } , 
									  { 130 , 195 } , { 190 , 140 } , { 185 , 60 } , { 280 , 15 } };
		
		private double[][] S_America = { { 55 , 320 } , { 5 , 400 } , { 105 , 410 } , { 60 , 490 } };
		
		private double[][] Europe = { { 230 , 195 } , { 210 , 265 } , { 280 , 245 } , { 260 , 305 } , { 200 , 360 } , 
									  { 340 , 295 } , { 295 , 380 } };
		
		private double[][] Africa = { { 220 , 460 } , { 315 , 440 } , { 200 , 550 } , { 320 , 530 } , { 260, 630 } , { 360 , 610 } };
		
		private double[][] Asia = { { 390 , 400 } , { 420 , 290 } , { 480 , 340 } , { 380 , 220 } , { 475 , 218 } , { 405 , 150 } ,
			    				    { 390 , 80 } , { 315 , 100 } , { 470 , 80 } , { 380 , 30 } , { 480 , 5 } , { 570 , 270 } };
		
		private double[][] Australia = { { 640 , 260 } , { 710 , 200 } , { 720 , 300 } , { 790 , 240 } };
		
		private double[][][] countries = {N_America,Europe, Asia, Australia, S_America, Africa};
		
		//color set of countries
		String[] NA_color = {"yellow","yellow","yellow","yellow","yellow","yellow","yellow","yellow","yellow"};
		String[] SA_color = {"DarkGreen","DarkGreen","DarkGreen","DarkGreen"};
		String[] Europe_color = {"blue","blue","blue","blue","blue","blue","blue"};
		String[] Africa_color = {"orange","orange","orange","orange","orange","orange"};
		String[] Asia_color = {"green","green","green","green","green","green","green","green","green","green","green","green"};
		String[] Australia_color = {"cyan","cyan","cyan","cyan"};
		
		String[][] colorSets = {NA_color,Europe_color,Asia_color,Australia_color,SA_color,Africa_color};
		
		//army numbers should also be here
		
		//getters		
		public double[][][] getCountries(){
			return countries;
		}
		
		public String[][] getColorSets(){
			return colorSets;
		}
		
		//setters
		public void setColorsets(int continentIndex, int countryIndex, String color) {
			colorSets[continentIndex][countryIndex] = color;
		}
		
}
