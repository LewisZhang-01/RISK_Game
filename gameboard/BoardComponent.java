package gameboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;

import java.util.ArrayList;

public class BoardComponent extends JComponent{
	
//	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		// Recover Graphics2D
	    Graphics2D g2 = (Graphics2D)g;
		
	    
	    //ArrayList for countries drawn by Ellipse2D.Double
		ArrayList<Ellipse2D.Double> countries = new ArrayList<Ellipse2D.Double>();
		
		//draw countries and lines
		g2.drawString("Alberta", 65, 25);
		countries.add(new Ellipse2D.Double(75,25,25,25));
		setCountry(g2, countries, 0, "yellow");
		
		g2.drawString("W United States", 5, 95);
		countries.add(new Ellipse2D.Double(15,95,25,25));
		setCountry(g2, countries, 1, "yellow");
		
		drawLines(g2,80,50,35,95);
		
		g2.drawString("Ontario", 90, 120);
		countries.add(new Ellipse2D.Double(100,120,25,25));
		setCountry(g2, countries, 2, "yellow");
		
		drawLines(g2,90,50,110,120);
		drawLines(g2,40,110,100,130);
		
		g2.drawString("E United States", 45, 170);
		countries.add(new Ellipse2D.Double(55,170,25,25));
		setCountry(g2, countries, 3, "yellow");
		
		drawLines(g2,80,180,110,145);
		drawLines(g2,55,180,30,120);
		
		g2.drawString("Central America", 0, 220);
		countries.add(new Ellipse2D.Double(0,220,25,25));
		setCountry(g2, countries, 4, "yellow");
		
		drawLines(g2,25,120,15,220);
		drawLines(g2,58,190,25,230);
		
		g2.drawString("Quebec", 120, 195);
		countries.add(new Ellipse2D.Double(130,195,25,25));
		setCountry(g2, countries, 5, "yellow");
		
		drawLines(g2,80,185,130,205);
		drawLines(g2,115,145,140,195);
		
		g2.drawString("Greenland", 180, 140);
		countries.add(new Ellipse2D.Double(190,140,25,25));
		setCountry(g2, countries, 6, "yellow");
		
		drawLines(g2,125,135,190,150);
		drawLines(g2,155,205,195,162);
		
		
		g2.drawString("NW Territory", 170, 60);
		countries.add(new Ellipse2D.Double(185,60,25,25));
		setCountry(g2, countries, 7, "yellow");
		
		drawLines(g2,125,130,185,75);
		drawLines(g2,200,140,200,85);
		drawLines(g2,100,40,185,70);
		
		
		g2.drawString("Alaska", 270, 15);
		countries.add(new Ellipse2D.Double(280,15,25,25));
		setCountry(g2, countries, 8, "yellow");
		
		drawLines(g2,210,70,280,30);
		drawLines(g2,100,35,280,25);
		
		
		g2.drawString("Venezuela", 45, 320);
		countries.add(new Ellipse2D.Double(55,320,25,25));
		setCountry(g2, countries, 9, "red");
		
		drawLines(g2,15,245,65,320);
		
		
		g2.drawString("Peru", 5, 400);
		countries.add(new Ellipse2D.Double(5,400,25,25));
		setCountry(g2, countries, 10, "red");
		
		drawLines(g2,25,402,65,345);
		
		
		g2.drawString("Brazil", 105, 410);
		countries.add(new Ellipse2D.Double(105,410,25,25));
		setCountry(g2, countries, 11, "red");
		
		drawLines(g2,30,415,105,422);
		drawLines(g2,75,345,115,410);
		
		
		g2.drawString("Argentina", 50, 490);
		countries.add(new Ellipse2D.Double(60,490,25,25));
		setCountry(g2, countries, 12, "red");
		
		drawLines(g2,20,425,62,495);
		drawLines(g2,115,435,83,495);
		
		
		g2.drawString("Iceland", 220, 195);
		countries.add(new Ellipse2D.Double(230,195,25,25));
		setCountry(g2, countries, 13, "blue");
		
		drawLines(g2,237,196,210,162);
		
		g2.drawString("Great Britain", 200, 265);
		countries.add(new Ellipse2D.Double(210,265,25,25));
		setCountry(g2, countries, 14, "blue");
		
		drawLines(g2,240,220,225,265);
		
