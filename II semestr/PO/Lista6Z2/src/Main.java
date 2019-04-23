// Marko Golovko
// Pracownia PO, piątek, s. 137
// L6, z2, Collection<E>
// implementacja interfejsu 
// klas Main
// 2018-13-05
public class Main {

	public static void main(String[] args) {
		
		SortedList<Vehicles> vlist = new SortedList<Vehicles>();
		
		SortedList<Vehicles> vlist2 = new SortedList<Vehicles>();
		
		Vehicles v1 = new Vehicles(1998, "Linder");
		Vehicles v2 = new Vehicles(1999, "Agusta");
		Vehicles v3 = new Vehicles(2000, "Ford");
		Vehicles v4 = new Vehicles(1964, "Auguste Piccard");
		Vehicles v5 = new Vehicles(1974, "Mercedes");
		Vehicles v6 = new Vehicles(1994, "Skoda");
		
		vlist.add(v4);
		vlist.add(v2);
		vlist.add(v3);
		vlist.add(v1);
		vlist.Print();
		
		vlist2.add(v5);
		vlist2.add(v6);
		
		//dodaje wszystkie elementy z vlist2 do vlist
		vlist.addAll(vlist2);
		vlist.Print();
		
		//usuwa pierwszy element
		vlist.Pop();
		vlist.Print();
		
		//usuwa dany obiekt
		vlist.remove(v4);
		vlist.remove(v6);
		vlist.Print();
		
		//robi pustą listę
		vlist.clear();
		vlist.Print();
	}

}
