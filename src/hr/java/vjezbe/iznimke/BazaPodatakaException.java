package hr.java.vjezbe.iznimke;

public class BazaPodatakaException extends Exception {

	private static final long serialVersionUID = -3764446744595660790L;

	public BazaPodatakaException() {
		super("Došlo je do pogreške u radu s bazom podataka");
	}

	public BazaPodatakaException(String message) {
		super(message);
	}

	public BazaPodatakaException(Throwable cause) {
		super(cause);
	}

	public BazaPodatakaException(String message, Throwable cause) {
		super(message, cause);
	}

}
