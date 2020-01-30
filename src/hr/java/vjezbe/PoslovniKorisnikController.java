package hr.java.vjezbe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PoslovniKorisnikController {

	ObservableList<PoslovniKorisnik> obList;
	@FXML
	TableView<PoslovniKorisnik> PoslovniKorisnikTable;
	@FXML
	private TableColumn<PoslovniKorisnik, String> naziv;
	@FXML
	private TableColumn<PoslovniKorisnik, String> web;
	@FXML
	private TableColumn<PoslovniKorisnik, String> email;
	@FXML
	private TableColumn<PoslovniKorisnik, String> tel;
	@FXML
	private TextField nazivTextField;
	@FXML
	private TextField webTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telefonTextField;

	public void initialize() {
		naziv.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("Naziv"));
		web.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("Web"));
		email.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("Email"));
		tel.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("Telefon"));

		List<PoslovniKorisnik> poslovniKorisnici = new ArrayList<>();
		try {
			poslovniKorisnici = BazaPodataka.dohvatiPoslovneKorisnike();
		} catch (BazaPodatakaException e) {
			System.out.println(e);
		}
		obList = FXCollections.observableArrayList(poslovniKorisnici);
		PoslovniKorisnikTable.setItems(obList);
	}

	@FXML
	public void pretragaPoslovnihKorisnika() {

		List<PoslovniKorisnik> newList = obList.stream().filter(a -> {
			String search = nazivTextField.getText().toLowerCase();
			if (search == null || search.isEmpty()) {
				return true;
			}
			if (a.getNaziv().toLowerCase().contains(search)) {
				return true;
			}
			return false;
		}).filter(a -> {
			String search = webTextField.getText().toLowerCase();
			if (search == null || search.isEmpty()) {
				return true;
			}
			if (a.getWeb().toLowerCase().contains(search))
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
		ObservableList<PoslovniKorisnik> tableList = FXCollections.observableArrayList(newList);
		PoslovniKorisnikTable.setItems(tableList);
	}
}