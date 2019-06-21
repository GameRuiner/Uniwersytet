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
public class Dzielenie extends Funkcja{
    
    /**
     *
     */
    public Dzielenie() {
       arn = 2;
    }
    
    /**
     *
     * @return iloraz wyrazenia
     * @throws narzedzia.ONP_DzieleniePrzez0
     */
    public double oblicz() throws ONP_DzieleniePrzez0{
        if (par[1] == 0)
            throw new ONP_DzieleniePrzez0();
        return par[0] / par[1] ;
    }
    
    public String toString() { 
        return  "("+ par[0] + "/" + par[1] +")";
    }
}