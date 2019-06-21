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
public class Sin extends Funkcja {
        
    /**
     *
     */
    public Sin() {
        arn = 1;
    }
    
    /**
     *
     * @return  obliczony sinusa
     */
    public double oblicz() {
        return Math.sin(par[0]);
    }
    
    public String toString() { 
        return "sin("+par[0]+")";
    }
}

