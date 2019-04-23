//Karol Mirek
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.*;
//Klasa edytor pojazd�w (JFrame,ActionListener,Serializable)
public class EdytorPojazd extends  JFrame implements ActionListener, Serializable {
	//Tworzymy zmienne 
	//Przyciski
	public JButton ButtonZapisz, ButtonOtworz;
	//Pola
	public JTextField TextMarka,TextRok,TextPredkosc;
	//Obiekt typu Pojazd
	public Pojazd K;
	
	//Metoda otwieraj�ca UI i tworz�ca nowy obiekt 
	public EdytorPojazd(String s)
	{ 
		initUI();  
		K = new Pojazd(); 
		if(s != "")
		{
			File file = new File(s);
			ReadFromFile(file);
		}
	}

	//Metoda czytaj�ca do p�l odpowiednie im warto��i
	public void Reade()
	{
		TextMarka.setText(K.marka);
		TextPredkosc.setText(Integer.toString(K.rok));
		TextRok.setText(Integer.toString(K.predkosc));
	}
	//Metoda kt�ra czyta warto��i z p�l
	public void Writee()
	{
		K = new Pojazd
				(
						TextMarka.getText(),
						Integer.parseInt(TextRok.getText()),
						Integer.parseInt(TextPredkosc.getText())
						);
	}
	
	//Interfejs U�ytkownika
	public final void initUI()
	{
	
		ButtonZapisz = new JButton("Zapisz do pliku");
		ButtonOtworz = new JButton("Otw�rz z pliku");
		
		ButtonZapisz.setBounds(0, 10, 200, 30);
		ButtonOtworz.setBounds(0, 50, 200, 30);
		
		this.setLayout(null);
		
        getContentPane().add(ButtonZapisz);
        getContentPane().add(ButtonOtworz);
        ButtonZapisz.addActionListener(this);
        ButtonOtworz.addActionListener(this);
        
		TextMarka = new JTextField("Model pojazdu ");
		TextMarka.setBounds(200, 10, 200, 30);
		getContentPane().add(TextMarka);
		
		TextRok = new JTextField("Rok produkcji pojazdu ");
		TextRok.setBounds(200, 50, 200, 30);
		getContentPane().add(TextRok);
		
		TextPredkosc = new JTextField("Pr�dko��  pojazdu");
		TextPredkosc.setBounds(200, 90, 200, 30);
		getContentPane().add(TextPredkosc);
		
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	//Zapisywanie pliku
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
	//Wczytywanie z pliku
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
	//Metoda obs�uguj�ca zdarzenia zapisz oraz otw�rz
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		//Obs�uga zdarzenia "zapisz plik"
		if( source == ButtonZapisz )
		{
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog
					(ButtonZapisz) == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				SaveToFile(file);
			}
		}
		//Obs�uga zdarzenia "otw�rz z pliku"
		if( source == ButtonOtworz )
		{
			JFileChooser fileChooser = new JFileChooser();
			
			if (fileChooser.showOpenDialog
					(ButtonOtworz) == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				ReadFromFile(file);
				
			}
		}
	}

}
