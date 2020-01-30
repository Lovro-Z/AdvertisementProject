package hr.java.vjezbe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UslugaController {

	ObservableList<Usluga> obList;
	@FXML
	TableView<Usluga> UslugeTable;
	@FXML
	private TableColumn<Usluga, String> naslov;
	@FXML
	private TableColumn<Usluga, String> opis;
	@FXML
	private TableColumn<Usluga, BigDecimal> cijena;
	@FXML
	private TableColumn<Usluga, Stanje> stanje;
	@FXML
	private TextField naslovTextField;
	@FXML
	private TextField opisTextField;
	@FXML
	private TextField cijenaTextField;

	public void initialize() {
		naslov.setCellValueFactory(new PropertyValueFactory<Usluga, String>("Naslov"));
		opis.setCellValueFactory(new PropertyValueFactory<Usluga, String>("Opis"));
		cijena.setCellValueFactory(new PropertyValueFactory<Usluga, BigDecimal>("Cijena"));
		stanje.setCellValueFactory(new PropertyValueFactory<Usluga, Stanje>("Stanje"));

		List<Usluga> usluge = new ArrayList<>();
		try {
			usluge = BazaPodataka.dohvatiUsluge();
		} catch (BazaPodatakaException e) {
			System.out.println(e);
		}
		obList = FXCollections.observableArrayList(usluge);
		UslugeTable.setItems(obList);
	}

	@FXML
	public void pretragaUsluga() {

		List<Usluga> newList = obList.stream().filter(a -> {
			String search = naslovTextField.getText().toLowerCase();
			if (search == null || search.isEmpty()) {
				return true;
			}
			if (a.getNaslov().toLowerCase().contains(search)) {
				return true;
			}
			return false;
		}).filter(a -> {
			String search = opisTextField.getText().toLowerCase();
			if (search == null || search.isEmpty()) {
				return true;
			}
			if (a.getOpis().toLowerCase().contains(search))
				return true;
			return false;
		}).filter(a -> {
			String search = cijenaTextField.getText();
			if (search == null || search.isEmpty())
				return true;
			if (a.getCijena().toString().equals(search))
				return true;
			return false;
		}).collect(Collectors.toList());
		ObservableList<Usluga> tableList = FXCollections.observableArrayList(newList);
		UslugeTable.setItems(tableList);
	}
}
