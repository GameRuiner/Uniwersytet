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
public class WartBezwzgl extends Operator1Arg{
    Wyrazenie expr;
    
    /**
     *
     * @param exp wyrazenie
     */
    public WartBezwzgl(Wyrazenie exp) {
        expr = exp;
    }
    
    /**
     *
     * @return wartbezwgl wyrazenia
     */
    public double oblicz() {
        return Math.abs(expr.oblicz());
    }
    
    public String toString() { 
        return "|"+expr+"|";
    }
}
