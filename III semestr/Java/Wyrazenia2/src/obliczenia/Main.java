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
    
    /**
     *
     */
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
    
    System.out.println(w1 + " = " + w1.oblicz());
    
    Wyrazenie w2 = new Dodawanie(
        new Liczba(2),
        new Mnozenie(
            new Zmienna("x"),
            new Liczba(7)));
    
    System.out.println(w2 + " = " + w2.oblicz());
    
    Wyrazenie w3 = new Dzielenie(
        new Odejmowanie(
            new Mnozenie(
                new Liczba(3),
                new Liczba(11)),
            new Liczba(5)),
        new Dodawanie(
            new Liczba(7),
            new Liczba(5)));
    
    System.out.println(w3 + " = " + w3.oblicz());
    
    Wyrazenie w4 = new Arctan(
        new Dzielenie(
            new Mnozenie(
                new Dodawanie(
                    new Zmienna("x"),
                    new Liczba(13)),
                new Zmienna("x")),
            new Liczba(2)));
    
    System.out.println(w4 + " = " + w4.oblicz());
    
     Wyrazenie w5 = new Dodawanie(
        new Potegowanie(
            new Liczba(2),
            new Liczba(5)),
        new Mnozenie(
            new Zmienna("x"),
            new Logarytmowanie(
                new Liczba(2),
                new Zmienna("y"))));
    
    System.out.println(w5 + " = " + w5.oblicz());
       
    }
    
}
