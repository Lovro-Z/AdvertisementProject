package hr.java.vjezbe.iznimke;

/**
 * @author Lovro
 * <p>
 * Klasa predstavlja iznimku koja se javlja u slucaju da nije bilo moguce odrediti grupu osiguranja za vozilo.
 *
 */
public class NemoguceOdreditiGrupuOsiguranjaException extends Exception {

	private static final long serialVersionUID = -212880976145839358L;

	/**
	 * Konstruktor koji nadklasi prosljeduje prilagodenu poruku za ispis korisniku o tome zasto se javila ta iznimka.
	 */
	public NemoguceOdreditiGrupuOsiguranjaException() {
		System.out.println("Dogodila se pogreska!");
	}

	/**
	 * Konstruktor koji nadklasi prosljeduje poruku za ispis korisniku.
	 * @param message predstavlja objekt tipa String koji sadrzi poruku za korisnika u slucaju iznimke
	 */
	public NemoguceOdreditiGrupuOsiguranjaException(String message) {
		super(message);
	}

	/**
	 * Konstruktor koji nadklasi prosljeduje uzrok i informacije o iznimci 
	 * @param cause predstavlja objekt koji sadrzi informacije o iznimci
	 */
	public NemoguceOdreditiGrupuOsiguranjaException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Konstruktor koji nadklasi prosljeduje poruku za ispis korisniku te uzrok i informacije o iznimci 
	 * @param message predstavlja objekt tipa String koji sadrzi poruku za korisnika u slucaju iznimke
	 * @param cause predstavlja objekt koji sadrzi informacije o iznimci
	 */
	public NemoguceOdreditiGrupuOsiguranjaException(String message, Throwable cause) {
		super(message, cause);
	}	

}
