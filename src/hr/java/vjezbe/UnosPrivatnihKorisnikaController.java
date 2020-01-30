package hr.java.vjezbe;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class UnosPrivatnihKorisnikaController {

	@FXML
	private TextField imeTextField;
	@FXML
	private TextField prezimeTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telefonTextField;

	@FXML
	public void unesiPrivatnogKorisnika() {
		boolean loop = false;
		String errorText = "";
		if (imeTextField.getText().trim().isEmpty())
			errorText += "Ime je obavezan podatak!" + System.lineSeparator();
		if (prezimeTextField.getText().trim().isEmpty())
			errorText += "Prezime je obavezan podatak!" + System.lineSeparator();
		if (emailTextField.getText().trim().isEmpty())
			errorText += "E-mail je obavezan podatak!" + System.lineSeparator();
		if (telefonTextField.getText().trim().isEmpty())
			errorText += "Telefon je obavezan podatak!" + System.lineSeparator();
		if (errorText.equals(""))
			loop = true;
		if (loop) {
			List<PrivatniKorisnik> listItems = new ArrayList<>();
			try {
				listItems = BazaPodataka.dohvatiPrivatneKorisnike();
			} catch (BazaPodatakaException e) {
				System.out.println(e);
			}
			OptionalLong maxId = listItems.stream().mapToLong(Entitet::getId).max();
			PrivatniKorisnik privatniKorisnik = new PrivatniKorisnik(maxId.getAsLong() + 1, emailTextField.getText(),
					telefonTextField.getText(), imeTextField.getText(), prezimeTextField.getText());
			listItems.add(privatniKorisnik);
			try {
				BazaPodataka.pohraniNovogPrivatnogKorisnika(privatniKorisnik);
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
