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
public class Minimum extends Operator2Arg {
    
    /**
     *
     * @param lew lewa czesz wyrazenia
     * @param praw prawa czesz wyrazenia
     */
    public Minimum(Wyrazenie lew, Wyrazenie praw) {
        left = lew;  
        right = praw; 
    }
    
    /**
     *
     * @return minimum
     */
    public double oblicz() {
        return Math.min(left.oblicz(), right.oblicz()) ;
    }
    
    public String toString() { 
        return "min("+left+","+right+")";
    }
    
}