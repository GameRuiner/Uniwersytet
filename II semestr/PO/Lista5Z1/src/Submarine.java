// Marko Golovko
// Pracownia PO, piątek, s. 137
// L5, z1, lista sortowana
// Lista sortowana
// klas podklasu Vehicles
// 2018-04-05
public class Submarine extends Vehicles {

	int displacement;
	
	Submarine(int y, String f, int d) {
		super(y, f);
		displacement = d;
	}
	
	
	public String toString() {
		return "Submarine [year=" + year + ", firm=" + firm + ", displacement=" + displacement + " t]";
	}

}
