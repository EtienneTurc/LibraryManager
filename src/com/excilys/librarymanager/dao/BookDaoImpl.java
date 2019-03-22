package com.excilys.librarymanager.dao;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.persistence.ConnectionManager;
import com.excilys.librarymanager.models.Book;

/**
 * BookDaoImpl
 */
public class BookDaoImpl implements BookDao, Serializable {

	private static BookDaoImpl instance;

	private BookDaoImpl() {
	}

	public static BookDaoImpl getInstance() {
		if (instance == null) {
			instance = new BookDaoImpl();
		}
		return instance;
	}

	public List<Book> getAll() throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String GetAllQuery = "SELECT * FROM BOOK;";
			PreparedStatement preparedStatement = connection.prepareStatement(GetAllQuery);

			ResultSet rs = preparedStatement.executeQuery();

			List<Book> res = new ArrayList<Book>();
			while (rs.next()) {
				res.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("isbn")));
			}
			return res;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};

	public Book getById(int id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String GetQuery = "SELECT * FROM BOOK WHERE id=(?);";
			PreparedStatement preparedStatement = connection.prepareStatement(GetQuery);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			Book b;
			if (rs.next()) {
				b = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("isbn"));
				return b;
			}

			throw new DaoException("Not found");
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};

	public int create(String title, String author, String isbn) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String CreateQuery = "INSERT INTO book(title, author, isbn) VALUES (?, ?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(CreateQuery,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, isbn);

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			int id = -1;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}

			connection.commit();

			return id;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};

	public void update(Book book) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String UpdateQuery = "UPDATE book SET title = ?, author = ?, isbn = ? WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(UpdateQuery);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getIsbn());
			preparedStatement.setInt(4, book.getId());

			preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};

	public void delete(int id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String DeleteQuery = "DELETE FROM book WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(DeleteQuery);
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};

	public int count() throws DaoException {

		try {
			Connection connection = ConnectionManager.getConnection();

			String CountQuery = "SELECT COUNT(id) AS count FROM book;";
			PreparedStatement preparedStatement = connection.prepareStatement(CountQuery);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rs.getInt("count");
			}
			return 0;

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};
}
