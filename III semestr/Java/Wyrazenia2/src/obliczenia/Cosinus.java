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
public class Cosinus extends Operator1Arg {
    
    Wyrazenie expr;
    
    /**
     *
     * @param exp wyrazenia dla obliczenia
     */
    public Cosinus(Wyrazenie exp) {
        expr = exp;
    }
    
    /**
     *
     * @return cosunis wyrazenia
     */
    public double oblicz() {
        return Math.cos(expr.oblicz());
    }
    
    public String toString() { 
        return "sin( " + expr +" )";
    }
}

