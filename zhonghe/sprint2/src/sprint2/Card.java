package sprint2;

public class Card {
		private String name;
		public enum type{Infantry,Cavalry,Artillery};
		private type cardType;
		
		public Card(String n, type p){
			name = n;
			cardType = p;
		}
		
		public String getName() {
			return name;
		}
		
		public type getType() {
			return cardType;
		}
}
