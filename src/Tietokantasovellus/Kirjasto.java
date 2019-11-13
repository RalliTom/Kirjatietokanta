package Tietokantasovellus;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;


public class Kirjasto {

	// Alustetaan taulukko
	static Kirja kirjaHylly[];

	// Ohjelman p��metodi
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		

		// Ladataan kirjat tietokannasta ja sijoitetaan kirjaHylly -muuttujaan
		kirjaHylly = kirjastoVirkailija.haeDB();

		// Uutta ikkunaa piirrett�ess�, v�litet��n kirjahyllyn sis�lt� mukana
		JFrame ikkuna = new GUI_Taulukko( kirjaHylly );
		ikkuna.getContentPane().setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		ikkuna.setVisible(true);
		

	}

}