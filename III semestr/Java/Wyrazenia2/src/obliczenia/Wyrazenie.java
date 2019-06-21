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
public abstract class Wyrazenie implements Obliczalny {
    
    /**
     *
     * @param wyr wyrazenia
     * @return suma
     */
    public static double suma (Wyrazenie... wyr) {
        double res = 0;
        for (int i = 0; i < wyr.length; i ++) {
            res += wyr[i].oblicz();
        }
        
        return res;
    }
    
    /**
     *
     * @param wyr wyrazenia
     * @return iloczym
     */
    public static double iloczyn (Wyrazenie... wyr) {
        double res = 1;
        for (int i = 0; i < wyr.length; i ++) {
            res *= wyr[i].oblicz();
        }
        
        return res;
    }
    
    /**
     *
     * @param o wyrazenia dla porownania
     * @return wartosc bolowska
     */
    public boolean equals(Wyrazenie o) {
        return this.oblicz() == o.oblicz();
    }
    
    
}
