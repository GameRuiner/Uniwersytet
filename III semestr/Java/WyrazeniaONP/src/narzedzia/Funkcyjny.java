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
public interface Funkcyjny extends Obliczalny {
    int arność ();
    int brakująceArgumenty ();
    void dodajArgument (double a) throws WyjątekONP;
    
}
