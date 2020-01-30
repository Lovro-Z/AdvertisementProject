package hr.java.vjezbe.entitet;

/**
 * @author Lovro
 * <p>
 * Predstavlja entitet poslovnog korisnika koji je definiran nazivom, webom, emailom i brojem telefona.
 */
public class PoslovniKorisnik extends Korisnik {

	private static final long serialVersionUID = 1L;
	private String naziv;
	private String web;

	/**
	 * Inicijalizira podatak o emailu, broju telefona, nazivu i webu poslovnog korisnika.
	 * @param email podatak o emailu poslovnog korisnika
	 * @param telefon podatak o broju telefona poslovnog korisnika
	 * @param naziv podatak o nazivu poslovnog korisnika
	 * @param web podatak o webu poslovnog korisnika
	 */
	public PoslovniKorisnik(Long id, String email, String telefon, String naziv, String web) {
		super(id, email, telefon);
		this.naziv = naziv;
		this.web = web;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	/**
	 * Ispisuje podatke o poslovnom korisniku
	 * @return Podatci o poslovnom korisniku
	 */
	@Override
	public String dohvatiKontakt() {
		return "Naziv tvrtke: " + getNaziv() + ", mail: " + getEmail() + ", tel: " + getTelefon() + ", web: " + getWeb();
	}
	@Override
	public String toString() {
		return getNaziv() + ", email: " + getEmail() + ", web: " + getWeb() + ", tel: " + getTelefon();
	}

}
