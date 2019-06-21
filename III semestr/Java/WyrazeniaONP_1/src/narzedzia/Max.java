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
public class Max extends Funkcja {
     
    /**
     *
     */
    public Max() {
        arn = 2;
    }
    
    /**
     *
     * @return maksimum
     */
    public double oblicz() {
        return Math.max(par[0], par[1]) ;
    }
    
    public String toString() { 
        return "max("+par[0]+","+par[1]+")";
    }
    
}