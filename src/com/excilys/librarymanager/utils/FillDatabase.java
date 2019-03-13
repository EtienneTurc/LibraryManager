package com.excilys.librarymanager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.DeleteDbFiles;

import com.excilys.librarymanager.persistence.ConnectionManager;

public class FillDatabase {

	public static void main(String[] args) throws Exception {
		try {
			DeleteDbFiles.execute("~", "libraryManagerDatabase", true);
			insertWithPreparedStatement();
			System.out.println("finished");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void insertWithPreparedStatement() throws SQLException {
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement createPreparedStatement = null;

		List<String> createTablesQueries = new ArrayList<>();
		createTablesQueries.add(
				"CREATE TABLE IF NOT EXISTS livre(id INT primary key auto_increment, title VARCHAR(100), author VARCHAR(100), isbn VARCHAR(20))");
		createTablesQueries.add(
				"CREATE TABLE IF NOT EXISTS membre(id INT primary key auto_increment, lastName VARCHAR(100), firstName VARCHAR(100), address TEXT, mail VARCHAR(100), phone VARCHAR(30), subscription ENUM('BASIC', 'PREMIUM', 'VIP') DEFAULT 'BASIC')");
		createTablesQueries.add(
				"CREATE TABLE IF NOT EXISTS emprunt(id INT primary key auto_increment, idMember INT, idBook INT, startBorrow DATETIME, endBorrow DATETIME)");

		try {
			connection.setAutoCommit(false);

			for (String createQuery : createTablesQueries) {
				createPreparedStatement = connection.prepareStatement(createQuery);
				createPreparedStatement.executeUpdate();
				createPreparedStatement.close();
			}

			// Ajout de plusieurs enregistrement avec Statement

			Statement stmt = connection.createStatement();
			stmt.execute(
					"INSERT INTO book(title, author, isbn) VALUES('Les oiseaux migrateurs', 'Patrick FICHTER', '978-2817704876')");
			stmt.execute(
					"INSERT INTO book(title, author, isbn) VALUES('Le génie des oiseaux', 'Jennifer ACKERMAN', '978-2501124881')");
			stmt.execute(
					"INSERT INTO book(title, author, isbn) VALUES('Dans la forêt', 'Jean HEGLAND', '978-2351781425')");
			stmt.execute(
					"INSERT INTO book(title, author, isbn) VALUES('Les Séries télé pour les nuls', 'Marjolaine BOUTET', '978-2754009126')");
			stmt.execute(
					"INSERT INTO book(title, author, isbn) VALUES('L''univers des séries TV', 'Jurgen MULLER', '978-3836542746')");
			stmt.execute(
					"INSERT INTO book(title, author, isbn) VALUES('Tartes pour tous', 'Jean-François PIÈGE', '978-2017042778')");
			stmt.execute(
					"INSERT INTO book(title, author, isbn) VALUES('Les enquêtes de l''inspecteur Higgins - Tome 32 : Jack l''éventreur, le retour', 'Christian JACQ', '978-2374481395')");
			stmt.execute(
					"INSERT INTO book(title, author, isbn) VALUES('M, le bord de l''abîme', 'Bernard MINIER', '978-2374481210')");
			stmt.execute(
					"INSERT INTO book(title, author, isbn) VALUES('Van Gogh, Maître de la couleur', 'Gérard DENIZEAU', '978-2035941800')");
			stmt.execute(
					"INSERT INTO book(title, author, isbn) VALUES('Catalogue vasarely/partage des formes/catalogue de l''exposition', 'Michel GAUTHIER', '978-2844268396')");

			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('CHERIF', 'Kader', '2 rue Sadi Carnot', 'kcherif@mail.com', '0243585672', 'PREMIUM')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('MONGEVILLE', 'Antoine', '8 rue Frédéric Chopin', 'amongeville@mail.com', '0426813579', 'VIP')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('RENOIR', 'Candice', '26 rue de l''Epeule', 'crenoir@mail.com', '0485724694', 'BASIC')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('MAGELLAN', 'Simon', '48 rue du Château', 'smagellan@mail.com', '0423496545', 'BASIC')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('MARLEAU', 'Sylvie', '20 rue de l''Aigle', 'smarleau@mail.com', '', 'BASIC')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('BALTHAZAR', 'Raphaël', '42 rue du Clair Bocage', 'rbalthazar@mail.com', '0654821968', 'PREMIUM')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('CHASSAGNE', 'Florence', '28 rue Michel Ange', 'fchassagne@mail.com', '0154829537', 'VIP')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('WEISS', 'Lauren', '49 rue St Ferréol', 'lweiss@mail.com', '0349302501', 'PREMIUM')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('LEBOWITZ', 'Paule', '75 avenue Jean Portalis', 'plebowitz@mail.com', '0165483283', 'BASIC')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('MUNCHOWSKI', 'Gabrielle', '92 rue Gustave Eiffel', 'gmunchowski@mail.com', '0157499347', 'PREMIUM')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('CAÏN', 'Frédéric', '43 route de Lyon', 'fcain@mail.com', '0629831280', 'BASIC')");
			stmt.execute(
					"INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES('CASSANDRE', 'Florence', '63 rue Joseph Vernet', 'fcassandre@mail.com', '0721137437', 'BASIC')");

			stmt.execute(
					"INSERT INTO borrow(idMember, idBook, startBorrow, endBorrow) VALUES('2', '1', '2019-02-12', '2019-02-19')");
			stmt.execute(
					"INSERT INTO borrow(idMember, idBook, startBorrow, endBorrow) VALUES('2', '2', '2019-03-07', '2019-03-10')");
			stmt.execute(
					"INSERT INTO borrow(idMember, idBook, startBorrow, endBorrow) VALUES('5', '3', '2019-03-01', NULL)");
			stmt.execute(
					"INSERT INTO borrow(idMember, idBook, startBorrow, endBorrow) VALUES('7', '10', '2019-03-02', '2019-03-05')");
			stmt.execute(
					"INSERT INTO borrow(idMember, idBook, startBorrow, endBorrow) VALUES('5', '2', '2019-03-02', NULL)");
			stmt.execute(
					"INSERT INTO borrow(idMember, idBook, startBorrow, endBorrow) VALUES('4', '8', '2019-03-11', NULL)");

			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
}
