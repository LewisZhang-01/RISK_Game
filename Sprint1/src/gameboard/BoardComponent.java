package gameboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.Line2D;
import javax.swing.JComponent;

public class BoardComponent extends JComponent{
	
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		// Recover Graphics2D
	    Graphics2D g2 = (Graphics2D)g;
		
		
		//draw countries and lines
	    
		setYellow(g2,75,25);
		setYellow(g2,15,95);
		
		drawLines(g2,80,50,35,95);
		
		setYellow(g2,100,120);
		
		drawLines(g2,90,50,110,120);
		drawLines(g2,40,110,100,130);
		
		setYellow(g2,55,170);
		
		drawLines(g2,80,180,110,145);
		drawLines(g2,55,180,30,120);
		
		setYellow(g2,0,220);
		
		drawLines(g2,25,120,15,220);
		drawLines(g2,58,190,25,230);
		
		setYellow(g2,130,195);
		
		drawLines(g2,80,185,130,205);
		drawLines(g2,115,145,140,195);
		
		setYellow(g2,190,140);
		
		drawLines(g2,125,135,190,150);
		drawLines(g2,155,205,195,162);
		
		setYellow(g2,185,60);
		
		drawLines(g2,125,130,185,75);
		drawLines(g2,200,140,200,85);
		drawLines(g2,100,40,185,70);
		
		setYellow(g2,280,15);
		
		drawLines(g2,210,70,280,30);
		drawLines(g2,100,35,280,25);
		
		setRed(g2,55,320);
		
		drawLines(g2,15,245,65,320);
		
		setRed(g2,5,400);
		
		drawLines(g2,25,402,65,345);
		
		setRed(g2,105,410);
		
		drawLines(g2,30,415,105,422);
		drawLines(g2,75,345,115,410);
		
		setRed(g2,60,490);
		
		drawLines(g2,20,425,62,495);
		drawLines(g2,115,435,83,495);
		
		setBlue(g2,230,195);
		
		drawLines(g2,237,196,210,162);
		
		setBlue(g2,210,265);
		
		drawLines(g2,240,220,225,265);
		
		setBlue(g2,280,245);
		
		drawLines(g2,253,215,285,245);
		drawLines(g2,235,275,282,260);
		
		setBlue(g2,260,305);
		
		drawLines(g2,260,315,230,285);
		drawLines(g2,290,270,280,310);
		
		setBlue(g2,200,360);
		
		drawLines(g2,222,290,212,360);
		drawLines(g2,222,365,266,328);
		
		setBlue(g2,340,295);
		
		drawLines(g2,340,310,285,318);
		drawLines(g2,345,295,305,262);
		
		setBlue(g2,295,380);
		
		drawLines(g2,295,392,225,375);
		drawLines(g2,302,380,278,330);
		drawLines(g2,317,385,350,320);
		
		setOrange(g2,220,460);
		
		drawLines(g2,220,468,130,425);
		drawLines(g2,230,460,215,385);
		drawLines(g2,242,465,305,405);
		
		setOrange(g2,315,440);
		
		drawLines(g2,315,455,245,470);
		drawLines(g2,324,440,312,405);
		
		setOrange(g2,200,550);
		
		drawLines(g2,215,550,230,485);
		
		setOrange(g2,320,530);
		
		drawLines(g2,225,562,320,544);
		drawLines(g2,240,482,322,535);
		drawLines(g2,332,530,328,465);
		
		setOrange(g2,260,630);
		
		drawLines(g2,262,633,217,575);
		drawLines(g2,278,632,330,555);
		
		setOrange(g2,360,610);
		
		drawLines(g2,285,640,360,624);
		drawLines(g2,370,610,338,555);
		
		setGreen(g2,390,400);
		
		drawLines(g2,390,407,320,395);
		drawLines(g2,390,420,340,448);
		drawLines(g2,398,400,360,318);
		
		setGreen(g2,420,290);
		
		drawLines(g2,420,302,365,307);
		drawLines(g2,432,315,407,400);
		
		setGreen(g2,480,340);
		
