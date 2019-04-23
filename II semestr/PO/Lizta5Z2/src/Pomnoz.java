// Marko Golovko
// Pracownia PO, piątek, s. 137
// L5, z2, Wyrażenia 
// Wyrażenia
// Mnozenie
// 2018-04-05
public class Pomnoz extends Wyrazenie{
		Wyrazenie a;
		Wyrazenie b;
		
		
		Pomnoz(Wyrazenie x, Wyrazenie y){
			a = x;
			b = y;
		}
		
		
		public int oblicz() {
			return a.oblicz() * b.oblicz();
		}
		
		
		public String toString() {
			return "(" + a.toString()+" * "+b.toString() + ")" ;
		}
			
			
	}