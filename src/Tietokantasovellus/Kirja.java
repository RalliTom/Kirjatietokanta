package Tietokantasovellus;

public class Kirja {
	private String teoksenNimi;
	private String julkaisuvuosi;
	private String tekijä;
	private String ISBN;

	public Kirja() {
		teoksenNimi = "Ei asetettu";
		julkaisuvuosi = "Ei asetettu";
		tekijä = "Ei asetettu";
		ISBN = "Ei asetettu";
	}

	public Kirja(String teoksenNimi, String tekijä, String julkaisuvuosi, String ISBN) {
		this();
		this.teoksenNimi = teoksenNimi;
		this.tekijä = tekijä;
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

	public String getTekijä() {
		return tekijä;
	}

	public void setTekijä(String tekijä) {
		this.tekijä = tekijä;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

}
