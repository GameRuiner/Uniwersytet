// Marko Golovko
// Pracownia PO, piątek, s. 137
// L6, z2, implementacja edytora obiektów
// JFrame 
// klas tramwaja
// 2018-13-05
@SuppressWarnings("serial")
public class Tramwaj extends Pojazd {

	int EnginePower;
	int speed;
	int length;
	
	Tramwaj(String b, int y, int c, int ep, int s, int l) {
		super(b, y, c);
		EnginePower = ep;
		speed = s;
		length = l;
	}

	@Override
	public String toString() {
		return  "Tramwaj [brand=" + brand + ", year=" + year + ", cost=" + cost +
				"\n EnginePower=" + EnginePower + ", speed=" + speed + ", length=" + length + "]";
	}

}