		g2.drawString("Scandinavia", 270, 245);
		countries.add(new Ellipse2D.Double(280,245,25,25));
		setCountry(g2, countries, 15, "blue");
		
		drawLines(g2,253,215,285,245);
		drawLines(g2,235,275,282,260);
		
		g2.drawString("N Europe", 250, 305);
		countries.add(new Ellipse2D.Double(260,305,25,25));
		setCountry(g2, countries, 16, "blue");

		drawLines(g2,260,315,230,285);
		drawLines(g2,290,270,280,310);
		
		
		g2.drawString("W Europe", 190, 360);
		countries.add(new Ellipse2D.Double(200,360,25,25));
		setCountry(g2, countries, 17, "blue");
		
		drawLines(g2,222,290,212,360);
		drawLines(g2,222,365,266,328);
		
		g2.drawString("Ukraine", 330, 295);
		countries.add(new Ellipse2D.Double(340,295,25,25));
		setCountry(g2, countries, 18, "blue");
		
		drawLines(g2,340,310,285,318);
		drawLines(g2,345,295,305,262);
		
		g2.drawString("S Europe", 285, 380);
		countries.add(new Ellipse2D.Double(295,380,25,25));
		setCountry(g2, countries, 19, "blue");
		
		drawLines(g2,295,392,225,375);
		drawLines(g2,302,380,278,330);
		drawLines(g2,317,385,350,320);
		
		g2.drawString("N Africa", 210, 460);
		countries.add(new Ellipse2D.Double(220,460,25,25));
		setCountry(g2, countries, 20, "orange");
		
		drawLines(g2,220,468,130,425);
		drawLines(g2,230,460,215,385);
		drawLines(g2,242,465,305,405);
		
		
		g2.drawString("Egypt", 305, 440);
		countries.add(new Ellipse2D.Double(315,440,25,25));
		setCountry(g2, countries, 21, "orange");
		
		drawLines(g2,315,455,245,470);
		drawLines(g2,324,440,312,405);
		
		g2.drawString("Congo", 190, 550);
		countries.add(new Ellipse2D.Double(200,550,25,25));
		setCountry(g2, countries, 22, "orange");
		
		drawLines(g2,215,550,230,485);
		
		g2.drawString("E Africa", 310, 530);
		countries.add(new Ellipse2D.Double(320,530,25,25));
		setCountry(g2, countries, 23, "orange");
		
		drawLines(g2,225,562,320,544);
		drawLines(g2,240,482,322,535);
		drawLines(g2,332,530,328,465);
		
		
		g2.drawString("S Africa", 250, 630);
		countries.add(new Ellipse2D.Double(260,630,25,25));
		setCountry(g2, countries, 24, "orange");
		
		drawLines(g2,262,633,217,575);
		drawLines(g2,278,632,330,555);
		
		g2.drawString("Madagascar", 350, 610);
		countries.add(new Ellipse2D.Double(360,610,25,25));
		setCountry(g2, countries, 25, "orange");
		
		drawLines(g2,285,640,360,624);
		drawLines(g2,370,610,338,555);
		
		g2.drawString("Middle East", 380, 400);
		countries.add(new Ellipse2D.Double(390,400,25,25));
		setCountry(g2, countries, 26, "green");
		
		drawLines(g2,390,407,320,395);
		drawLines(g2,390,420,340,448);
		drawLines(g2,398,400,360,318);
		
		g2.drawString("Afghanistan", 410, 290);
		countries.add(new Ellipse2D.Double(420,290,25,25));
		setCountry(g2, countries, 27, "green");
		
		drawLines(g2,420,302,365,307);
		drawLines(g2,432,315,407,400);
		
		g2.drawString("India", 480, 340);
		countries.add(new Ellipse2D.Double(480,340,25,25));
		setCountry(g2, countries, 28, "green");
		
		drawLines(g2,483,341,445,308);
		drawLines(g2,483,360,415,405);
		
		
		g2.drawString("Ural", 380, 220);
		countries.add(new Ellipse2D.Double(380,220,25,25));
		setCountry(g2, countries, 29, "green");
		
		drawLines(g2,358,297,388,244);
		drawLines(g2,430,290,396,244);
		
		
		g2.drawString("China", 475, 218);
		countries.add(new Ellipse2D.Double(475,218,25,25));
		setCountry(g2, countries, 30, "green");
		
