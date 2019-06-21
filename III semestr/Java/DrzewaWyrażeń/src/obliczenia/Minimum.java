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
    
    public Minimum(Wyrazenie lew, Wyrazenie praw) {
        left = lew;  
        right = praw; 
    }
    
    public double oblicz() {
        return Math.min(left.oblicz(), right.oblicz()) ;
    }
    
    public String toString() { 
        return "min("+left+","+right+")";
    }
    
}