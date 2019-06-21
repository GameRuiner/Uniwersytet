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
public class Atan extends Funkcja {
    
    public Atan() {
        arn = 1;
    }
    
    /**
     *
     * @return zwraca arctan wyrazenia
     */
    public double oblicz() {
        return Math.atan(par[0]);
    }
    
    public String toString() { 
        return "arctan(" + par[0] +")";
    }
}
