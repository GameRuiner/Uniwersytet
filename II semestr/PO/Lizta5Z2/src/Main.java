// Marko Golovko
// Pracownia PO, piątek, s. 137
// L5, z2, Wyrażenia 
// Wyrażenia
// testy
// 2018-04-05
public class Main {

	public static void main(String[] args) {
		Wyrazenie x =  new Zmienna("x");
		Wyrazenie wyrazenie = new Odejmij( (new Pomnoz(new Stala(7), x)) ,new Stala(20));
		System.out.println(wyrazenie);
		System.out.println(wyrazenie.oblicz());
		
		Wyrazenie wyrazenie2 = new Dodaj( (new Podziel(new Stala(9), x)) ,new Pomnoz(new Stala(2),x));
		System.out.println(wyrazenie2);
		System.out.println(wyrazenie2.oblicz());
	}

}
