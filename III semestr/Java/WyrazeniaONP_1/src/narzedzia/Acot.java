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
public class Acot extends Funkcja {
    
    /**
     *
     */
    public Acot() {
        arn = 1;
    }

    public double oblicz() {
        return Math.asin(par[0] / Math.sqrt(1 + par[0] * par[0]));
    }
    
    public String toString() { 
        return "arccotan(" + par[0] +")";
    }
    
}
