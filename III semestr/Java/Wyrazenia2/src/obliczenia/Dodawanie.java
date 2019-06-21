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
public class Dodawanie extends Operator2Arg {
    
    /**
     *
     * @param lew lewa czesc wyrazenia
     * @param praw prawa czesc wyrazenia
     */
    public Dodawanie(Wyrazenie lew, Wyrazenie praw) {
        left = lew;
        right = praw;
    }
    
    /**
     *
     * @return suma wyrazenia
     */
    public double oblicz() {
        return left.oblicz() + right.oblicz();
    }
    
    public String toString() { 
        return "(" + left + "+" + right+")";
    }
    
}
