package hr.java.vjezbe.entitet;

/**
 * @author Lovro
 * <p>
 * Predstavlja entitet privatnog korisnika koji je definiran imenom, prezimenom, emailom i brojem telefona.
 */
public class PrivatniKorisnik extends Korisnik {

	private static final long serialVersionUID = 1L;
	private String ime;
	private String prezime;

	/**
	 * Inicijalizira podatak o eamilu, broju telefona, imenu i prezimenu privatnog korisnika.
	 * @param email podatak o emailu privatnog korisnika
	 * @param telefon podatak o broju telefona privatnog korisnika
	 * @param ime podatak o imenu privatnog korisnika
	 * @param prezime podatak o prezimenu privatnog korisnika
	 */
	public PrivatniKorisnik(Long id, String email, String telefon, String ime, String prezime) {
		super(id, email, telefon);
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
	 * Ispisuje podatke o privatnom korisniku
	 * @return Podatci o privatnom korisniku
	 */
	@Override
	public String dohvatiKontakt() {
		return "Osobni podaci prodavatelja: " + getIme() + " " + getPrezime() + ", mail: " + super.getEmail() + ", tel: " + super.getTelefon();
	}
	
	@Override
	public String toString() {
		return getIme() + ", " + getPrezime() + ", email: " + getEmail() + ", tel: " + getTelefon();
	}

}
