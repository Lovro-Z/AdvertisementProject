package hr.java.vjezbe;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class UnosPoslovnihKorisnikaController {

	@FXML
	private TextField nazivTextField;
	@FXML
	private TextField webTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telefonTextField;

	@FXML
	public void unesiPoslovnogKorisnika() {
		boolean loop = false;
		String errorText = "";
		if (nazivTextField.getText().trim().isEmpty())
			errorText += "Naziv je obavezan podatak!" + System.lineSeparator();
		if (webTextField.getText().trim().isEmpty())
			errorText += "Web je obavezan podatak!" + System.lineSeparator();
		if (emailTextField.getText().trim().isEmpty())
			errorText += "E-mail je obavezan podatak!" + System.lineSeparator();
		if (telefonTextField.getText().trim().isEmpty())
			errorText += "Telefon je obavezan podatak!" + System.lineSeparator();
		if (errorText.equals(""))
			loop = true;
		if (loop) {
			List<PoslovniKorisnik> listItems = new ArrayList<>();
			try {
				listItems = BazaPodataka.dohvatiPoslovneKorisnike();
			} catch (BazaPodatakaException e) {
				System.out.println(e);
			}
			OptionalLong maxId = listItems.stream().mapToLong(Entitet::getId).max();
			PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik(maxId.getAsLong() + 1, emailTextField.getText(),
					telefonTextField.getText(), nazivTextField.getText(), webTextField.getText());
			listItems.add(poslovniKorisnik);
			try {
				BazaPodataka.pohraniNovogPoslovnogKorisnika(poslovniKorisnik);
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
