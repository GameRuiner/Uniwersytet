// Marko Golovko
// Pracownia PO, piątek, s. 137
// L6, z2, implementacja edytora obiektów
// JFrame 
// klas Main
// 2018-13-05
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		
		JFrame frame = new JFrame("Vehicles");
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		TextField B = new TextField("Brand");
		TextField C = new TextField("Cost");
		TextField Y = new TextField("Year");
        JButton pb2 = new JButton("Save");
        JButton back = new JButton("Back");
        panel.add(back);
        panel.add(pb2);
		panel.add(B);
		panel.add(C);
		panel.add(Y);
		frame.setSize(400, 300);
		
		
		JPanel panel2 = new JPanel();
		
		JButton b1 = new JButton("Car");
		JButton b2 = new JButton("Tram");
		JButton b3 = new JButton("Vehicles");
		JButton read = new JButton("Read");
		panel2.add(read);
		panel2.add(b1);
		panel2.add(b2);
		panel2.add(b3);
		frame.add(panel2);
		panel2.setVisible(true);
		
		JPanel panel3 = new JPanel();
		JButton print = new JButton("Print");
		//JTextArea text = new JTextArea("");
		panel3.add(print);
		panel3.add(back);
		panel3.add(B);
		
		
		b3.addActionListener(new ActionListener()  {
            
            @Override
            public void actionPerformed(ActionEvent e) {   
            panel2.setVisible(false);
            frame.add(panel);
            panel.setVisible(true);
            
                     
            pb2.addActionListener(new ActionListener() {
            	 @Override
                 public void actionPerformed(ActionEvent e) {
            
            		 String y = Y.getText(); 
            		 String brand = B.getText();
            		 String cost = C.getText();
            		 
            		 Pojazd pojazd = new Pojazd(brand, Integer.parseInt(y), Integer.parseInt(cost) );
            		 FileOutputStream fos;
					try {
						fos = new FileOutputStream(brand+".out");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
	            		oos.writeObject(pojazd);
	            		 oos.flush();
	            		 oos.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					catch (IOException e2) {
						e2.printStackTrace();
					}
            		
            }});
            
            back.addActionListener(new ActionListener() {
            	@Override
                public void actionPerformed(ActionEvent e) {
            	panel.setVisible(false);
            	panel2.setVisible(true);
            	
            	}});
            
            }});
		read.addActionListener(new ActionListener() {
       	 @Override
         public void actionPerformed(ActionEvent e) {
       		panel2.setVisible(false);
        	panel3.setVisible(true);
       	 }
		});
	}


}
