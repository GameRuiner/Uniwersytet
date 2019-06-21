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
public class Maksimum extends Operator2Arg {
     
    public Maksimum(Wyrazenie lew, Wyrazenie praw) {
        left = lew;  
        right = praw; 
    }
    
    public double oblicz() {
        return Math.max(left.oblicz(), right.oblicz()) ;
    }
    
    public String toString() { 
        return "max("+left+","+right+")";
    }
    
}