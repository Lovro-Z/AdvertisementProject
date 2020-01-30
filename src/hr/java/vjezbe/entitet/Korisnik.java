package hr.java.vjezbe.entitet;

/**
 * @author Lovro
 *	<p>
 *	Predstavlja entitet korisnika koji je definiran emailom i brojem telefona.
 */
public abstract class Korisnik extends Entitet{

	private static final long serialVersionUID = 1L;
	private String email;
	private String telefon;

	/**
	 * Inicijalizira podatak o emailu i broju telefona korisnika.
	 * @param email podatak o emailu korisnika
 	 * @param telefon podatak o telefonu korisnika
	 */
	public Korisnik(Long id, String email, String telefon) {
		super(id);
		this.email = email;
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	/**
	 * Nasljeduje se u podklasama koje nasljeduju klasu Korisnik te ispisuje podatke o korisniku
	 * @return Podatke o korisniku
	 */
	public abstract String dohvatiKontakt();
}
