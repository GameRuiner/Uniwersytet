/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliczenia;

/**
 *
 * @author MarkG
 */
public class Liczba extends Wyrazenie {
    double value;
    
    /**
     *
     * @param val wartosc
     */
    public Liczba(double val) {
        value = val;
    }
    
    /**
     *
     * @return wartosc
     */
    public double oblicz() {
        return value;
    }
    
    public String toString() { 
        return Double.toString(value);
    }
}
