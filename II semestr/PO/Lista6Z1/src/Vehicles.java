// Marko Golovko
// Pracownia PO, piątek, s. 137
// L6, z1, 
// Serializacja
// object dla listy
// 2018-04-05

import java.io.Serializable;

@SuppressWarnings("serial")
public class Vehicles implements Comparable<Vehicles> , Serializable {
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
