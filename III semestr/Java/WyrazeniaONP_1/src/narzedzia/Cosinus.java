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
public class Cosinus extends Funkcja {
    
    
    /**
     *
     */
    public Cosinus() {
        arn = 1;
    }
    
    /**
     *
     * @return cosunis wyrazenia
     */
    public double oblicz() {
        return Math.cos(par[0]);
    }
    
    public String toString() { 
        return "cos( " + par[0] +" )";
    }
}

