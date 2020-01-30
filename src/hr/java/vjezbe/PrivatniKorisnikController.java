package hr.java.vjezbe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrivatniKorisnikController {

	ObservableList<PrivatniKorisnik> obList;
	@FXML
	TableView<PrivatniKorisnik> PrivatniKorisnikTable;
	@FXML
	private TableColumn<PrivatniKorisnik, String> ime;
	@FXML
	private TableColumn<PrivatniKorisnik, String> prezime;
	@FXML
	private TableColumn<PrivatniKorisnik, String> email;
	@FXML
	private TableColumn<PrivatniKorisnik, String> tel;
	@FXML
	private TextField imeTextField;
	@FXML
	private TextField prezimeTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telefonTextField;

	public void initialize() {
		ime.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("Ime"));
		prezime.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("Prezime"));
		email.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("Email"));
		tel.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("Telefon"));

		List<PrivatniKorisnik> privatniKorisnici = new ArrayList<>();
		try {
			privatniKorisnici = BazaPodataka.dohvatiPrivatneKorisnike();
		} catch (BazaPodatakaException e) {
			System.out.println(e);
		}
		obList = FXCollections.observableArrayList(privatniKorisnici);
		PrivatniKorisnikTable.setItems(obList);
	}

	@FXML
	public void pretragaPrivatnihKorisnika() {

		List<PrivatniKorisnik> newList = obList.stream().filter(a -> {
			String search = imeTextField.getText().toLowerCase();
			if (search == null || search.isEmpty()) {
				return true;
			}
			if (a.getIme().toLowerCase().contains(search)) {
				return true;
			}
			return false;
		}).filter(a -> {
			String search = prezimeTextField.getText().toLowerCase();
			if (search == null || search.isEmpty()) {
				return true;
			}
			if (a.getPrezime().toLowerCase().contains(search))
				return true;
			return false;
		}).filter(a -> {
			String search = emailTextField.getText().toLowerCase();
			if (search == null || search.isEmpty())
				return true;
			if (a.getEmail().toLowerCase().contains(search))
				return true;
			return false;

		}).filter(a -> {
			String search = telefonTextField.getText();
			if (search == null || search.isEmpty())
				return true;
			if (a.getTelefon().contains(search))
				return true;
			return false;
		}).collect(Collectors.toList());
		ObservableList<PrivatniKorisnik> tableList = FXCollections.observableArrayList(newList);
		PrivatniKorisnikTable.setItems(tableList);
	}
}
