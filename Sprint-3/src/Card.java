/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 * */

public class Card {
	
	private int countryId;
	private String countryName;

	Card (int inCountryId, String inCountryName) {   
		countryId = inCountryId;
		countryName = inCountryName;
		return;
	}
	
	public int getCountryId () {
		return countryId;
	}
	
	public String getCountryName () {
		return countryName;
	}
	
}