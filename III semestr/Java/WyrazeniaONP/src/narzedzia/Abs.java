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
public class Abs extends Funkcja{
    
    /**
     *
     * @param exp wyrazenie
     */
    public Abs() {
        arn = 1;
    }
    
    /**
     *
     * @return wartbezwgl wyrazenia
     */
    public double oblicz() {
        return Math.abs(par[0]);
    }
    
    public String toString() { 
        return "|"+par[0]+"|";
    }
}
