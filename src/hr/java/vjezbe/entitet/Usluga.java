package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * @author Lovro
 * <p>
 * Predstavlja entitet Usluge koji je definiran naslovom, opisom i cijenom.
 */
public class Usluga extends Artikl {

	private static final long serialVersionUID = 1L;

	/**
	 * Inicijalizira podatak o naslovu, opisu i cijeni usluge.
	 * @param naslov podatak o naslovu usluge
	 * @param opis podatak o opisu usluge
	 * @param cijena podatak o cijeni usluge
	 * @param stanje podatak o stanju usluge
	 */
	public Usluga(Long id, String naslov, String opis, BigDecimal cijena, Stanje stanje) {
		super(id, naslov, opis, cijena, stanje);
	}

	/**
	 * Ispisuje tekst podatke o oglasu usluge.
	 * @return String sa podatcima oglasa usluge
	 */
	@Override
	public String tekstOglasa() {
		return "Naslov usluge: " + this.getNaslov() + "\nOpis usulge: " + this.getOpis() + "\nCijena usluge: "
				+ this.getCijena() + "\nStanje usluge: " + this.getStanje();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return getNaslov() + ", " + getOpis() + ", cijena: " + getCijena().toString() + "kn";
	}
}
