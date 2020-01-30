package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Lovro
 * <p>
 * Predstavlja entitet prodaje koji je definiran artiklom, korisnikom i datumom objave.
 */
public class Prodaja extends Entitet implements Serializable{

	private static final long serialVersionUID = 1L;
	private Artikl artikl;
	private Korisnik korisnik;
	private LocalDate datumObjave;
	private static Long id = Long.valueOf(0);

	/**
	 * Inicijalizira podatak o artiklu, korisniku i datumu objave.
	 * @param artikl podatak o artiklu prodaje
	 * @param korisnik podatak o korisniku prodaje
	 * @param datumObjave podatak o datumu objave prodaje
	 */
	public Prodaja(Artikl artikl, Korisnik korisnik, LocalDate datumObjave) {
		super(++id);
		this.artikl = artikl;
		this.korisnik = korisnik;
		this.datumObjave = datumObjave;
	}

	public Artikl getArtikl() {
		return artikl;
	}

	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public LocalDate getDatumObjave() {
		return datumObjave;
	}

	public void setDatumObjave(LocalDate datumObjave) {
		this.datumObjave = datumObjave;
	}
	
	@Override
	public String toString() {
		return super.getId() + "\n" + artikl.tekstOglasa() + "\n" + korisnik.dohvatiKontakt();
	}

}
