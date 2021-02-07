//Zhonghe Chen, Zhi Zhang
package gameboard;

public class Initializer {
	// Following comments & code provided by Zhi Zhang
	// Allocate 9 territories to each of the two players
			// and 6 territories to each of the 4 neutral players.
			public void territoriesInitial() {
				// [0]<9 [1]<7 [2]<12 [3]<4 [4]<4 [5]<6
				Constants c = new Constants();
				
				// for player #1 (red)
				c.setColorsets(0, 0, "red");
				c.setColorsets(0, 1, "red");
				c.setColorsets(1, 0, "red");
				c.setColorsets(1, 1, "red");
				c.setColorsets(2, 0, "red");
				c.setColorsets(2, 1, "red");
				c.setColorsets(3, 0, "red");
				c.setColorsets(3, 1, "red");
				c.setColorsets(4, 0, "red");
				// for player #2 (blue) 
				c.setColorsets(0, 2, "blue");
				c.setColorsets(0, 3, "blue");
				c.setColorsets(0, 4, "blue");
				c.setColorsets(1, 2, "blue");
				c.setColorsets(1, 3, "blue");
				c.setColorsets(2, 2, "blue");
				c.setColorsets(2, 3, "blue");
				c.setColorsets(3, 2, "blue");
				c.setColorsets(3, 3, "blue");
				
				// for neutral #1 (Grey)
				c.setColorsets(1, 4, "Grey");
				c.setColorsets(1, 5, "Grey");
				c.setColorsets(1, 6, "Grey");
			    c.setColorsets(2, 4, "Grey");
				c.setColorsets(2, 5, "Grey");
				c.setColorsets(2, 6, "Grey");
				
				// for neutral #2 (Moccasin)
				c.setColorsets(2, 7, "Moccasin");
				c.setColorsets(2, 8, "Moccasin");
				c.setColorsets(2, 9, "Moccasin");
				c.setColorsets(2, 10, "Moccasin");
				c.setColorsets(2, 11, "Moccasin");
				c.setColorsets(4, 1, "Moccasin");
				
				// for neutral #3 (Bisque)
				c.setColorsets(4, 2, "Bisque");
				c.setColorsets(4, 3, "Bisque");
				c.setColorsets(5, 0, "Bisque");
				c.setColorsets(5, 1, "Bisque");
				c.setColorsets(5, 2, "Bisque");
				c.setColorsets(5, 3, "Bisque");
			
				// for neutral #3 (DarkGrey)
				c.setColorsets(5, 4, "DarkGrey");
				c.setColorsets(5, 5, "DarkGrey");
				c.setColorsets(0, 5, "DarkGrey");
				c.setColorsets(0, 6, "DarkGrey");
				c.setColorsets(0, 7, "DarkGrey");
				c.setColorsets(0, 8, "DarkGrey");
				
			}
}
