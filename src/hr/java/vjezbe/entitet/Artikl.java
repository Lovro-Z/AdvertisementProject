package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Lovro
 *         <p>
 *         Predstavlja entitet artikla koji je definiran naslovom, opisom i
 *         cijenom.
 */
public abstract class Artikl extends Entitet {

	private static final long serialVersionUID = 1L;
	private String naslov;
	private String opis;
	private BigDecimal cijena;
	private Stanje stanje;

	/**
	 * Inicijalizira podatke o naslovu, opisu i cijeni artikla
	 * 
	 * @param naslov podatak o naslovu
	 * @param opis   podatak o opisu
	 * @param cijena podatak o cijeni
	 * @param stanje podataka o stanju
	 */
	public Artikl(Long id, String naslov, String opis, BigDecimal cijena, Stanje stanje) {
		super(id);
		this.naslov = naslov;
		this.opis = opis;
		this.cijena = cijena;
		this.stanje = stanje;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public BigDecimal getCijena() {
		return cijena;
	}

	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}

	public Stanje getStanje() {
		return stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	/**
	 * Nasljeduje se u podklasama koje nasljeduju klasu Artikl te ispisuje podatke o
	 * oglasu.
	 * 
	 * @return Podatke o oglasu
	 */
	public abstract String tekstOglasa();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Artikl artikl = (Artikl) o;
		return Objects.equals(naslov, artikl.naslov) && Objects.equals(opis, artikl.opis)
				&& Objects.equals(cijena, artikl.cijena) && stanje == artikl.stanje;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}