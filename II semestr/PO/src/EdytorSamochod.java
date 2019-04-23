//Karol Mirek
//Pracownia PO, czwartek, s.108
//L7, z1, Swing
//Swing
//Edytor Samochodu
//2018-04-11 
//Java
//Eclipse

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.*;
public class EdytorSamochod extends  JFrame implements ActionListener, Serializable {
	public JButton ButtonZapisz, ButtonOtworz;
	public JTextField TextMarka,TextRok,TextPredkosc;
	public Pojazd K;

	public EdytorSamochod(String s)
	{ 
		initUI();  
		K = new Samochod(); 
		
		if(s != "")
		{
			File file = new File(s);
			ReadFromFile(file);
		}
	}

	public void Reade()
	{
		TextMarka.setText(K.marka);
		TextPredkosc.setText(Integer.toString(K.rok));
		TextRok.setText(Integer.toString(K.predkosc));
	}
	
	public void Writee()
	{
		K = new Pojazd
				(TextMarka.getText(),Integer.parseInt(TextRok.getText()),Integer.parseInt(TextPredkosc.getText()));
	}
	
	public final void initUI()
	{
		ButtonZapisz = new JButton("Zapisz do pliku");
		ButtonOtworz = new JButton("Otwórz z pliku");

		ButtonZapisz.setBounds(0, 10, 200, 30);
		ButtonOtworz.setBounds(0, 50, 200, 30);
		
		this.setLayout(null);
		
        getContentPane().add(ButtonZapisz);
        getContentPane().add(ButtonOtworz);
        ButtonZapisz.addActionListener(this);
        ButtonOtworz.addActionListener(this);
        
		TextMarka = new JTextField("Model samochodu");
		TextMarka.setBounds(200, 10, 200, 30);
		getContentPane().add(TextMarka);
		
		TextRok = new JTextField("Rok produkcji samochodu");
		TextRok.setBounds(200, 50, 200, 30);
		getContentPane().add(TextRok);
		
		
		TextPredkosc = new JTextField("Prêdkoœæ samochodu");
		TextPredkosc.setBounds(200, 90, 200, 30);
		getContentPane().add(TextPredkosc);
		
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	public void SaveToFile(File file)
	{
		try
		{
			PrintWriter zapis = new PrintWriter(file);
			Writee();
			zapis.println(K.toString());
			zapis.close();
		}
		catch (FileNotFoundException e1) {}
	}
	
	public void ReadFromFile(File file)
	{
		try
		{
			Scanner in = new Scanner(file);
			
			String A = in.nextLine();
			String B = in.nextLine();
			String C = in.nextLine();
			
			K = new Pojazd(A,Integer.parseInt(C),Integer.parseInt(B));
			Reade();
			in.close();
		}
		catch (FileNotFoundException e1) {}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if( source == ButtonZapisz )
		{
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(ButtonZapisz) == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				SaveToFile(file);
			}
		}
		
		if( source == ButtonOtworz )
		{
			JFileChooser fileChooser = new JFileChooser();
			
			if (fileChooser.showOpenDialog(ButtonOtworz) == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				ReadFromFile(file);
				
			}
		}
	}

}
