// Marko Golovko
// Pracownia PO, piątek, s. 137
// L5, z1, lista sortowana
// Lista sortowana
// klas podklasu Vehicles
// 2018-04-05
public class Boat extends Vehicles {
	
	int length;
	
	Boat(int y, String f, int l) {
		super(y, f);
		length = l;
	}
	
	@Override
	public String toString() {
		return "Boat [year=" + year + ", firm=" + firm + ", length=" + length + " metres]";
	}
	

}
