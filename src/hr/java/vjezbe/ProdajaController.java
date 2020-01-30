package hr.java.vjezbe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class ProdajaController {

	ObservableList<Prodaja> obList;
	@FXML
	TableView<Prodaja> prodajaTable;
	@FXML
	private TableColumn<Prodaja, Artikl> oglas;
	@FXML
	private TableColumn<Prodaja, Korisnik> korisnik;
	@FXML
	private TableColumn<Prodaja, LocalDate> datum;
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
		oglas.setCellValueFactory(new PropertyValueFactory<Prodaja, Artikl>("Artikl"));
		korisnik.setCellValueFactory(new PropertyValueFactory<Prodaja, Korisnik>("Korisnik"));
		datum.setCellValueFactory(new PropertyValueFactory<Prodaja, LocalDate>("DatumObjave"));

		List<Prodaja> prodaje = new ArrayList<>();
		try {
			prodaje = BazaPodataka.dohvatiProdaje();
		} catch (BazaPodatakaException e) {
			System.out.println(e);
		}
		obList = FXCollections.observableArrayList(prodaje);
		prodajaTable.setItems(obList);
		artiklComboBox.setItems(FXCollections.observableArrayList(BazaPodataka.dohvatiArtikle()));
		korisnikComboBox.setItems(FXCollections.observableArrayList(BazaPodataka.dohvatiKorisnike()));
	}

	@FXML
	public void pretragaProdaja() {

		List<Prodaja> newList = obList.stream().filter(p -> {
			Artikl search = artiklComboBox.getValue();
			if (search == null) {
				return true;
			}
			if (p.getArtikl().equals(search)) {
				return true;
			}
			return false;
		}).filter(p -> {
			Korisnik search = korisnikComboBox.getValue();
			if (search == null)
				return true;
			if (p.getKorisnik().getId().equals(search.getId()))
				return true;
			return false;
		}).filter(p -> {
			LocalDate datum = datumDatePicker.getValue();
			if (datum == null || datum.toString().isEmpty()) {
				return true;
			}
			if (p.getDatumObjave().equals(datum))
				return true;
			return false;
		}).collect(Collectors.toList());
		ObservableList<Prodaja> tableList = FXCollections.observableArrayList(newList);
		prodajaTable.setItems(tableList);
	}
}
