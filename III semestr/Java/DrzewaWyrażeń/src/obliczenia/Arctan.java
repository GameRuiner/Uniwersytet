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
public class Arctan extends Operator1Arg {
    
    Wyrazenie expr;
    
    public Arctan(Wyrazenie exp) {
        expr = exp;
    }
    
    public double oblicz() {
        return Math.atan(expr.oblicz());
    }
    
    public String toString() { 
        return "arctan( " + expr +" )";
    }
}
