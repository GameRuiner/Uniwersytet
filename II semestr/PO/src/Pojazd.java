
//Karol Mirek
//Pracownia PO, czwartek, s.108
//L7, z1, Swing
//Swing
//Klasa Pojazd
//2018-04-11 
//Java
//Eclipse
import java.io.Serializable;

//Klasa pojazd
public class Pojazd implements Serializable
{
	public String marka;
	public int rok , predkosc ;
	//Konstruktor 3 argumentowy   
	public Pojazd(String marka, int rok, int predkosc)
	{
		this.marka = marka; this.rok = rok; this.predkosc = predkosc;
	}
	//Konstruktor bezargumentowy
	public Pojazd() {this("marka",1998,120);}
	//Zamiana na stringa
	public String toString()
	{
		return marka + "\n" + 
				Integer.toString(rok) + 
				"\n" + Integer.toString(predkosc); 
	}

}
