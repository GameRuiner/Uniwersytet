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
public class Operator2Arg extends Operator1Arg {
    Wyrazenie left;
    Wyrazenie right;
    
    public String toString() { 
        return "("+left+","+right+")";
    }
    
    
}
