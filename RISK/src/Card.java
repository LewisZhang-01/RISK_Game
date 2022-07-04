/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 */

public class Card {
	
	private int countryId;
	public enum type{Infantry,Cavalry,Artillery,Wild};
	private type cardType;
	private String countryName;

	Card (int inCountryId, String inCountryName, type p) {   
		countryId = inCountryId;
		countryName = inCountryName;
		cardType = p;
		return;
	}
	
	public int getCountryId () {
		return countryId;
	}
	
	public String getCountryName () {
		return countryName;
	}
	
	public type getType() {
		return cardType;
	}
	
	public String toString() {
		return cardType + ", " + countryName;
	}
}