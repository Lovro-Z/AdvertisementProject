package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * @author Lovro
 * <p>
 * Predstavlja sucelje unutar kojeg se nalaze metode za izracun grupe osiguranja, cijene osiguranja i snage automobila u kw.
 */
public interface Vozilo {

	/**
	 * Racuna snagu u kilowatima ovisno o konjskim snagama.
	 * @param ks podatak o konjskim snagama vozila
	 * @return Snagu u kilowatima.
	 */
	public default BigDecimal izracunajKw(BigDecimal ks) {
		//double tmp = ks.doubleValue() * 0.745;
		return ks;
	}

	/**
	 * Racuna grupu osiguranja ovisno o snazi vozila.
	 * @return Grupu osiguranja vozila
	 * @throws NemoguceOdreditiGrupuOsiguranjaException baca iznimku u slucaju da ne moze odrediti grupu osiguranja
	 */
	public BigDecimal izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException;

	/**
	 * Racuna cijenu osiguranja vozila ovisno o kojoj grupi osiguranja vozilo pripada.
	 * @return Cijenu osiguranja vozila
	 * @throws NemoguceOdreditiGrupuOsiguranjaException baca iznimku u slucaju da ne moze odrediti grupu osiguranja
	 */
	public default BigDecimal izracunajCijenuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException{
		BigDecimal cijena = new BigDecimal(0);
		switch (this.izracunajGrupuOsiguranja().intValue()) {
		case 150:
			cijena = new BigDecimal(1500);
			break;
		case 190:
			cijena = new BigDecimal(1500);
			break;
		case 345:
			cijena = new BigDecimal(3000);
			break;
		case 445:
			cijena = new BigDecimal(4000);
			break;
		case 545:
			cijena = new BigDecimal(5000);
			break;
		}

		return cijena;
	}
}
