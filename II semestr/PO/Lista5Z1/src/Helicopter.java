// Marko Golovko
// Pracownia PO, piątek, s. 137
// L5, z1, lista sortowana
// Lista sortowana
// klas podklasu Vehicles
// 2018-04-05
public class Helicopter extends Vehicles {
    int maxheight;
    
    
	Helicopter(int y, String f, int height) {
		super(y, f);
		// TODO Auto-generated constructor stub
		maxheight = height;
	}
	
	
	@Override
	public String toString() {
		return "Helicopter [year=" + year + ", firm=" + firm + ", max height=" + maxheight + " feet]";
	}
	

}
