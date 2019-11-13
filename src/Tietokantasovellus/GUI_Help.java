package Tietokantasovellus;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.ComponentOrientation;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Help extends JFrame {

	public static void avaaHelp() {
		GUI_Help frame = new GUI_Help();
		frame.setVisible(true);
		
	}
		
	public GUI_Help() {
		setResizable(false);
		setBounds(new Rectangle(250, 250, 384, 152));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Ohje kirjan lis\u00E4\u00E4miseen");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JTextPane txtpnLisKirjaTietokantaan = new JTextPane();
		txtpnLisKirjaTietokantaan.setEditable(false);
		txtpnLisKirjaTietokantaan.setMargin(new Insets(10, 10, 1, 10));
		txtpnLisKirjaTietokantaan.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtpnLisKirjaTietokantaan.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txtpnLisKirjaTietokantaan.setBounds(new Rectangle(10, 10, 10, 10));
		txtpnLisKirjaTietokantaan.setBackground(UIManager.getColor("Button.background"));
		txtpnLisKirjaTietokantaan.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtpnLisKirjaTietokantaan.setText("1. Lis\u00E4\u00E4 kirja tietokantaan kirjoittamalla vaaditut tiedot kenttiin\r\n    Jos tietoja puuttuu, saat virheilmoituksen\r\n2. Paina nappia 'lis\u00E4\u00E4' \r\n3. Vastaa ponnahdusikkunan kysymykseen 'Ok'.\r\n\r\nT\u00E4m\u00E4n j\u00E4lkeen kirjasi tiedot l\u00F6ytyv\u00E4t tietokannasta.");
		panel.add(txtpnLisKirjaTietokantaan);
		
		Panel panel_1 = new Panel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		Button button = new Button("Sulje");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBackground(UIManager.getColor("Button.light"));
		panel_1.add(button);

	

	}
}
