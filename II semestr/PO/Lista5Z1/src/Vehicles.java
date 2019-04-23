// Marko Golovko
// Pracownia PO, piątek, s. 137
// L5, z1, lista sortowana
// Lista sortowana
// glowny klass
// 2018-04-05
public class Vehicles implements Comparable<Vehicles> {
	int year;
	String firm;
	
	
	//zwraca napis z objektu
	@Override
	public String toString() {
		return "Vehicles [year=" + year + ", firm=" + firm + "]";
	}


	//konstruktor
	Vehicles (int y, String f){
		year = y;
		firm = f;
	}
	
	
	//metoda porównająca pojazdy
	@Override
	public int compareTo(Vehicles o) {
		if (o.year == this.year) return 0;
		else {
			if (o.year < this.year) return -1;
			return 1;
		}
	}
}
