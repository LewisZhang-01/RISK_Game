/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 * */

import java.util.ArrayList;

public class Player {
	
	private int id;
	private String name;
	private int numUnits;
	private int numTerrs;
	private ArrayList<Integer> dice = new ArrayList<Integer>();
	
	Player (int inId, String inName, int inNumUnits, int inNumTerrs) {
		id = inId;
		name = inName;
		numUnits = inNumUnits;
		numTerrs = inNumTerrs;
		return;
	}
	
	public void rollDice (int numDice) {
		dice.clear();
		for (int j=0; j<numDice; j++) {
				dice.add(1 + (int)(Math.random() * 6));   
		}
		return;
	}

	public void addTerrs (int inNum) {
		numTerrs = numTerrs + inNum;
		return;
	}
	
	public void addUnits (int inNum) {
		numUnits = numUnits + inNum;
		return;
	}
	
	public void subtractTerrs (int inNum) {
		numTerrs = numTerrs - inNum;
		return;
	}
	
	public void subtractUnits (int inNum) {
		numUnits = numUnits - inNum;
		return;
	}
		
	public int getId () {
		return id;
	}
	
	public String getName () {
		return name;
	}
	
	public int getNumTerrs() {
		return numTerrs;
	}
	
	public int getNumUnits () {
		return numUnits;
	}

	public ArrayList<Integer> getDice () {
		return dice;
	}
	
	public int getDie (int dieId) {
		return dice.get(dieId);
	}
	
}
