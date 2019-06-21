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
public class Odwrotnosc extends Operator1Arg {
    Wyrazenie expr;
    
    public Odwrotnosc(Wyrazenie exp) {
        expr = exp;
    }
    
    public double oblicz() {
        return 1/expr.oblicz();
    }
    
    public String toString() { 
        return "1/"+expr;
    }
}

