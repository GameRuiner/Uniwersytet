/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarycalculator2;
import java.math.BigInteger;
/**
 *
 * @author MarkG
 */
public class CalculatorController{
    
    String binary;
    String decimal;
    BigInteger bi;
    String astr;
    String bstr;
   
   
   
   public String MakeExpression(String left, String right, String sign) throws Exception {
       BigInteger a = MakeInt(left);
       BigInteger b = MakeInt(right);
       decimal = "";
       astr = "";
       bstr = "";
       switch(sign) {
           case "+":
            bi = a.add(b);
            break;
            
           case "-":
            bi = a.subtract(b);
            break;
            
           case "*":
            bi = a.multiply(b);
            break;
            
           case "/":
            bi = a.divide(b);
               if (b.intValue() == 0) {
                   return "Error divide by zero";
               }
            break;
            
           case "%":
            bi = a.mod(b);
               if (b.intValue() == 0) {
                   return "Error modulo zero";
               }
            break;
            
           default:
               return "Sign Error";
       }
       binary = bi.toString(2);
       decimal = bi.toString();
       astr = a.toString();
       bstr = b.toString();
       return binary;
       } 
   
   
  BigInteger MakeInt(String str) throws Exception {
    char[] buffer = str.toCharArray();
    BigInteger a = new BigInteger("0");
    for (int i = 0; i < buffer.length; i++) {
        int power = buffer.length - i - 1;
        if (buffer[i] == '1') {
            a = a.add(BigInteger.valueOf((long)Math.pow(2, power)));
        } else {
            if (buffer[i] != '0') {
                throw new Exception("Error input " + str);
            }
            }
    }
    return a;
  }
           
  
}
