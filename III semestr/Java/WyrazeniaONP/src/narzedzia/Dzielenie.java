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
public class Dzielenie extends Funkcja{
    
    /**
     *
     * @param lew lewa czesc wyrazenia
     * @param praw prawa czesc wyrazenia
     */
    public Dzielenie() {
       arn = 2;
    }
    
    /**
     *
     * @return iloraz wyrazenia
     */
    public double oblicz() {
        return par[0] / par[1] ;
    }
    
    public String toString() { 
        return  "("+ par[0] + "/" + par[1] +")";
    }
}