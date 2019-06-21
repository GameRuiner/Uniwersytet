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
public class Potegowanie extends Operator2Arg{
    
    /**
     *
     * @param lew lewa czesc wyrazenia
     * @param praw lewa czesc wyrazenia
     */
    public Potegowanie(Wyrazenie lew, Wyrazenie praw) {
        left = lew;
        right = praw;
    }
    
    /**
     *
     * @return
     */
    public double oblicz() {
        return Math.pow(left.oblicz(), right.oblicz() );
    }
    
    public String toString() { 
        return "pow("+left+","+right+")";
    }
    
}

    
