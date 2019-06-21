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
public class Floor extends Funkcja {
        /**
     *
     */
    public Floor() {
        arn = 1;
    }
    
    /**
     *
     * @return wartbezwgl wyrazenia
     */
    public double oblicz() {
        return Math.floor(par[0]);
    }
    
    public String toString() { 
        return "floor("+par[0]+")";
    }
}
