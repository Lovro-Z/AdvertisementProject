package hr.java.vjezbe.iznimke;

/**
 * @author Lovro
 * <p>
 * Klasa predstavlja iznimku koja se javlja u slucaju da je korisnik unio premalu cijenu.
 *
 */
public class CijenaJePreniskaException extends RuntimeException {

	private static final long serialVersionUID = -7966744782829041743L;

	/**
	 * Konstruktor koji nadklasi prosljeduje prilagodenu poruku za ispis korisniku o tome zasto se javila ta iznimka.
	 */
	public CijenaJePreniskaException() {
		super("Cijena ne smije biti manja od 10000kn");
	}

	/**
	 * Konstruktor koji nadklasi prosljeduje poruku za ispis korisniku.
	 * @param message predstavlja objekt tipa String koji sadrzi poruku za korisnika u slucaju iznimke
	 */
	public CijenaJePreniskaException(String message) {
		super(message);
	}

	/**
	 * Konstruktor koji nadklasi prosljeduje uzrok i informacije o iznimci 
	 * @param cause predstavlja objekt koji sadrzi informacije o iznimci
	 */
	public CijenaJePreniskaException(Throwable cause) {
		super(cause);
	}

	/**
	 * Konstruktor koji nadklasi prosljeduje poruku za ispis korisniku te uzrok i informacije o iznimci 
	 * @param message predstavlja objekt tipa String koji sadrzi poruku za korisnika u slucaju iznimke
	 * @param cause predstavlja objekt koji sadrzi informacije o iznimci
	 */
	public CijenaJePreniskaException(String message, Throwable cause) {
		super(message, cause);
	}
}
