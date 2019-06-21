/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narzedzia;

/**
 *
 * @author MarkG
 */
public class Frac extends Funkcja {
    public Frac() {
        arn = 1;
    }
    
    /**
     *
     * @return wartbezwgl wyrazenia
     */
    public double oblicz() {
        return par[0] - Math.floor(par[0]);
    }
    
    public String toString() { 
        return "frac("+par[0]+")";
    }
}
