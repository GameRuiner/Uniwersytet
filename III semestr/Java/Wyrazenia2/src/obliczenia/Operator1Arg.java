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
public class Operator1Arg extends Wyrazenie {
    
    protected Wyrazenie expr;
    
    /**
     *
     * @return wartosc
     */
    public double oblicz() {
        return expr.oblicz();
    }
    
    public String toString() { 
        return expr.toString();
    }
}
