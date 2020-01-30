package hr.java.vjezbe.niti;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatumObjaveNit implements Runnable {

	@Override
	public void run() {

		List<Prodaja> prodaje = new ArrayList<>();
		try {
			prodaje = BazaPodataka.dohvatiProdaje();
		} catch (BazaPodatakaException e) {
			System.out.println(e);
		}
		List<Prodaja> sortedList = prodaje.stream().sorted(Comparator.comparing(Prodaja::getDatumObjave))
				.collect(Collectors.toList());
		Prodaja najnovijaProdaja = sortedList.get(sortedList.size() - 1);
		String formatedDate = najnovijaProdaja.getDatumObjave().format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		String alertText = "Oglas: " + najnovijaProdaja.getArtikl().toString() + "\nProdavatelj: "
				+ najnovijaProdaja.getKorisnik().toString() + "\nDatum objave: " + formatedDate;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(alertText);
		alert.showAndWait();
	}

}
