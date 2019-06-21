/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliczenia;
import struktury.Zbior;

/**
 *
 * @author MarkG
 */
public class Zmienna extends Wyrazenie {
    
    static final Zbior zmienne = Main.vars;
    private double value;
    private String zmienna;
    
    public Zmienna(String var) {
        try {
        value = zmienne.szukaj(var).get();
        zmienna = var;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public double oblicz() {
        return value;
    }
    
    public String toString() { 
        return zmienna;
    }
    
    
}
