// Marko Golovko
// Pracownia PO, piątek, s. 137
// L7 implementacja edytora obiektów
// JFrame 
// klas Main
// 2018-20-05
import java.awt.EventQueue;
import javax.swing.JFrame;
public class Main {
	public static void main(String[] args)
	{
		String type="Vehicle";
		String file="";
		EventQueue.invokeLater(
				
				new Runnable()
				{
					//metoda wybierająca edytor w zależności od klasy
					public void run()
					{
						JFrame frame;
						if(type == "Vehicle")
							frame = new EditVehicle(file);
		            	else if(type == "Tram")
		            		frame = new EditTram(file);
		            	else 
		            		frame = new EditCar(file);
						frame.setVisible(true);
		            	
					}
				});
	}
}