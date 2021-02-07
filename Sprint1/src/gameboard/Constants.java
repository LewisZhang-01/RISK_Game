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
		String[] Europe_color = {"Pink","Pink","Pink","Pink","Pink","Pink","Pink"};
		String[] Africa_color = {"orange","orange","orange","orange","orange","orange"};
		String[] Asia_color = {"green","green","green","green","green","green","green","green","green","green","green","green"};
		String[] Australia_color = {"cyan","cyan","cyan","cyan"};
		
		String[][] colorSets = {NA_color,Europe_color,Asia_color,Australia_color,SA_color,Africa_color};
		
		//army numbers should also be here
		
		//getters		
		public double[][][] getCountries(){
			return countries;
		}
		
		//boolean flag = false;
		public String[][] getColorSets(){
			//System.out.println("in get : "+flag);
			//if(flag==true) {
				
				//setColorsets(0,0,"red");
				//return colorSets;
			//}else {
				
				return colorSets;
			//}
			
		}
		
		//setters
		public void setColorsets(int continentIndex, int countryIndex, String color) {
			
			//BoardComponent b = new BoardComponent();
			
			//flag=true;
			
			System.out.println("Before change color: "+colorSets[continentIndex][countryIndex]);
			
			colorSets[continentIndex][countryIndex] = color;
			
			//b.update();
			//b.repaint();// we try to use repaint.
			//b.paintComponent(b.g);// and even through call painComponent instead of repaint, but still not working.
			
			System.out.println("After change color: "+colorSets[continentIndex][countryIndex]);
			
			
			//b.repaint();
		}
		
		// Allocate 9 territories to each of the two players
		// and 6 territories to each of the 4 neutral players.
		public void territoriesInitial() {
			// [0]<9 [1]<7 [2]<12 [3]<4 [4]<4 [5]<6
			
			// for player #1 (red)
			setColorsets(0, 0, "red");
			setColorsets(0, 1, "red");
			setColorsets(1, 0, "red");
			setColorsets(1, 1, "red");
			setColorsets(2, 0, "red");
			setColorsets(2, 1, "red");
			setColorsets(3, 0, "red");
			setColorsets(3, 1, "red");
			setColorsets(4, 0, "red");
			// for player #2 (blue) 
			setColorsets(0, 2, "blue");
			setColorsets(0, 3, "blue");
			setColorsets(0, 4, "blue");
			setColorsets(1, 2, "blue");
			setColorsets(1, 3, "blue");
			setColorsets(2, 2, "blue");
			setColorsets(2, 3, "blue");
			setColorsets(3, 2, "blue");
			setColorsets(3, 3, "blue");
			
			// for neutral #1 (Grey)
			setColorsets(1, 4, "Grey");
			setColorsets(1, 5, "Grey");
			setColorsets(1, 6, "Grey");
			setColorsets(2, 4, "Grey");
			setColorsets(2, 5, "Grey");
			setColorsets(2, 6, "Grey");
			
			// for neutral #2 (Moccasin)
			setColorsets(2, 7, "Moccasin");
			setColorsets(2, 8, "Moccasin");
			setColorsets(2, 9, "Moccasin");
			setColorsets(2, 10, "Moccasin");
			setColorsets(2, 11, "Moccasin");
			setColorsets(4, 1, "Moccasin");
			
			// for neutral #3 (Bisque)
			setColorsets(4, 2, "Bisque");
			setColorsets(4, 3, "Bisque");
			setColorsets(5, 0, "Bisque");
			setColorsets(5, 1, "Bisque");
			setColorsets(5, 2, "Bisque");
			setColorsets(5, 3, "Bisque");
		
			// for neutral #3 (DarkGrey)
			setColorsets(5, 4, "DarkGrey");
			setColorsets(5, 5, "DarkGrey");
			setColorsets(0, 5, "DarkGrey");
			setColorsets(0, 6, "DarkGrey");
			setColorsets(0, 7, "DarkGrey");
			setColorsets(0, 8, "DarkGrey");
			
		}
}
