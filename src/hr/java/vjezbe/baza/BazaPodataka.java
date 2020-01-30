package hr.java.vjezbe.baza;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

public class BazaPodataka {

	private static final String DATABASE_PROPERTIES = "database.properties";
	private static final Logger logger = LoggerFactory.getLogger(BazaPodataka.class);

	private static Connection spajanjeNaBazu() throws SQLException, IOException {

		Properties properties = new Properties();

		properties.load(new FileReader(DATABASE_PROPERTIES));

		String dataBaseUrl = properties.getProperty("dataBaseUrl");
		String userName = properties.getProperty("username");
		String password = properties.getProperty("password");

		Connection connection = DriverManager.getConnection(dataBaseUrl, userName, password);

		return connection;
	}

	public static List<Stan> dohvatiStanove() throws BazaPodatakaException {
		List<Stan> listaStanova = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, kvadratura, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Stan'");
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				String opis = resultSet.getString("opis");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				Integer kvadratura = resultSet.getInt("kvadratura");
				String stanje = resultSet.getString("naziv");
				Stan newStan = new Stan(id, naslov, opis, cijena, Stanje.valueOf(stanje), kvadratura);
				listaStanova.add(newStan);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Pogreska s bazom";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaStanova;
	}

	public static List<Automobil> dohvatiAutomobile() throws BazaPodatakaException {
		List<Automobil> listaAutomobila = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, snaga, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Automobil'");
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				String opis = resultSet.getString("opis");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				BigDecimal snaga = resultSet.getBigDecimal("snaga");
				String stanje = resultSet.getString("naziv");
				Automobil newAutomobil = new Automobil(id, naslov, opis, cijena, Stanje.valueOf(stanje), snaga);
				listaAutomobila.add(newAutomobil);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Pogreska s bazom";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaAutomobila;
	}

	public static List<Usluga> dohvatiUsluge() throws BazaPodatakaException {
		List<Usluga> listaUsluga = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, stanje.naziv "
					+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
					+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Usluga'");
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				String opis = resultSet.getString("opis");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				String stanje = resultSet.getString("naziv");
				Usluga newUsluga = new Usluga(id, naslov, opis, cijena, Stanje.valueOf(stanje));
				listaUsluga.add(newUsluga);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Pogreska s bazom";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaUsluga;
	}

