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
public class Funkcja extends Symbol implements Funkcyjny {
    
    /**
     *
     */
    protected int arn;

    /**
     *
     */
    protected int dost;

    /**
     *
     */
    protected double[] par;
    
    /**
     *
     */
    public Funkcja() {
        dost = 0;
        par = new double[2];
    }
    
    public double oblicz() throws WyjątekONP{
        return this.oblicz();
    }
    
    public String toString() { 
        return this.toString();
    }
    
    /**
     *
     * @param a
     * @throws WyjątekONP
     */
    public void dodajArgument (double a) throws WyjątekONP {
        par[dost] = a;
        dost++;
    }
    
    /**
     *
     * @return
     */
    public int brakująceArgumenty () {
        return arn - dost;
    }
    
    /**
     *
     * @return
     */
    public int arność () {
        return arn;
    }
    
}
