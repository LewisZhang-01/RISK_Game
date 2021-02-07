//Zhonghe Chen
package gameboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;


public class BoardComponent extends JComponent{
	private Graphics2D g2;
	
	//get country coordinates
	Constants c = new Constants();
	
	private double[][][] countries = c.getCountries();
	
	private String[][] colorSets = c.getColorSets();
	
	public BoardComponent() {

		class AddInputListener implements ActionListener {
			int i = 1;
			
			public void actionPerformed(ActionEvent event) {
					if(i==2) {
						// Call to initialize the player's territories
						
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
						
						
						repaint();	//I guess repaint should be placed here, but nothing happens --Zhonghe Chen
					}
					i++;
				}
			//create button here
			
		}
		
		
	}
	
	// Draw the countries
	// 0<=i<=41
	public void setCountry(double[][] countries, int i, String color) {
		Ellipse2D.Double country = new Ellipse2D.Double(countries[i][0], countries[i][1], 25, 25);
		
		switch(color) {
		case "yellow":
			g2.setColor(Color.YELLOW);
			break;
		case "blue":
			g2.setColor(Color.BLUE);
			break;
		case "red":
			g2.setColor(Color.RED);
			break;
		case "cyan":
			g2.setColor(Color.CYAN);
			break;
		case "green":
			g2.setColor(Color.GREEN);
			break;
		case "orange":
			g2.setColor(Color.ORANGE);
			break;
		case "DarkGreen":
			g2.setColor(new Color(0,100,0));
			break;
			
		default:
			g2.setColor(Color.BLACK);
		}
		g2.fill(country);
		g2.setColor(Color.BLACK);
		g2.drawOval((int)countries[i][0], (int)countries[i][1], 25,25);
		repaint(); //I guess repaint should be placed here, but nothing happens. --Zhonghe Chen
	}
	
	// Draw lines
	public void drawLines(Graphics2D g2, int x1, int y1, int x2, int y2) {
		g2.setColor(Color.gray);
		Line2D.Double lines = new Line2D.Double(x1,y1,x2,y2);
		g2.draw(lines);
		g2.setColor(Color.BLACK);
	}	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Recover Graphics2D
		g2 = (Graphics2D)g;
		
		//draw country points
		for(int i = 0; i < countries.length; i++) {
			for(int j = 0; j < countries[i].length; j++) {
				setCountry(countries[i],j,colorSets[i][j]);
			}
		}
		
