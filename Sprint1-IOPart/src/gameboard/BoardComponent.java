package gameboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.JComponent;

public class BoardComponent extends JComponent{
	public void paintComponent(Graphics g) {
		// Recover Graphics2D
		Graphics2D g2 = (Graphics2D)g;
		
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
	}
	
	
	// Draw the countries
	private void setYellow(Graphics2D g2, int x, int y) {
		g2.setColor(Color.YELLOW);
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
	
}
