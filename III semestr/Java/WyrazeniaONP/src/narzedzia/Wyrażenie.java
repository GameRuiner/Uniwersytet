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
                stos.push(d);
            } catch (NumberFormatException e) {
                switch(list[i]) {
                    case "=":
                        String name = zStos.pop();
                        double val = stos.pop();
                        zmienne.put(name, val );
                        stos.push(val);
                        napis = zmienne.toString();
                        zm.add(name+" = "+val);
                        break;
                    case "abs": {
                        Abs s = new Abs();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    case "arccot": {
                        Acot s = new Acot();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    } 
                    
                    case "arctan": {
                        Atan s = new Atan();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "ceil": {
                        Ceil s = new Ceil();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "cos": {
                        Cosinus s = new Cosinus();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "+": {
                        Dodawanie s = new Dodawanie();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "/": {
                        Dzielenie s = new Dzielenie();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "e": {
                        E s = new E();
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "exp": {
                        Exp s = new Exp();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "floor": {
                        Floor s = new Floor();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "Frac": {
                        Frac s = new Frac();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "ln": {
                        Ln s = new Ln();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "log": {
                        Log s = new Log();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "max": {
                        Max s = new Max();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "min": {
                        Min s = new Min();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "*":
                        Mnozenie mn = new Mnozenie();
                        while (mn.brakująceArgumenty() > 0) {
                                mn.dodajArgument(stos.pop());
                        }
                        kolejka.add(mn);
                        this.calc();
                        break;
                        
                    case "-": {
                        Odejmowanie s = new Odejmowanie();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "phi":
                        Phi phi = new Phi();
                        kolejka.add(phi);
                        this.calc();
                        break;
                    
                    case "pi":
                        Pi p = new Pi();
                        kolejka.add(p);
                        this.calc();
                        break;
                        
                    case "power":
                        Pow pow = new Pow();
                        while (pow.brakująceArgumenty() > 0) {
                                pow.dodajArgument(stos.pop());
                        }
                        kolejka.add(pow);
                        this.calc();
                        break;
                        
                    case "sgn": {
                        Sgn s = new Sgn();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                    
                    case "sin": {
                        Sin s = new Sin();
                        while (s.brakująceArgumenty() > 0) {
                                s.dodajArgument(stos.pop());
                        }
                        kolejka.add(s);
                        this.calc();
                        break;
                    }
                          
                    default:
                        if (zmienne.containsKey(list[i])) {
                            stos.push(zmienne.get(list[i]));
                        } else
                            //jest w zm?
                            for(int j = 0; j < zm.size(); j++ ){
                                if(Objects.equals(zm.get(j).split(" ")[0], list[i])) {
                                    napis += zm.get(j);
                                    stos.push(Double.parseDouble(zm.get(j).split(" ")[2]));
                                
                            }
                            }
                            zStos.push(list[i]);
                }
            }
        }
    }
    
    public void calc() {
        while(kolejka.size() > 0) {
            Symbol s = kolejka.poll();
            try {
            stos.push(s.oblicz());
            } catch (Exception e) {
                Main.logger.log(Level.SEVERE,"Calculation error",e);
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
