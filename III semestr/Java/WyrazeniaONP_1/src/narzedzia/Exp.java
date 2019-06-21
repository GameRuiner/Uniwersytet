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
public class Exp extends Funkcja {

    /**
     *
     */
    public Exp() {
        arn = 1;
    }

    public double oblicz() {
        return Math.exp(par[0]);
    }
    
    public String toString() { 
        return "Exp(" + par[0] +")";
    }
    
}
