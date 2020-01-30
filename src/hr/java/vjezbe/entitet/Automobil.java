package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.tools.javac.Main;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * @author Lovro
 * <p>
 * Predstavlja entitet automobila koji je definiran naslovom, opisom, cijenom i snagom.
 *
 */
public class Automobil extends Artikl implements Vozilo {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private BigDecimal snagaKs;
	boolean loop = false;

	/**
	 * Inicijalizira podatak o snazi te prosljeduje podatke o naslovu, opisu i cijeni automobila nadklasi.
	 * @param naslov podatak o naslovu automobila 
	 * @param opis podataka o opisu automobila
	 * @param cijena podataka o cijeni automobila
	 * @param snagaKs podataka o snazi automobila
	 * @param stanje podataka o stanju automobila
	 */
	public Automobil(Long id, String naslov, String opis, BigDecimal cijena, Stanje stanje, BigDecimal snagaKs) {
		super(id, naslov, opis, cijena, stanje);
		this.snagaKs = snagaKs;
	}

	public BigDecimal getSnagaKs() {
		return snagaKs;
	}

	public void setSnagaKs(BigDecimal snagaKs) {
		this.snagaKs = snagaKs;
	}

	/**
	 * Odreduje grupu osiguranja na temelju snage automobila izrazene u Kw.
	 * @return Grupu osiguranja
	 */
	@Override
	public BigDecimal izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {
		if(izracunajKw(snagaKs).doubleValue() > 600) {
			throw new NemoguceOdreditiGrupuOsiguranjaException("Previše kw, ne mogu odrediti grupu osiguranja.");
		}
		return snagaKs;
	}

	/**
	 *	Ispisuje tekst sa podacima o oglasu automobila.
	 *	@return String sa podatcima oglasa automobila
	 */
	@Override
	public String tekstOglasa() {
		BigDecimal cijena;
		try {
			cijena = this.izracunajCijenuOsiguranja();
		} catch (NemoguceOdreditiGrupuOsiguranjaException e) {
			logger.error("Pogreška prilikom određivanja cijene osiguranja!", e);
			return "Naslov automobila: " + this.getNaslov() + "\nOpis automobila: " + this.getOpis()
					+ "\nSnaga automobila: " + this.getSnagaKs() + "\nStanje autmobila: " + this.getStanje() + "\nIzračun osiguranja automobila: "
					+ e.getMessage() + "\nCijena automobila: " + this.getCijena();
		}
		return "Naslov automobila: " + this.getNaslov() + "\nOpis automobila: " + this.getOpis()
				+ "\nSnaga automobila: " + this.getSnagaKs() + "\nStanje autmobila: " + this.getStanje() + "\nIzračun osiguranja automobila: "
				+ cijena + "\nCijena automobila: " + this.getCijena();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Automobil automobil = (Automobil) o;
		return loop == automobil.loop &&
				Objects.equals(snagaKs, automobil.snagaKs);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return getNaslov() + ", " + getOpis() + ", snaga:" + getSnagaKs().toString() + ", cijena: " + getCijena().toString() + "kn, stanje:" + getStanje().toString();
	}
}
