
public class Card {
	
	private int countryId;
	public enum type{Infantry,Cavalry,Artillery};
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