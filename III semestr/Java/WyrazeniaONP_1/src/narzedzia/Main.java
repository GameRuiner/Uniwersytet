/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narzedzia;
import java.util.Scanner;
import java.util.Objects;
import java.util.logging.*;

/**
 *
 * @author MarkG
 */
public class Main {
 
    /**
     *
     */
    public static final Logger logger = Logger.getLogger(Main.class.getName()); 
         
    /**
     *
     * @param args nie używane w tym programie
     */
    public static void main(String[] args) {
        FileHandler fh;
        
        try {  

        // This block configure the logger with handler and formatter  
        fh = new FileHandler("C:/temp/test/Calc.log");  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  

        // the following statement is used to log any messages  
        logger.info("Start");  

        } catch (Exception e) {  
        e.printStackTrace();  
        }
        
        Scanner scan = new Scanner(System.in);
        String s = null;
        Lista zm = new Lista();
        while(!(Objects.equals(s, "exit"))) {
            s = scan.nextLine();
            String[] line = s.split(" ");
            try {
            assert Objects.equals(line[0], "calc") || Objects.equals(line[0], "clear") || Objects.equals(line[0], "exit") ;
            } catch (AssertionError e) {
                logger.log(Level.SEVERE,"Assertion error",e);
            }   
            switch(line[0]) {
                case("calc"):
                    try {
                    Wyrażenie w = new Wyrażenie(s,zm);
                    //System.out.println(w);
                    w.calc();
                    logger.info(s+"\n"+w+"");
                    } catch (WyjątekONP e) {
                        System.out.println(e);
                    }
                    break;
                case("clear"): 
                    if (line.length == 1) {
                        zm = new Lista();
                    } else {
                        for(int i = 1; i < line.length; i++) {
                            for(int j = 0; j < zm.size(); j++ ){
                                if(Objects.equals(zm.get(j).split(" ")[0], line[i])) {
                                 zm.remove(j);
                                }
                            }    
                        }
                      }
                System.out.println(zm);
                break;               
            }
        }
        
        scan.close();
       
    }
    
}
