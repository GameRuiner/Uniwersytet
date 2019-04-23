// Marko Golovko
//Pracownia PO, piątek, s. 137
//L6, z2, implementacja edytora obiektów
//JFrame 
//klas samochodu
//2018-13-05
@SuppressWarnings("serial")
public class Samochod extends Pojazd {

	int passangers;
	String fuel;
	String color;
	
	@Override
	public String toString() {
		return  "Samochod [brand=" + brand + ", year=" + year + ", cost=" + cost +
				"\n passangers=" + passangers + ", fuel=" + fuel + ", color=" + color + "]\n";
	}

	Samochod(String b, int y, int c, int p, String f, String col ) {
		super(b, y, c);
		passangers = p;
		fuel = f;
		color = col;
	}

}
