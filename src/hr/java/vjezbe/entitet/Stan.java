package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.tools.javac.Main;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * @author Lovro
 * <p>
 * Predstavlja entitet stana koji je definiran kvadraturom, naslovom, opisom i cijenom.
 *
 */
public class Stan extends Artikl implements Nekretnina {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private Integer kvadratura;
	
	/**
	 * Inicijalizira podatak o naslovu, opisu, cijeni i kvadraturi stana.
	 * @param naslov podatak o naslovu stana
	 * @param opis podatak o opisu stana
	 * @param cijena podatak o cijeni stana
	 * @param kvadratura podatak o kvadraturi stana
	 * @param stanje podatak o stanju stana
	 */
	public Stan(Long id, String naslov, String opis, BigDecimal cijena, Stanje stanje, Integer kvadratura) {
		super(id, naslov, opis, cijena, stanje);
		this.kvadratura = kvadratura;
	}

	public Integer getKvadratura() {
		return kvadratura;
	}

	public void setKvadratura(Integer kvadratura) {
		this.kvadratura = kvadratura;
	}

	/**
	 * Ispisuje tekst koji opisuje podatke o oglasu stana.
	 * @return String sa podatcima oglasa stana.
	 */
	@Override
	public String tekstOglasa() {
		BigDecimal porez;
		try {
			porez = this.izracunajPorez(super.getCijena());
		} catch (CijenaJePreniskaException e) {
			logger.error("Pogreška prilikom određivanja iznosa poreza!", e);
			return "Naslov nekretnine: " + super.getNaslov() + "\nOpis nekretnine: " + super.getOpis()
					+ "\nKvadratura nekretnine: " + this.getKvadratura() + "\nStanje nekretnine: " + this.getStanje()
					+ "\nPorez na nekretnine: " + e.getMessage()
					+ "\nCijena nekretnine: " + super.getCijena();
		}
		return "Naslov nekretnine: " + super.getNaslov() + "\nOpis nekretnine: " + super.getOpis()
		+ "\nKvadratura nekretnine: " + this.getKvadratura() + "\nStanje nekretnine: " + this.getStanje()
		+ "\nPorez na nekretnine: " + porez
		+ "\nCijena nekretnine: " + super.getCijena();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Stan stan = (Stan) o;
		return Objects.equals(kvadratura, stan.kvadratura);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return getNaslov() + ", " + getOpis() + ", " + getKvadratura().toString() + "m2, " + getCijena().toString() + "kn, stanje: " + getStanje().toString();
	}
}
