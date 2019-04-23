// Marko Golovko
// Pracownia PO, piątek, s. 137
// L5, z2, Wyrażenia 
// Wyrażenia
// Zmienna
// 2018-04-05
import java.util.Scanner;

public class Zmienna extends Wyrazenie{
			int val;
			public String name;
			boolean defined;
			
			
	         Zmienna(String n){
				name = n;
				defined = false;
			}
	         
	         
	        @Override
	        public int oblicz()  {
				if (defined) return val;
				else {
					@SuppressWarnings("resource")
					Scanner scanner = new Scanner(System.in);
					System.out.println("Zmienna "+ name + " =");
					int n = 0;
					n = scanner.nextInt();
					val = n;
					defined = true;
					return val;
				}
			}
	        
	        public String toString() {
				return name;
			}
		}