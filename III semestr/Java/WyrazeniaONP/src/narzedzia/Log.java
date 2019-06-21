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
public class Log extends Funkcja{
    
    /**
     *
     * @param lew lewa czesz wyrazenia - wartosc
     * @param praw prawa czesz wyrazenia - podstawa
     */
    public Log() {
       arn = 2 ;
    }
    
    /**
     *
     * @return logarytm 
     */
    public double oblicz() {
        return Math.log(par[0]) / Math.log(par[1]) ;
    }
    
    public String toString() { 
        return "log("+par[0]+","+par[1]+")";
    }
    
}