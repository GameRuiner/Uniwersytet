// Marko Golovko
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;
import javax.swing.*;


import javax.swing.*;
public class EditTram extends  JFrame implements ActionListener, Serializable {
	public JButton ButtonSave, ButtonOpen;
	public JTextField TextBrand,TextYear,TextCost;
	public Vehicle V;

	//tworzy nowy obiekt i plik
	public EditTram(String s)
	{ 
		initUI();  
		V = new Tram(); 
		
		File file = new File(s);
		ReadFromFile(file);
	}

	//czytywanie pól tekstowych
	public void Reade()
	{
		TextBrand.setText(V.brand);
		TextCost.setText(Integer.toString(V.year));
		TextYear.setText(Integer.toString(V.cost));
	}
	
	//tworzy Ui interface 
	public final void initUI()
	{
		ButtonSave = new JButton("Save");
		ButtonOpen = new JButton("Open");

		ButtonSave.setBounds(0, 10, 200, 30);
		ButtonOpen.setBounds(0, 50, 200, 30);
		
		this.setLayout(null);
		
        getContentPane().add(ButtonSave);
        getContentPane().add(ButtonOpen);
        ButtonSave.addActionListener(this);
        ButtonOpen.addActionListener(this);
        
		TextBrand = new JTextField("Tram brand");
		TextBrand.setBounds(200, 10, 200, 30);
		getContentPane().add(TextBrand);
		
		TextYear = new JTextField("Year of tram");
		TextYear.setBounds(200, 50, 200, 30);
		getContentPane().add(TextYear);
		
		
		TextCost = new JTextField("Cost of tram");
		TextCost.setBounds(200, 90, 200, 30);
		getContentPane().add(TextCost);
		
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
			V = new Vehicle
			(TextBrand.getText(),Integer.parseInt(TextYear.getText()),Integer.parseInt(TextCost.getText()));
			zapis.println(V.toString());
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
			
			V = new Vehicle(A,Integer.parseInt(C),Integer.parseInt(B));
			Reade();
			in.close();
		}
		catch (FileNotFoundException e1) {}
	}
	
	//obsługa zdarzeń
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if( source == ButtonSave )
		{
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(ButtonSave) == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				SaveToFile(file);
			}
		}
		
		if( source == ButtonOpen )
		{
			JFileChooser fileChooser = new JFileChooser();
			
			if (fileChooser.showOpenDialog(ButtonOpen) == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				ReadFromFile(file);
				
			}
		}
	}

}