		drawLines(g2,483,341,445,308);
		drawLines(g2,483,360,415,405);
		
		setGreen(g2,380,220);
		
		drawLines(g2,358,297,388,244);
		drawLines(g2,430,290,396,244);
		
		setGreen(g2,475,218);
		
		drawLines(g2,475,229,405,233);
		drawLines(g2,488,242,492,340);
		drawLines(g2,479,239,441,292);
		
		setGreen(g2,405,150);
		
		drawLines(g2,412,175,395,220);
		drawLines(g2,426,173,485,218);
		
		setGreen(g2,390,80);
		
		drawLines(g2,405,105,415,150);
		
		setGreen(g2,315,100);
		
		drawLines(g2,390,95,340,110);
		drawLines(g2,407,155,338,120);
		
		setGreen(g2,470,80);
		
		drawLines(g2,415,90,470,92);
		drawLines(g2,425,153,478,104);
		drawLines(g2,482,105,488,217);
		
		setGreen(g2,380,30);
		
		drawLines(g2,395,55,400,80);
		drawLines(g2,385,53,336,104);
		drawLines(g2,405,48,474,84);
		drawLines(g2,380,40,305,30);
		
		setGreen(g2,480,5);
		
		drawLines(g2,480,17,405,40);
		drawLines(g2,492,30,485,80);
		
		setGreen(g2,570,270);
		
		drawLines(g2,570,280,500,230);
		drawLines(g2,572,290,505,345);
		
		setCyan(g2,640,260);
		
		drawLines(g2,595,282,640,275);
		
		setCyan(g2,710,200);
		
		drawLines(g2,712,220,662,262);
		
		setCyan(g2,720,300);
		
		drawLines(g2,720,306,664,277);
		drawLines(g2,732,300,725,225);
		
		setCyan(g2,790,240);
		
		drawLines(g2,790,250,735,217);
		drawLines(g2,792,260,745,308);
	}
	
	
	// Draw the countries
	private void setYellow(Graphics2D g2, int x, int y) {
		g2.setColor(Color.YELLOW);
		g2.fillOval(x,y,25,25);
		g2.setColor(Color.BLACK);
		g2.drawOval(x,y,25,25);
	}
	
	private void setBlue(Graphics2D g2, int x, int y) {
		g2.setColor(Color.BLUE);
		g2.fillOval(x,y,25,25);
		g2.setColor(Color.BLACK);
		g2.drawOval(x,y,25,25);
	}
	
	private void setOrange(Graphics2D g2, int x, int y) {
		g2.setColor(Color.ORANGE);
		g2.fillOval(x,y,25,25);
		g2.setColor(Color.BLACK);
		g2.drawOval(x,y,25,25);
	}
	
	private void setGreen(Graphics2D g2, int x, int y) {
		g2.setColor(Color.GREEN);
		g2.fillOval(x,y,25,25);
		g2.setColor(Color.BLACK);
		g2.drawOval(x,y,25,25);
	}
	
	private static void setRed(Graphics2D g2, int x, int y) {
		g2.setColor(Color.RED);
		g2.fillOval(x,y,25,25);
		g2.setColor(Color.BLACK);
		g2.drawOval(x,y,25,25);
	}
	
	private void setCyan(Graphics2D g2, int x, int y) {
		g2.setColor(Color.CYAN);
		g2.fillOval(x,y,25,25);
		g2.setColor(Color.BLACK);
		g2.drawOval(x,y,25,25);
	}
	
	// Draw lines
	private void drawLines(Graphics2D g2, int x1, int y1, int x2, int y2) {
		g2.setColor(Color.BLACK);
		Line2D.Double lines = new Line2D.Double(x1,y1,x2,y2);
		g2.draw(lines);
	}
	
	// initialize the plyaer, 
	//Allocate 9 territories to each of the two players 
	//and 6 territories to each of the 4 neutral players. 
	//The allocation does not need to be random. Put one army on each territory.
	public static void playerTerritories_Initialization() {
		// P1 - RED
		Graphics2D g2 = null;
		setRed(g2,75,25);
		
	}
	
}