		//draw lines
		drawLines(g2,80,50,35,95);
		drawLines(g2,90,50,110,120);
		drawLines(g2,40,110,100,130);
		drawLines(g2,80,180,110,145);
		drawLines(g2,55,180,30,120);
		drawLines(g2,25,120,15,220);
		drawLines(g2,58,190,25,230);
		drawLines(g2,80,185,130,205);
		drawLines(g2,115,145,140,195);
		drawLines(g2,125,135,190,150);
		drawLines(g2,155,205,195,162);
		drawLines(g2,125,130,185,75);
		drawLines(g2,200,140,200,85);
		drawLines(g2,100,40,185,70);
		drawLines(g2,210,70,280,30);
		drawLines(g2,100,35,280,25);
		drawLines(g2,15,245,65,320);
		drawLines(g2,25,402,65,345);
		drawLines(g2,30,415,105,422);
		drawLines(g2,75,345,115,410);
		drawLines(g2,20,425,62,495);
		drawLines(g2,115,435,83,495);
		drawLines(g2,237,196,210,162);
		drawLines(g2,240,220,225,265);
		drawLines(g2,253,215,285,245);
		drawLines(g2,235,275,282,260);
		drawLines(g2,260,315,230,285);
		drawLines(g2,290,270,280,310);
		drawLines(g2,222,290,212,360);
		drawLines(g2,222,365,266,328);
		drawLines(g2,340,310,285,318);
		drawLines(g2,345,295,305,262);
		drawLines(g2,295,392,225,375);
		drawLines(g2,302,380,278,330);
		drawLines(g2,317,385,350,320);
		drawLines(g2,220,468,130,425);
		drawLines(g2,230,460,215,385);
		drawLines(g2,242,465,305,405);
		drawLines(g2,315,455,245,470);
		drawLines(g2,324,440,312,405);
		drawLines(g2,215,550,230,485);
		drawLines(g2,225,562,320,544);
		drawLines(g2,240,482,322,535);
		drawLines(g2,332,530,328,465);
		drawLines(g2,262,633,217,575);
		drawLines(g2,278,632,330,555);
		drawLines(g2,285,640,360,624);
		drawLines(g2,370,610,338,555);
		drawLines(g2,390,407,320,395);
		drawLines(g2,390,420,340,448);
		drawLines(g2,398,400,360,318);
		drawLines(g2,420,302,365,307);
		drawLines(g2,432,315,407,400);
		drawLines(g2,483,341,445,308);
		drawLines(g2,483,360,415,405);
		drawLines(g2,358,297,388,244);
		drawLines(g2,430,290,396,244);
		drawLines(g2,475,229,405,233);
		drawLines(g2,488,242,492,340);
		drawLines(g2,479,239,441,292);
		drawLines(g2,412,175,395,220);
		drawLines(g2,426,173,485,218);
		drawLines(g2,405,105,415,150);		
		drawLines(g2,390,95,340,110);
		drawLines(g2,407,155,338,120);
		drawLines(g2,415,90,470,92);
		drawLines(g2,425,153,478,104);
		drawLines(g2,482,105,488,217);
		drawLines(g2,395,55,400,80);
		drawLines(g2,385,53,336,104);
		drawLines(g2,405,48,474,84);
		drawLines(g2,380,40,305,30);
		drawLines(g2,480,17,405,40);
		drawLines(g2,492,30,485,80);
		drawLines(g2,570,280,500,230);
		drawLines(g2,572,290,505,345);
		drawLines(g2,595,282,640,275);
		drawLines(g2,712,220,662,262);
		drawLines(g2,720,306,664,277);
		drawLines(g2,732,300,725,225);
		drawLines(g2,790,250,735,217);
		drawLines(g2,792,260,745,308);
		
		
		
		//label country names
		g2.drawString("Alberta", 65, 25);
		g2.drawString("W United States", 5, 95);
		g2.drawString("Ontario", 90, 120);
		g2.drawString("E United States", 45, 170);
		g2.drawString("Central America", 0, 220);
		g2.drawString("Quebec", 120, 195);
		g2.drawString("Greenland", 180, 140);
		g2.drawString("NW Territory", 170, 60);
		g2.drawString("Alaska", 270, 15);
		g2.drawString("Venezuela", 45, 320);
		g2.drawString("Peru", 5, 400);
		g2.drawString("Brazil", 105, 410);
		g2.drawString("Argentina", 50, 490);
		g2.drawString("Iceland", 220, 195);
		g2.drawString("Great Britain", 200, 265);
		g2.drawString("Scandinavia", 270, 245);
		g2.drawString("N Europe", 250, 305);
		g2.drawString("W Europe", 190, 360);
		g2.drawString("Ukraine", 330, 295);
		g2.drawString("S Europe", 285, 380);
		g2.drawString("N Africa", 210, 460);
		g2.drawString("Egypt", 305, 440);
		g2.drawString("Congo", 190, 550);
		g2.drawString("E Africa", 310, 530);
		g2.drawString("S Africa", 250, 630);
		g2.drawString("Madagascar", 350, 610);
		g2.drawString("Middle East", 380, 400);
		g2.drawString("Afghanistan", 410, 290);
		g2.drawString("India", 480, 340);
		g2.drawString("Ural", 380, 220);
		g2.drawString("China", 475, 218);
		g2.drawString("Siberia", 400, 150);
		g2.drawString("Irkutsk", 380, 80);
		g2.drawString("Yakutsk", 305, 100);
		g2.drawString("Mongolia", 460, 80);
		g2.drawString("Kamchatka", 370, 30);
		g2.drawString("Japan", 510, 15);
		g2.drawString("Siam", 560, 270);
		g2.drawString("Indonesia", 630, 260);
		g2.drawString("New Guinea", 700, 200);
		g2.drawString("W Australia", 710, 300);
		g2.drawString("E Australia", 780, 240);
	}
	
}
