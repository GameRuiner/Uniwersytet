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
public class Logarytmowanie extends Operator2Arg{
    
    /**
     *
     * @param lew lewa czesz wyrazenia - wartosc
     * @param praw prawa czesz wyrazenia - podstawa
     */
    public Logarytmowanie(Wyrazenie lew, Wyrazenie praw) {
        left = lew; //value 
        right = praw; // podst
    }
    
    /**
     *
     * @return logarytm 
     */
    public double oblicz() {
        return Math.log(left.oblicz()) / Math.log(right.oblicz()) ;
    }
    
    public String toString() { 
        return "log("+left+","+right+")";
    }
    
}