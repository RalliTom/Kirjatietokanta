package Tietokantasovellus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class kirjastoVirkailija {

	static final String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7311344";
	static final String USERID = "sql7311344";
	static final String PASSWORD = "KC7pBSDTCg";

	public static Kirja[] haeDB() {

		Statement stmt = null;
		Connection con = null;

		Kirja[] hylly = null;

		try {

			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			// SQL kysely
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM kirjat");

			int i = 0;
			hylly = new Kirja[100];
//
			while (rs.next()) {
				System.out.println(
						rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4));

				hylly[i] = new Kirja(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				i++;
			}
			// Otetaan mahdolliset virheet kiinni
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return hylly;

	}

	public static void updateTaulukko() throws SQLException {
		Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
		Kirja[] hylly = haeDB();
		Statement stmt = con.createStatement();
		// haetaan kaikki tieto tietokannasta 'kirjat'
		ResultSet rs = stmt.executeQuery("SELECT * FROM kirjat");
		GUI_Taulukko.clearTable();
		int i = 0;
		hylly = new Kirja[100];
		// jatketaan tulosten hyllyyn sijoittamista niin kauan kuin tietokanta sis‰lt‰‰
		// seuraavan alkion
		while (rs.next()) {
			// tulostetaan konsoliin
			System.out
					.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4));
			// t‰ytet‰‰n taulukkomuuttuja hylly
			hylly[i] = new Kirja(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			i++;
		}
		// p‰ivitet‰‰n JTable
		
		GUI_Taulukko.lataaTaulukko(hylly);
	}

	public static void tallennaKirja(Kirja uusi) {

		String Nimi = uusi.getTeoksenNimi();
		String Tekija = uusi.getTekij‰();
		String Vuosi = uusi.getJulkaisuvuosi();
		String ISBN = uusi.getISBN();

		try {
			Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
			String sql = "INSERT INTO kirjat (teoksenNimi, tekijanNimi, julkaisuvuosi, isbn) values (?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(sql);

			preparedStmt.setString(1, Nimi);
			preparedStmt.setString(2, Tekija);
			preparedStmt.setString(3, Vuosi);
			preparedStmt.setString(4, ISBN);

			preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