		drawLines(g2,475,229,405,233);
		drawLines(g2,488,242,492,340);
		drawLines(g2,479,239,441,292);
		
		
		g2.drawString("Siberia", 400, 150);
		countries.add(new Ellipse2D.Double(405,150,25,25));
		setCountry(g2, countries, 31, "green");
		
		drawLines(g2,412,175,395,220);
		drawLines(g2,426,173,485,218);
		
		
		g2.drawString("Irkutsk", 380, 80);
		countries.add(new Ellipse2D.Double(390,80,25,25));
		setCountry(g2, countries, 32, "green");
		
		drawLines(g2,405,105,415,150);
		
		g2.drawString("Yakutsk", 305, 100);
		countries.add(new Ellipse2D.Double(315,100,25,25));
		setCountry(g2, countries, 33, "green");
		
		drawLines(g2,390,95,340,110);
		drawLines(g2,407,155,338,120);
		
		g2.drawString("Mongolia", 460, 80);
		countries.add(new Ellipse2D.Double(470,80,25,25));
		setCountry(g2, countries, 34, "green");
		
		drawLines(g2,415,90,470,92);
		drawLines(g2,425,153,478,104);
		drawLines(g2,482,105,488,217);
		
		g2.drawString("Kamchatka", 370, 30);
		countries.add(new Ellipse2D.Double(380,30,25,25));
		setCountry(g2, countries, 35, "green");
		
		drawLines(g2,395,55,400,80);
		drawLines(g2,385,53,336,104);
		drawLines(g2,405,48,474,84);
		drawLines(g2,380,40,305,30);
		
		g2.drawString("Japan", 510, 15);
		countries.add(new Ellipse2D.Double(480,5,25,25));
		setCountry(g2, countries, 36, "green");
		
		drawLines(g2,480,17,405,40);
		drawLines(g2,492,30,485,80);
		
		
		g2.drawString("Siam", 560, 270);
		countries.add(new Ellipse2D.Double(570,270,25,25));
		setCountry(g2, countries, 37, "green");
		
		drawLines(g2,570,280,500,230);
		drawLines(g2,572,290,505,345);
		
		g2.drawString("Indonesia", 630, 260);
		countries.add(new Ellipse2D.Double(640,260,25,25));
		setCountry(g2, countries, 38, "cyan");
		
		drawLines(g2,595,282,640,275);
		
		g2.drawString("New Guinea", 700, 200);
		countries.add(new Ellipse2D.Double(710,200,25,25));
		setCountry(g2, countries, 39, "cyan");
		
		drawLines(g2,712,220,662,262);
		
		g2.drawString("W Australia", 710, 300);
		countries.add(new Ellipse2D.Double(720,300,25,25));
		setCountry(g2, countries, 40, "cyan");
		
		drawLines(g2,720,306,664,277);
		drawLines(g2,732,300,725,225);
		
		g2.drawString("E Australia", 780, 240);
		countries.add(new Ellipse2D.Double(790,240,25,25));
		setCountry(g2, countries, 41, "cyan");
		
		drawLines(g2,790,250,735,217);
		drawLines(g2,792,260,745,308);
	}
	
	// Draw the countries
	private void setCountry(Graphics2D g2, ArrayList<Ellipse2D.Double> countries, int i, String color) {
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
		default:
			g2.setColor(Color.BLACK);
		}
		g2.fill(countries.get(i));
		g2.setColor(Color.BLACK);
		g2.drawOval((int)countries.get(i).x, (int)countries.get(i).y, 25,25);
	}

	
	// Draw lines
	private void drawLines(Graphics2D g2, int x1, int y1, int x2, int y2) {
		g2.setColor(Color.LIGHT_GRAY);
		Line2D.Double lines = new Line2D.Double(x1,y1,x2,y2);
		g2.draw(lines);
		g2.setColor(Color.BLACK);
	}
/*	
	// initialize the plyaer, 
	//Allocate 9 territories to each of the two players 
	//and 6 territories to each of the 4 neutral players. 
	//The allocation does not need to be random. Put one army on each territory.
	public static void playerTerritories_Initialization() {
		// P1 - RED
		Graphics2D g2 = null;
//		setRed(g2,75,25);
		
	}
	*/
}
