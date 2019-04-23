// Marko Golovko
// Pracownia PO, piątek, s. 137
// L5, z2, Wyrażenia 
// Wyrażenia
// Stala
// 2018-04-05
public class Stala extends Wyrazenie{
		int val;
		
		
		Stala(int n){
			val = n;
		}
		
		@Override
		public int oblicz() {
			return val;
		}
		
		
		public String toString() {
			return String.valueOf(val);
		}
			
	}