// Marko Golovko
// Pracownia PO, piątek, s. 137
// L6, z2, implementacja edytora obiektów
// JFrame 
// klas pojazdu
// 2018-13-05
import java.io.Serializable;

@SuppressWarnings("serial")
public class Pojazd implements Serializable {
	String brand;
	int year;
	int cost;
	
	Pojazd (String b, int y, int c){
		brand = b;
		year = y;
		cost = c;
	}

	@Override
	public String toString() {
		return "Pojazd [brand=" + brand + ", year=" + year + ", cost=" + cost + "]";
	}

}
