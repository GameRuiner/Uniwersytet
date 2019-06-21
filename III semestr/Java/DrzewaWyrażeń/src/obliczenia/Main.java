/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliczenia;
import struktury.Zbior;
import struktury.Para;

/**
 *
 * @author MarkG
 */
public class Main {

    
    public static Zbior vars = new Zbior();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Para x,y;
        try {
            x = new Para("x", 2.0);
            y = new Para("y", 3.0);
            vars.wstaw(x);
            vars.wstaw(y);
        } catch(Exception e) {
            System.out.println(e);
        }
        
        Wyrazenie w1 = new Dodawanie(new Liczba(3), 
                                new Liczba(5));
        
        System.out.println(w1 + "=" + w1.oblicz());
        
        
        
    }
    
}
