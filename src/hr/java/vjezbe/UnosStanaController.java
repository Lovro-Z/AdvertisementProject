package hr.java.vjezbe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class UnosStanaController {
	@FXML
	private TextField naslovTextField;
	@FXML
	private TextField opisTextField;
	@FXML
	private TextField kvadraturaTextField;
	@FXML
	private ComboBox<Stanje> stanjeComboBox;
	@FXML
	private TextField cijenaTextField;

	public void initialize() {
		stanjeComboBox.setItems(FXCollections.observableArrayList(Stanje.values()));
	}

	@FXML
	public void unesiStan() {
		boolean loop = false;
		String errorText = "";
		if (naslovTextField.getText().trim().isEmpty())
			errorText += "Naslov je obavezan podatak!" + System.lineSeparator();
		if (opisTextField.getText().trim().isEmpty())
			errorText += "Opis je obavezan podatak!" + System.lineSeparator();
		if (kvadraturaTextField.getText().trim().isEmpty()) {
			errorText += "Kvadratura je obavezan podatak!" + System.lineSeparator();
		} else {
			try {
				BigDecimal kvadratura = new BigDecimal(kvadraturaTextField.getText());
				kvadratura.getClass();
			} catch (NumberFormatException e) {
				errorText += "Za kvadraturu je potrebno unijeti brojacanu vrijednost!" + System.lineSeparator();
			}
		}
		if (stanjeComboBox.getSelectionModel().isEmpty())
			errorText += "Stanje je obavezan podatak!" + System.lineSeparator();
		if (cijenaTextField.getText().trim().isEmpty()) {
			errorText += "Cijena je obavezan podatak!" + System.lineSeparator();
		} else {
			try {
				BigDecimal cijena = new BigDecimal(cijenaTextField.getText());
				cijena.getClass();
			} catch (NumberFormatException e) {
				errorText += "Za cijenu je potrebno unijeti brojacanu vrijednost!" + System.lineSeparator();
			}
		}
		if (errorText.equals(""))
			loop = true;
		if (loop) {
			List<Stan> listItems = new ArrayList<>();
			try {
				listItems = BazaPodataka.dohvatiStanove();
			} catch (BazaPodatakaException e) {
				System.out.println(e);
			}
			OptionalLong maxId = listItems.stream().mapToLong(Entitet::getId).max();
			Stan stan = new Stan(maxId.getAsLong() + 1, naslovTextField.getText(), opisTextField.getText(),
					new BigDecimal(cijenaTextField.getText()), stanjeComboBox.getValue(),
					Integer.parseInt(kvadraturaTextField.getText()));
			listItems.add(stan);
			try {
				BazaPodataka.pohraniNoviStan(stan);
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
