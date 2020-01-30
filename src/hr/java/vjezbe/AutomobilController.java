package hr.java.vjezbe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AutomobilController {

	ObservableList<Automobil> obList;
	@FXML
	TableView<Automobil> AutomobiliTable;
	@FXML
	private TableColumn<Automobil, String> naslov;
	@FXML
	private TableColumn<Automobil, String> opis;
	@FXML
	private TableColumn<Automobil, BigDecimal> snaga;
	@FXML
	private TableColumn<Automobil, BigDecimal> cijena;
	@FXML
	private TableColumn<Automobil, Stanje> stanje;
	@FXML
	private TextField naslovTextField;
	@FXML
	private TextField opisTextField;
	@FXML
	private TextField snagaTextField;
	@FXML
	private TextField cijenaTextField;

	public void initialize() {
		naslov.setCellValueFactory(new PropertyValueFactory<Automobil, String>("Naslov"));
		opis.setCellValueFactory(new PropertyValueFactory<Automobil, String>("Opis"));
		snaga.setCellValueFactory(new PropertyValueFactory<Automobil, BigDecimal>("SnagaKs"));
		cijena.setCellValueFactory(new PropertyValueFactory<Automobil, BigDecimal>("Cijena"));
		stanje.setCellValueFactory(new PropertyValueFactory<Automobil, Stanje>("Stanje"));

		List<Automobil> automobili = new ArrayList<>();
		try {
			automobili = BazaPodataka.dohvatiAutomobile();
		} catch (BazaPodatakaException e) {
			System.out.println(e);
		}
		obList = FXCollections.observableArrayList(automobili);
		AutomobiliTable.setItems(obList);
	}

	@FXML
	public void pretragaAutomobila() {

		List<Automobil> newList = obList.stream().filter(a -> {
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
			String search = snagaTextField.getText();
			if (search == null || search.isEmpty())
				return true;
			if (a.getSnagaKs().toBigInteger().toString().equals(search))
				return true;
			return false;

		}).filter(a -> {
			String search = cijenaTextField.getText();
			if (search == null || search.isEmpty())
				return true;
			if (a.getCijena().toBigInteger().toString().equals(search))
				return true;
			return false;
		}).collect(Collectors.toList());
		ObservableList<Automobil> tableList = FXCollections.observableArrayList(newList);
		AutomobiliTable.setItems(tableList);
	}
}