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
public class Pow extends Funkcja{
    
    /**
     *
     * @param lew lewa czesc wyrazenia
     * @param praw lewa czesc wyrazenia
     */
    public Pow() {
        arn = 2;
    }
    
    /**
     *
     * @return
     */
    public double oblicz() {
        return Math.pow(par[0], par[1] );
    }
    
    public String toString() { 
        return "pow("+par[0]+","+par[1]+")";
    }
    
}

    
