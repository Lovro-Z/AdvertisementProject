package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Lovro
 *         <p>
 *         Predstavlja entitet Kategorije koji je definiran nazivom i poljem
 *         artikala.
 */
public class Kategorija<T extends Artikl> extends Entitet{
	
	private static final long serialVersionUID = 1L;
	private String naziv;
	private List<T> artikli = new ArrayList<>();

	/**
	 * Inicijalizira podatak o nazivu i artiklima.
	 * 
	 * @param naziv podatak o nazivu kategorije
	 * @param artikli podatak o artiklima kategorije
	 */

	public Kategorija(Long id, String naziv, List<T> artikli) {
		super(id);
		this.naziv = naziv;
		this.artikli = new ArrayList<>();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public void dodajArtikl(T artikl) {
		artikli.add(artikl);
	}

	public T dohvatiArtikl(int index) {
		return artikli.get(index);
	}

	public List<T> getArtikli() {
		return artikli;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Kategorija<?> that = (Kategorija<?>) o;
		return Objects.equals(naziv, that.naziv) && Objects.equals(artikli, that.artikli);
	}

	@Override
	public int hashCode() {
		return Objects.hash(naziv);
	}
	
	@Override
	public String toString() {
		return super.getId() + "\n" + getNaziv();
	}
}
