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
public class Min extends Funkcja {
    
    /**
     *
     * @param lew lewa czesz wyrazenia
     * @param praw prawa czesz wyrazenia
     */
    public Min() {
        arn = 2;
    }
    
    /**
     *
     * @return minimum
     */
    public double oblicz() {
        return Math.min(par[0], par[1]) ;
    }
    
    public String toString() { 
        return "min("+par[0]+","+par[1]+")";
    }
    
}