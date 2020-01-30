package hr.java.vjezbe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StanController {

	ObservableList<Stan> obList;
	@FXML
	TableView<Stan> StanoviTable;
	@FXML
	private TableColumn<Stan, String> naslov;
	@FXML
	private TableColumn<Stan, String> opis;
	@FXML
	private TableColumn<Stan, BigDecimal> kvadratura;
	@FXML
	private TableColumn<Stan, BigDecimal> cijena;
	@FXML
	private TableColumn<Stan, Stanje> stanje;
	@FXML
	private TextField naslovTextField;
	@FXML
	private TextField opisTextField;
	@FXML
	private TextField kvadraturaTextField;
	@FXML
	private TextField cijenaTextField;

	public void initialize() {
		naslov.setCellValueFactory(new PropertyValueFactory<Stan, String>("Naslov"));
		opis.setCellValueFactory(new PropertyValueFactory<Stan, String>("Opis"));
		kvadratura.setCellValueFactory(new PropertyValueFactory<Stan, BigDecimal>("Kvadratura"));
		cijena.setCellValueFactory(new PropertyValueFactory<Stan, BigDecimal>("Cijena"));
		stanje.setCellValueFactory(new PropertyValueFactory<Stan, Stanje>("Stanje"));

		List<Stan> stanovi = new ArrayList<>();
		try {
			stanovi = BazaPodataka.dohvatiStanove();
		}catch(BazaPodatakaException e) {
			System.out.println(e);
		}
		obList = FXCollections.observableArrayList(stanovi);
		StanoviTable.setItems(obList);
	}
	
	@FXML
	public void pretragaStanova() {
		
		List<Stan> newList = obList.stream().filter(a -> {
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
			String search = kvadraturaTextField.getText();
			if (search == null || search.isEmpty())
				return true;
			if (a.getKvadratura().toString().equals(search))
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
		ObservableList<Stan> tableList = FXCollections.observableArrayList(newList);
		StanoviTable.setItems(tableList);
	}
}