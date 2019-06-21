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
public class Stala extends Symbol {
    
  
    
    private double value;
    private String name;
    
    private double phi = 1.6180339887;
    private double e = 2.7182818284;
    private double pi = 3.1415926535;;
    
    /**
     *
     * @param s nazwa stalej (phi, e, pi)
     */
    public Stala(String s) {
        name = s;
        switch(s) {
            case "phi":
                value = phi;
                break;
            case "e":
                value = e;
                break;
            case "pi":
                value = pi;
                break;
        }
    }
    
    /**
     *
     * @return
     */
    public double oblicz() {
        return value;
    }
    
    public String toString() { 
        return name;
    }
}
