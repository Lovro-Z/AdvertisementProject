package hr.java.vjezbe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class UnosProdajaController {

	@FXML
	private ComboBox<Artikl> artiklComboBox;
	@FXML
	private ComboBox<Korisnik> korisnikComboBox;
	@FXML
	private DatePicker datumDatePicker;

	public void initialize() {

		datumDatePicker.setConverter(new StringConverter<LocalDate>() {

			private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

			@Override
			public String toString(LocalDate object) {
				if (object == null)
					return "";
				return dateTimeFormatter.format(object);
			}

			@Override
			public LocalDate fromString(String string) {
				if (string == null || string.trim().isEmpty())
					return null;
				return LocalDate.parse(string, dateTimeFormatter);
			}
		});
		artiklComboBox.setItems(FXCollections.observableArrayList(BazaPodataka.dohvatiArtikle()));
		korisnikComboBox.setItems(FXCollections.observableArrayList(BazaPodataka.dohvatiKorisnike()));
	}

	@FXML
	public void unesiProdaju() {
		boolean loop = false;
		String errorText = "";
		if (artiklComboBox.getSelectionModel().getSelectedItem() == null)
			errorText += "Morate odabrati artikl!" + System.lineSeparator();
		if (korisnikComboBox.getSelectionModel().getSelectedItem() == null)
			errorText += "Morate odabrati korisnika!" + System.lineSeparator();
		if (datumDatePicker.getValue() == null)
			errorText += "Morate odabrati datum!" + System.lineSeparator();
		if (errorText.equals(""))
			loop = true;
		if (loop) {
			List<Prodaja> listItems = new ArrayList<>();
			try {
				listItems = BazaPodataka.dohvatiProdaje();
			} catch (BazaPodatakaException e) {
				System.out.println(e);
			}
			Prodaja prodaja = new Prodaja(artiklComboBox.getValue(), korisnikComboBox.getValue(),
					datumDatePicker.getValue());
			listItems.add(prodaja);
			try {
				BazaPodataka.pohraniNovuProdaju(prodaja);
			} catch (BazaPodatakaException e) {
				System.out.println(e);
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Podaci uspjesno uneseni!");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(errorText);
			alert.showAndWait();
		}
	}
}
