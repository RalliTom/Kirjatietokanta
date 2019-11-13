package Tietokantasovellus;

public class Kirja {
	private String teoksenNimi;
	private String julkaisuvuosi;
	private String tekij�;
	private String ISBN;

	public Kirja() {
		teoksenNimi = "Ei asetettu";
		julkaisuvuosi = "Ei asetettu";
		tekij� = "Ei asetettu";
		ISBN = "Ei asetettu";
	}

	public Kirja(String teoksenNimi, String tekij�, String julkaisuvuosi, String ISBN) {
		this();
		this.teoksenNimi = teoksenNimi;
		this.tekij� = tekij�;
		this.julkaisuvuosi = julkaisuvuosi;
		this.ISBN = ISBN;
	}

	public String getTeoksenNimi() {
		return teoksenNimi;
	}

	public void setTeoksenNimi(String teoksenNimi) {
		this.teoksenNimi = teoksenNimi;
	}

	public String getJulkaisuvuosi() {
		return julkaisuvuosi;
	}

	public void setJulkaisuvuosi(String julkaisuvuosi) {
		this.julkaisuvuosi = julkaisuvuosi;
	}

	public String getTekij�() {
		return tekij�;
	}

	public void setTekij�(String tekij�) {
		this.tekij� = tekij�;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

}
