package Tietokantasovellus;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class GUI_AddBook extends JFrame {
// Kapselointi
	static GUI_AddBook frame;
	private JPanel contentPane;
	private JTextField nimiTF;
	private JTextField tekijaTF;
	private JTextField vuosiTF;

// Kenttien aksessorit
	public String getNimiTF() {
		String nimi = nimiTF.getText();
		return nimi;
	}

	public void setNimiTF(JTextField nimiTF) {
		this.nimiTF = nimiTF;
	}

	public String getTekija() {
		String uusiTekija = tekijaTF.getText();
		return uusiTekija;
	}

	public void setTekijaTF(JTextField tekijaTF) {
		this.tekijaTF = tekijaTF;
	}

	public String getVuosiTF() {
		String uusiVuosi = vuosiTF.getText();
		return uusiVuosi;
	}

	public void setVuosiTF(JTextField vuosiTF) {
		this.vuosiTF = vuosiTF;
	}

// Metodi kutsutaan aloitussivulta, avaa lis‰ysikkunan
	public static void avaaGUI() {
		GUI_AddBook frame = new GUI_AddBook();
		frame.setVisible(true);
	}

// Luodaan lis‰ysikkunan frame, kent‰t, painikkeet ja kuuntelijat
	public GUI_AddBook() {
		setResizable(false);
		setTitle("Lis\u00E4\u00E4 kirja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 268);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);

		// Kirjan lis‰ys menun kautta
		JMenuItem mntmLisKirja = new JMenuItem("Lis\u00E4\u00E4 kirja");
		mntmLisKirja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmLisKirja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uusiNimi = getNimiTF();
				String uusiTekija = getTekija();
				String uusiVuosi = getVuosiTF();
				if (nimiTF.getText().equals("") || tekijaTF.getText().equals("") || vuosiTF.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Pakollinen tieto puuttuu.", "Virhe",
							JOptionPane.ERROR_MESSAGE);
				} else

					try {
						lisaaKirja(uusiNimi, uusiTekija, uusiVuosi);
						nimiTF.setText("");
						tekijaTF.setText("");
						vuosiTF.setText("");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		mnTiedosto.add(mntmLisKirja);

		// Sulje ikkuna ei sulje koko ohjelmaa, vaan sulkee AddBook-framen
		JMenuItem mntmSuljeIkkuna = new JMenuItem("Sulje ikkuna");
		mntmSuljeIkkuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mntmSuljeIkkuna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmSuljeIkkuna);

		JMenu mnApua = new JMenu("Apua");
		menuBar.add(mnApua);

		JMenuItem mntmApu = new JMenuItem("Ohjeet");
		mntmApu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Help.avaaHelp();
			}
		});
		mnApua.add(mntmApu);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Kirjan nimi: ");
		lblNewLabel.setBounds(19, 63, 60, 14);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);

		nimiTF = new JTextField();
		nimiTF.setBounds(108, 60, 96, 20);
		nimiTF.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(nimiTF);
		nimiTF.setColumns(10);

		tekijaTF = new JTextField();
		tekijaTF.setBounds(108, 85, 96, 20);
		tekijaTF.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(tekijaTF);
		tekijaTF.setColumns(10);

		JLabel lblJulkaisuvuosi = new JLabel("Julkaisuvuosi: ");
		lblJulkaisuvuosi.setBounds(19, 113, 71, 14);
		lblJulkaisuvuosi.setVerticalAlignment(SwingConstants.TOP);
		lblJulkaisuvuosi.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblJulkaisuvuosi);

		JLabel lblKirjanTekij = new JLabel("Kirjan tekij\u00E4: ");
		lblKirjanTekij.setBounds(19, 88, 67, 14);
		lblKirjanTekij.setHorizontalAlignment(SwingConstants.LEFT);
		lblKirjanTekij.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblKirjanTekij);

		vuosiTF = new JTextField();
		vuosiTF.setBounds(108, 110, 96, 20);
		vuosiTF.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(vuosiTF);
		vuosiTF.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(29, Short.MAX_VALUE)));

		JButton btnLis = new JButton("Lis\u00E4\u00E4");
		btnLis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String uusiNimi = getNimiTF();
				String uusiTekija = getTekija();
				String uusiVuosi = getVuosiTF();
				if (nimiTF.getText().equals("") || tekijaTF.getText().equals("") || vuosiTF.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Pakollinen tieto puuttuu.", "Virhe",
							JOptionPane.ERROR_MESSAGE);
				} else

					try {
						lisaaKirja(uusiNimi, uusiTekija, uusiVuosi);
						nimiTF.setText("");
						tekijaTF.setText("");
						vuosiTF.setText("");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnLis.setBounds(10, 165, 89, 23);
		panel.add(btnLis);

		JButton btnTyhjenn = new JButton("Tyhjenn\u00E4");
		btnTyhjenn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				nimiTF.setText("");
				tekijaTF.setText("");
				vuosiTF.setText("");
			}
		});
		btnTyhjenn.setBounds(108, 165, 89, 23);
		panel.add(btnTyhjenn);

		JButton btnSuljeIkkuna = new JButton("Sulje");
		btnSuljeIkkuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSuljeIkkuna.setBounds(327, 165, 89, 23);
		panel.add(btnSuljeIkkuna);

		JTextPane txtpnLisKirjaTietokantaan = new JTextPane();
		txtpnLisKirjaTietokantaan.setEditable(false);
		txtpnLisKirjaTietokantaan.setBackground(UIManager.getColor("Button.background"));
		txtpnLisKirjaTietokantaan.setText(
				"Lis\u00E4\u00E4 kirja tietokantaan kirjoittamalla vaaditut tiedot kenttiin ja painamalla nappia 'Lis\u00E4\u00E4'.");
		txtpnLisKirjaTietokantaan.setBounds(8, 11, 408, 41);
		panel.add(txtpnLisKirjaTietokantaan);
		contentPane.setLayout(gl_contentPane);

	}

	// Metodi lis‰‰ kirjan SQL-tietokantaan
	public static void lisaaKirja(String uusiNimi, String uusiTekija, String uusiVuosi) throws SQLException {
		// Luodaan uusi Kirja-olio
		Kirja uusi;

		int result = JOptionPane.showConfirmDialog(frame, "Haluatko lis‰t‰ kirjan arkistoon?", "Lis‰‰ kirja",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

			uusi = new Kirja(uusiNimi, uusiTekija, uusiVuosi, null);

			// Virkailija tallentaa kirjan ja p‰ivitt‰‰ taulukkosivun
			kirjastoVirkailija.tallennaKirja(uusi);
			// kirjastoVirkailija.haeDB();
			GUI_Taulukko.lisaaTauluun(uusi);
			kirjastoVirkailija.updateTaulukko();
		}

	}

}
