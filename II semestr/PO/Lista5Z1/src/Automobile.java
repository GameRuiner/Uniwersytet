// Marko Golovko
// Pracownia PO, piątek, s. 137
// L5, z1, lista sortowana
// Lista sortowana
// klas podklasu Vehicles
// 2018-04-05
public class Automobile extends Vehicles {
	String fuel;
	
	
	Automobile(int y, String f, String fu) {
		super(y, f);
		fuel = fu;
	}
	
	
	@Override
	public String toString() {
		return "Automobile [year=" + year + ", firm=" + firm + ", fuel=" + fuel + "]";
	}
	
	

}
