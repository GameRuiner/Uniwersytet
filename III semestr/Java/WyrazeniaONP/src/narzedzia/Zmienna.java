/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narzedzia;
import struktury.Zbior;

/**
 *
 * @author MarkG
 */
public class Zmienna extends Symbol {
    
    private double value;
    private String zmienna;
    private String[] funkcje = {"Phi", "Pi", "E"};
    
    /**
     *
     * @param var nazwa zmiennej
     */
    public Zmienna(String var, double wart) throws WyjątekONP {
        for(int i = 0; i < funkcje.length; i++ ) {
            if (funkcje[i] == var)
                throw new WyjątekONP();
        }
        try {
        value = wart;
        zmienna = var;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     *
     * @return wartosc
     */
    public double oblicz() {
        return value;
    }
    
    public String toString() { 
        return zmienna;
    }
    
    
}
