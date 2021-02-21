
public class Card {
		private String name;
		public enum type{Infantry,Cavalry,Artillery};
		private type cardType;
		private int cardNo;
		
		public Card(String n, type p, int no){
			name = n;
			cardType = p;
			cardNo = no;
		}
		
		public String getName() {
			return name;
		}
		
		public type getType() {
			return cardType;
		}
		
		public int getCardNo() {
			return cardNo;
		}
		
		public String toString() {
			return cardType + ", " + name;
		}
}
