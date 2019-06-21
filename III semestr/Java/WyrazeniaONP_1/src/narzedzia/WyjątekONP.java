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
public class WyjątekONP extends Exception{

    /**
     *
     */
    public WyjątekONP() { super("wyjątek onp"); }

    /**
     *
     * @param message
     */
    public WyjątekONP(String message) { super(message); }

    /**
     *
     * @param message
     * @param cause
     */
    public WyjątekONP(String message, Throwable cause) { super(message, cause); }

    /**
     *
     * @param cause
     */
    public WyjątekONP(Throwable cause) { super(cause); }
    
}
