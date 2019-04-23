//Karol Mirek
//Pracownia PO, czwartek, s.108
//L7, z1, Swing
//Swing
//Main
//2018-04-11 
//Java
//Eclipse 
import java.awt.EventQueue;
import javax.swing.JFrame;
public class main {
	public static void main(String[] args)
	{
		String plik="";
		String klasa="Pojazd";
		EventQueue.invokeLater(
				
				new Runnable()
				{
					public void run()
					{
						JFrame ex;
						if(klasa == "Pojazd")
							ex = new EdytorPojazd(plik);
		            	else if(klasa == "Samochód")
		            		ex = new EdytorSamochod(plik);
		            	else 
		            		ex = new EdytorTramwaj(plik);
						ex.setVisible(true);
		            	
					}
				});
	}
}