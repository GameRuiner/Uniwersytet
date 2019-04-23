// Marko Golovko
// Pracownia PO, piątek, s. 137
// L5, z1, lista sortowana
// Lista sortowana
// testy
// 2018-04-05
public class Main {

	public static void main(String[] args) {
		
		SortedList<Vehicles> vlist = new SortedList<Vehicles>();
		
		Boat v1 = new Boat(1998, "Linder",17);
		Helicopter v2 = new Helicopter(1999, "Agusta",10000);
		Automobile v3 = new Automobile(2000, "Ford","Gas");
		Submarine v4 = new Submarine(1964, "Auguste Piccard",222);
		
		vlist.Add(v4);
		vlist.Add(v2);
		vlist.Add(v3);
		vlist.Add(v1);
		vlist.Print();
	}

}