	public static List<PrivatniKorisnik> dohvatiPrivatneKorisnike() throws BazaPodatakaException {
		List<PrivatniKorisnik> listaPrivatnihKorisnika = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT distinct korisnik.id, ime, prezime, telefon, email "
					+ "FROM korisnik inner join tipkorisnika on tipkorisnika.id = korisnik.idtipkorisnika WHERE tipkorisnika.naziv = 'PrivatniKorisnik'");
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");
				String telefon = resultSet.getString("telefon");
				String email = resultSet.getString("email");
				PrivatniKorisnik newPrivatniKorisnik = new PrivatniKorisnik(id, email, telefon, ime, prezime);
				listaPrivatnihKorisnika.add(newPrivatniKorisnik);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Pogreska s bazom";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaPrivatnihKorisnika;
	}

	public static List<PoslovniKorisnik> dohvatiPoslovneKorisnike() throws BazaPodatakaException {
		List<PoslovniKorisnik> listaPoslovnihKorisnika = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct korisnik.id, korisnik.naziv, web, telefon, email "
							+ "FROM korisnik inner join tipkorisnika on tipkorisnika.id = korisnik.idtipkorisnika WHERE tipkorisnika.naziv = 'PoslovniKorisnik'");
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naziv = resultSet.getString("naziv");
				String web = resultSet.getString("web");
				String telefon = resultSet.getString("telefon");
				String email = resultSet.getString("email");
				PoslovniKorisnik newPoslovniKorisnik = new PoslovniKorisnik(id, email, telefon, naziv, web);
				listaPoslovnihKorisnika.add(newPoslovniKorisnik);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Pogreska s bazom";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaPoslovnihKorisnika;
	}

	public static List<Prodaja> dohvatiProdaje() throws BazaPodatakaException {

		List<Prodaja> listaProdaje = new ArrayList<>();

		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"select distinct korisnik.id as idKorisnika, tipKorisnika.naziv as tipKorisnika, \r\n"
							+ "korisnik.naziv as nazivKorisnika, web, email, telefon, \r\n"
							+ "korisnik.ime, korisnik.prezime, tipArtikla.naziv as tipArtikla,\r\n"
							+ "artikl.naslov, artikl.opis, artikl.cijena, artikl.kvadratura,\r\n"
							+ "artikl.snaga, stanje.naziv as stanje, prodaja.datumObjave, artikl.id as idArtikla\r\n"
							+ "from korisnik inner join \r\n"
							+ "tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika inner join\r\n"
							+ "prodaja on prodaja.idKorisnik = korisnik.id inner join\r\n"
							+ "artikl on artikl.id = prodaja.idArtikl inner join\r\n"
							+ "tipArtikla on tipArtikla.id = artikl.idTipArtikla inner join\r\n"
							+ "stanje on stanje.id = artikl.idStanje where 1=1");

			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Korisnik korisnik = null;
				if (resultSet.getString("tipKorisnika").equals("PrivatniKorisnik")) {
					korisnik = new PrivatniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("email"),
							resultSet.getString("telefon"), resultSet.getString("ime"), resultSet.getString("prezime"));
				} else if (resultSet.getString("tipKorisnika").equals("PoslovniKorisnik")) {
					korisnik = new PoslovniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("email"),
							resultSet.getString("telefon"), resultSet.getString("nazivKorisnika"),
							resultSet.getString("web"));
				}
				Artikl artikl = null;
				if (resultSet.getString("tipArtikla").equals("Automobil")) {
					artikl = new Automobil(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
							resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
							Stanje.valueOf(resultSet.getString("stanje")), resultSet.getBigDecimal("snaga"));
				} else if (resultSet.getString("tipArtikla").equals("Usluga")) {
					artikl = new Usluga(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
							resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
							Stanje.valueOf(resultSet.getString("stanje")));
				} else if (resultSet.getString("tipArtikla").equals("Stan")) {
					artikl = new Stan(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
							resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
							Stanje.valueOf(resultSet.getString("stanje")), resultSet.getInt("kvadratura"));
				}
				Prodaja novaProdaja = new Prodaja(artikl, korisnik,
						resultSet.getTimestamp("datumObjave").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				listaProdaje.add(novaProdaja);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			throw new BazaPodatakaException(poruka, e);
		}
		return listaProdaje;
	}

	public static List<Artikl> dohvatiArtikle() {
		List<Artikl> artikli = new ArrayList<>();
		try {
			artikli.addAll(dohvatiAutomobile());
			artikli.addAll(dohvatiStanove());
			artikli.addAll(dohvatiUsluge());
		} catch (BazaPodatakaException e) {
			System.out.println(e);
		}
		return artikli;
	}

	public static List<Korisnik> dohvatiKorisnike() {
		List<Korisnik> korisnici = new ArrayList<>();
		try {
			korisnici.addAll(dohvatiPrivatneKorisnike());
			korisnici.addAll(dohvatiPoslovneKorisnike());
		} catch(BazaPodatakaException e) {
			System.out.println(e);
		}
		return korisnici;
	}

	public static void pohraniNovuProdaju(Prodaja prodaja) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into prodaja(idKorisnik, idArtikl, datumObjave) " + "values (?, ?, ?);");
			preparedStatement.setString(1, prodaja.getKorisnik().getId().toString());
			preparedStatement.setString(2, prodaja.getArtikl().getId().toString());
			preparedStatement.setString(3, prodaja.getDatumObjave().toString());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void pohraniNoviStan(Stan stan) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Kvadratura, idStanje, idTipArtikla) "
							+ "values (?, ?, ?, ?, ?, 3);");
			preparedStatement.setString(1, stan.getNaslov());
			preparedStatement.setString(2, stan.getOpis());
			preparedStatement.setBigDecimal(3, stan.getCijena());
			preparedStatement.setInt(4, stan.getKvadratura());
			preparedStatement.setLong(5, (stan.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void pohraniNoviAutomobil(Automobil automobil) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Snaga, idStanje, idTipArtikla) "
							+ "values (?, ?, ?, ?, ?, 1);");
			preparedStatement.setString(1, automobil.getNaslov());
			preparedStatement.setString(2, automobil.getOpis());
			preparedStatement.setBigDecimal(3, automobil.getCijena());
			preparedStatement.setBigDecimal(4, automobil.getSnagaKs());
			preparedStatement.setLong(5, (automobil.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void pohraniNovuUslugu(Usluga usluga) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into artikl(Naslov, Opis, Cijena, idStanje, idTipArtikla) " + "values (?, ?, ?, ?, 2);");
			preparedStatement.setString(1, usluga.getNaslov());
			preparedStatement.setString(2, usluga.getOpis());
			preparedStatement.setBigDecimal(3, usluga.getCijena());
			preparedStatement.setLong(4, (usluga.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void pohraniNovogPrivatnogKorisnika(PrivatniKorisnik privatniKorisnik) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into korisnik(Ime, Prezime, Telefon, Email, idTipKorisnika) " + "values (?, ?, ?, ?, 1);");
			preparedStatement.setString(1, privatniKorisnik.getIme());
			preparedStatement.setString(2, privatniKorisnik.getPrezime());
			preparedStatement.setString(3, privatniKorisnik.getTelefon());
			preparedStatement.setString(4, privatniKorisnik.getEmail());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void pohraniNovogPoslovnogKorisnika(PoslovniKorisnik poslovniKorisnik) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into korisnik(Naziv, Web, Telefon, Email, idTipKorisnika) " + "values (?, ?, ?, ?, 2);");
			preparedStatement.setString(1, poslovniKorisnik.getNaziv());
			preparedStatement.setString(2, poslovniKorisnik.getWeb());
			preparedStatement.setString(3, poslovniKorisnik.getTelefon());
			preparedStatement.setString(4, poslovniKorisnik.getEmail());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}
}
