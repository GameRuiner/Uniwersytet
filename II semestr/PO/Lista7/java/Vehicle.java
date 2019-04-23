// Marko Golovko
// Pracownia PO, piątek, s. 137
// L7 implementacja edytora obiektów
// JFrame 
// class Vehicle
// 2018-20-05
import java.io.Serializable;

public class Vehicle implements Serializable
{

	public int year , cost ;
	public String brand;   
	
	//konstruktor argumentowy
	public Vehicle(String b, int y, int c)
	{
		this.brand = b; 
		this.year = y; 
		this.cost = c;
	}
	
	
	//zwraca napis z obiektu 
	@Override
	public String toString() {
		return brand + "\n" + 
				Integer.toString(year) + 
				"\n" + Integer.toString(cost); 
	}
	
	//konstruktor bezargumentowy
	public Vehicle() {
		this("brand",1998,120);
	}


}
