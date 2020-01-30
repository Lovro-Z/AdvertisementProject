package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * @author Lovro
 * <p> 
 * Predstavlja sucelje unutar kojeg se nalazi metoda za izracun poreza nekretnine.
 */
public interface Nekretnina {
	
	/**
	 * Racuna porez nekretnine ovisno o cijeni nekretnine
	 * @param cijenaNekretnine podatak o cijeni nekretnine
	 * @return Porez nekretnine
	 */
	public default BigDecimal izracunajPorez(BigDecimal cijenaNekretnine){
		if(cijenaNekretnine.intValue() < 100000) {
			throw new CijenaJePreniskaException("Cijena ne smije biti manja od 10000kn");
		}
		return new BigDecimal(0.03 * cijenaNekretnine.doubleValue());
	}

}
