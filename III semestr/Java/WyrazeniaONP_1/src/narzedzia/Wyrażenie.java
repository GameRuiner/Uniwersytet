/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narzedzia;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.logging.Level;

/**
 *
 * @author MarkG
 */
public class Wyrażenie {
    private Kolejka kolejka; ; // kolejka symboli wyrażenia ONP (elementy typu Symbol)
    private Stos stos; // stos z wynikami pośrednimi obliczeń (elementy typu Double)
    private ArrayDeque<String> zStos;
    private Zbiór zmienne; // lista zmiennych czyli pary klucz-wartość (String-Double)
    private String napis;
    
    /**
     *
     * @param onp napis zawierający wyrażenie w onp
     * @param zm lista zmiennych
     * @throws WyjątekONP
     */
    public Wyrażenie (String onp, Lista zm) throws WyjątekONP {
        zmienne = new Zbiór();
        napis = "";
        stos = new Stos();
        zStos = new ArrayDeque();
        kolejka = new Kolejka();
        String[] list = onp.split(" ");
        for(int i = 1; i < list.length; i++) {
            try {
                double d = Double.parseDouble(list[i]);
                kolejka.push(new Liczba(d));
               // stos.push(d);
            } catch (NumberFormatException e) {
                switch(list[i]) {
                    case "=":
                        String name = zStos.pop();
                        double val = kolejka.getLast().oblicz();
                        zmienne.put(name, val );
                        napis = zmienne.toString();
                        zm.add(name+" = "+val);
                        break;
                    case "abs": {
                        kolejka.push(new Abs());
                        break;
                    }
                    case "arccot": {
                        Acot s = new Acot();
                        kolejka.push(s);
                        break;
                    } 
                    
                    case "arctan": {
                        Atan s = new Atan();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "ceil": {
                        Ceil s = new Ceil();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "cos": {
                        Cosinus s = new Cosinus();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "+": {
                        Dodawanie s = new Dodawanie();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "/": {
                        Dzielenie s = new Dzielenie();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "e": {
                        E s = new E();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "exp": {
                        Exp s = new Exp();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "floor": {
                        Floor s = new Floor();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "Frac": {
                        Frac s = new Frac();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "ln": {
                        Ln s = new Ln();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "log": {
                        Log s = new Log();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "max": {
                        Max s = new Max();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "min": {
                        Min s = new Min();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "*":
                        Mnozenie mn = new Mnozenie();
                        kolejka.push(mn);
                        break;
                        
                    case "-": {
                        Odejmowanie s = new Odejmowanie();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "phi":
                        Phi phi = new Phi();
                        kolejka.push(phi);
                        break;
                    
                    case "pi":
                        Pi p = new Pi();
                        kolejka.push(p);
                        break;
                        
                    case "power":
                        Pow pow = new Pow();
                        kolejka.push(pow);
                        break;
                        
                    case "sgn": {
                        Sgn s = new Sgn();
                        kolejka.push(s);
                        break;
                    }
                    
                    case "sin": {
                        Sin s = new Sin();
                        kolejka.push(s);
                        break;
                    }
                          
                    default:
                        if (zmienne.containsKey(list[i])) {
                           kolejka.push(new Liczba(zmienne.get(list[i])));
                        } else
                            //jest w zm?
                            for(int j = 0; j < zm.size(); j++ ){
                                if(Objects.equals(zm.get(j).split(" ")[0], list[i])) {
                                    napis += zm.get(j);
                                    kolejka.push(new Liczba(Double.parseDouble(zm.get(j).split(" ")[2])));
                                
                            }
                            }
                        zStos.push(list[i]);
                }
            }
        }
    }
    
    /**
     *
     */
    public void calc() {
        while(kolejka.size() > 0) {
            Funkcja s = kolejka.removeLast();
            try {
                 while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                                
            }
                 stos.push(s.oblicz());
            } catch (Exception e) {
                Main.logger.log(Level.SEVERE,"Calculation error", new ONP_PustyStos());
            }
        }
        
    }
     
    public String toString() {
        if (stos.size() > 0) {
            return stos.getFirst()+"";
        }
        return napis;
    }
   
    
}
