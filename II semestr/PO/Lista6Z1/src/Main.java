// Marko Golovko
// Pracownia PO, piątek, s. 137
// L6, z1, 
// Serializacja
// testy
// 2018-04-05

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		SortedList<Vehicles> vlist = new SortedList<Vehicles>();
		SortedList<Vehicles> tvlist = new SortedList<Vehicles>();
		
		Vehicles v1 = new Vehicles(1998, "Linder");
		Vehicles v2 = new Vehicles(1999, "Agusta");
		Vehicles v3 = new Vehicles(2000, "Ford");
		Vehicles v4 = new Vehicles(1964, "Auguste Piccard");
		
		vlist.Add(v4);
		vlist.Add(v2);
		vlist.Add(v3);
		vlist.Add(v1);
		
		
		//zapisywanie na dysk 
		FileOutputStream fos = new FileOutputStream("vlist.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(tvlist);
		oos.flush();
		oos.close();
		
		
		vlist.Add(v4);
		
		
		//czytanie z dysku
		FileInputStream fis = new FileInputStream("vlist.out");
		@SuppressWarnings("resource")
		ObjectInputStream oin = new ObjectInputStream(fis);
		@SuppressWarnings("unchecked")
		SortedList<Vehicles> vlist2 = (SortedList<Vehicles>) oin.readObject();
		vlist2.Print();
		System.out.println('_');
		vlist.Print();
	}

}
