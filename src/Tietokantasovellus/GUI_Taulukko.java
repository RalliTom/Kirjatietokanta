package Tietokantasovellus;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.FlowLayout;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.event.InputEvent;

public class GUI_Taulukko extends JFrame {

	private JPanel contentPane;
	static JTable table;

	// Sarakkeiden nimet

	String[] sarakkeet = { "Teoksen nimi", "Tekijän nimi", "Julkaisuvuosi", "ISBN" };
	Object[][] data = {};
	private JButton btnLisKirja;
	private JMenuBar menuBar;
	private JMenu mnTiedosto;
	private JMenu mnTietoja;
	private JMenuItem mntmVersio;
	private JMenuItem mntmLisKirja;
	private JMenuItem mntmPivitTaulukko;
	private JMenuItem mntmPoistaRivi;
	private JMenuItem mntmSuljeOhjelma;
	private JButton btnPoistaValitutRivit;
	private JButton btnPivitTaulukko;
	private JButton btnHae;

	// Luodaan ikkuna

	public GUI_Taulukko(Kirja[] kirjahylly) {
		table = new JTable();

		setTitle("Kirjasto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 405);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);

		mntmLisKirja = new JMenuItem("Lis\u00E4\u00E4 kirja");
		mntmLisKirja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmLisKirja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_AddBook.avaaGUI();
			}
		});
		mnTiedosto.add(mntmLisKirja);

		mntmPivitTaulukko = new JMenuItem("P\u00E4ivit\u00E4 taulukko");
		mntmPivitTaulukko.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mntmPivitTaulukko.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kirjastoVirkailija.updateTaulukko();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnTiedosto.add(mntmPivitTaulukko);

		mntmPoistaRivi = new JMenuItem("Poista rivi");
		mntmPoistaRivi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poistaRivi(table);
			}
		});
		mntmPoistaRivi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmPoistaRivi);

		mntmSuljeOhjelma = new JMenuItem("Sulje ohjelma");
		mntmSuljeOhjelma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Sulje ohjelma?", "Sulje",
						JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		mntmSuljeOhjelma.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmSuljeOhjelma);

		mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);

		mntmVersio = new JMenuItem("Versio");
		mntmVersio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInternalMessageDialog(null,
						"Kirjastotietokanta v 1.0\nRakennuttaja Laurea AMK\nRakennustyön suoritti Tommi Ralli ja kumiankka",
						"Kirjastotietokanta v 1.0", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnTietoja.add(mntmVersio);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		table.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane scrollPane = new JScrollPane(table);

		contentPane.add(scrollPane);

		// Taulukon alustaminen

		table.setModel(new DefaultTableModel(data, sarakkeet));
		// lisätään sorttaustoiminto tauluun
		// table.setAutoCreateRowSorter(true);
		// Eipäs lisätty, koska sortattu taulu rikkoo taulun päivittämisen

		// Jos hylly sisältää jotakin, ladataan hyllyn sisältö tauluun
		if (kirjahylly != null)
			lataaTaulukko(kirjahylly);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		btnLisKirja = new JButton("Lis\u00E4\u00E4 kirja");
		btnLisKirja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_AddBook.avaaGUI();
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnLisKirja.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnLisKirja);

		btnPoistaValitutRivit = new JButton("Poista rivi");
		btnPoistaValitutRivit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poistaRivi(table);
			}
		});
		btnPoistaValitutRivit.setToolTipText("Poista valitut rivit taulukosta");
		panel.add(btnPoistaValitutRivit);

		btnPivitTaulukko = new JButton("P\u00E4ivit\u00E4 taulukko");
		btnPivitTaulukko.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kirjastoVirkailija.updateTaulukko();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnHae = new JButton("Hae kirjaa");
		btnHae.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hae();
			}
		});
		panel.add(btnHae);
		panel.add(btnPivitTaulukko);

	}

	public static void clearTable() {
		// tyhjätään taulu
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().removeAllElements();
		table.revalidate();

	}

	// hae-metodi luodaan ponnahdusikkuna, jossa kysellään tiedot.
	// kun löytyy osuma jostakin annetuista hakukentistä, taulussa rivi muuttuu
	// valituksi
	protected void hae() {
		JPanel haku = new JPanel();

		JLabel hknimi = new JLabel("Kirjan nimi: ");
		JTextField hnimi = new JTextField(10);
		haku.add(hknimi);
		haku.add(hnimi);

		JLabel hktekija = new JLabel("Kirjan tekijä: ");
		JTextField htekija = new JTextField(10);
		haku.add(hktekija);
		haku.add(htekija);

		JLabel hkvuosi = new JLabel("Kirjan julkaisuvuosi: ");
		JTextField hvuosi = new JTextField(5);
		haku.add(hkvuosi);
		haku.add(hvuosi);

		int result = JOptionPane.showConfirmDialog(null, haku, "Anna tietoja haettavasta kirjasta",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			// tähän haku ja tulosten läpikäynti
			String nimihaku = hnimi.getText();
			String tekijahaku = htekija.getText();
			String vuosihaku = hvuosi.getText();

			String SQL = "SELECT * FROM kirjat WHERE tekijanNimi LIKE '" + tekijahaku + "' OR teoksenNimi LIKE '"
					+ nimihaku + "' OR julkaisuvuosi LIKE '" + vuosihaku + "'";

			haeDB(SQL);

		}

	}

	private void haeDB(String SQL) {
		// haetaan tietoja verkkotietokannasta
		try {

			String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7311344";
			String USERID = "sql7311344";
			String PASSWORD = "KC7pBSDTCg";
			String tulokset = "";
			// Luodaan yhteys käyttäen edellänmainittuja tietoja
			Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);

			System.out.println("Yhteys tietokantaan on luotu.");

			// Tässä kohtaa voitaisiin alkaa luomaan kyselyitä

			// Suoritetaan kysely ja otetaan tulokset talteen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			// Tulosjoukko on taulukko-tyyppinen rakenne, joka
			// voidaan käydä läpi esim. while silmukalla

			while (rs.next()) {
				tulokset += (rs.getString(1) + "  " + rs.getString(2) + " " + rs.getString(3) + "\n");

			}

			// String tulosArr = Arrays.toString(tulokset);
			rs.last();
			JOptionPane.showMessageDialog(contentPane,
					"Tuloksia palautui: " + rs.getRow() + " riviä.\n\n" + tulokset + "\n");

			// Suljetaan yhteys
			con.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Virhe tietokannan käytössä!\n" + e);
		} // catch
	} // main

	public static void lataaTaulukko(Kirja[] kirjahylly) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (int rivi = 0; rivi < kirjahylly.length; rivi++) {

			if (kirjahylly[rivi] != null) {
				// getterit hakevat tiedot taulukkoon
				String nimi = kirjahylly[rivi].getTeoksenNimi();
				String tekija = kirjahylly[rivi].getTekijä();
				String vuosi = kirjahylly[rivi].getJulkaisuvuosi();
				String ISBN = kirjahylly[rivi].getISBN();

				// Lisää tiedot taulukkoon
				model.addRow(new Object[] { nimi, tekija, "" + vuosi, ISBN });
			}

		}
	}

	static void lisaaTauluun(Kirja uusi) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { uusi.getTeoksenNimi(), uusi.getTekijä(), uusi.getJulkaisuvuosi(), uusi.getISBN() });

	}

	private void poistaRivi(JTable table) {

		int result = JOptionPane.showConfirmDialog(null, "Haluatko varmasti poistaa valitut rivit?", "Poista rivit",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int[] rows = table.getSelectedRows();
			// for-toistolla poistetaan niin monta riviä kuin niitä oli valittuna
			for (int i = 0; i < rows.length; i++) {
				model.removeRow(rows[i] - i);
			}
		}
	}
}
