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
public class Sinus extends Operator1Arg  {
    
    Wyrazenie expr;
    
    public Sinus(Wyrazenie exp) {
        expr = exp;
    }
    
    public double oblicz() {
        return Math.sin(expr.oblicz());
    }
    
    public String toString() { 
        return "sin("+expr+")";
    }
}

