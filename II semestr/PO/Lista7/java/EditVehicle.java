// Marko Golovko
// Pracownia PO, piątek, s. 137
// L7 implementacja edytora obiektów
// JFrame 
// class EditVehicle
// 2018-20-05
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.*;

public class EditVehicle extends  JFrame implements ActionListener, Serializable {
	public JTextField TBrand,TYear,TCost;
	public JButton open,save;
	public Vehicle v;
	
	//tworzy nowy obiekt i plik
	public EditVehicle(String s)
	{ 
		initUI();  
		v = new Vehicle(); 
		File file = new File(s);
		ReadFromFile(file);
		
	}

	//czytywanie pól tekstowych
	public void Reade()
	{
		TBrand.setText(v.brand);
		TCost.setText(Integer.toString(v.year));
		TYear.setText(Integer.toString(v.cost));
	}
	
	public final void initUI()
	{
	
		open = new JButton("Open");
		save = new JButton("Save");
		
		save.setBounds(0, 10, 200, 30);
		open.setBounds(0, 50, 200, 30);
		
		this.setLayout(null);
		
        getContentPane().add(save);
        getContentPane().add(open);
        save.addActionListener(this);
        open.addActionListener(this);
        
		TBrand = new JTextField("Vehicle brand ");
		TBrand.setBounds(200, 10, 200, 30);
		getContentPane().add(TBrand);
		
		TYear = new JTextField("Year of vehicle");
		TYear.setBounds(200, 50, 200, 30);
		getContentPane().add(TYear);
		
		TCost = new JTextField("Cost of vehicle");
		TCost.setBounds(200, 90, 200, 30);
		getContentPane().add(TCost);
		
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	//metoda zapisująca wartości do obiektu
	public void SaveToFile(File file)
	{
		try
		{
			PrintWriter zapis = new PrintWriter(file);
			v = new Vehicle
					(TBrand.getText(),
					 Integer.parseInt(TYear.getText()),
					 Integer.parseInt(TCost.getText()));
			zapis.println(v.toString());
			zapis.close();
		}
		catch (FileNotFoundException e1) {}
	}
	
	//czytywanie obiektu z pliku
	public void ReadFromFile(File file)
	{
		try
		{
			Scanner in = new Scanner(file);	
			String A = in.nextLine();
			String B = in.nextLine();
			String C = in.nextLine();
			
			v = new Vehicle(A,Integer.parseInt(C),Integer.parseInt(B));
			Reade();
			in.close();
		}
		catch (FileNotFoundException e1) {}
	}
	
	//obsługa zdarzeń
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if( source == save )
		{
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog
					(save) == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				SaveToFile(file);
			}
		}
		if( source == open )
		{
			JFileChooser fileChooser = new JFileChooser();
			
			if (fileChooser.showOpenDialog
					(open) == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				ReadFromFile(file);
				
			}
		}
	}

}
