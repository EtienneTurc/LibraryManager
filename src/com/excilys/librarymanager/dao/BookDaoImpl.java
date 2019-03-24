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

	private static final long serialVersionUID = 3792939001092042951L;
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String GetAllQuery = "SELECT * FROM BOOK;";
			preparedStatement = connection.prepareStatement(GetAllQuery);

			rs = preparedStatement.executeQuery();

			List<Book> res = new ArrayList<Book>();
			while (rs.next()) {
				res.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("isbn")));
			}
			return res;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			;
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	};

	public Book getById(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String GetQuery = "SELECT * FROM BOOK WHERE id=(?);";
			preparedStatement = connection.prepareStatement(GetQuery);
			preparedStatement.setInt(1, id);

			rs = preparedStatement.executeQuery();
			Book b;
			if (rs.next()) {
				b = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("isbn"));
				return b;
			}

			throw new DaoException("Not found");
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			;
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	};

	public int create(String title, String author, String isbn) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String CreateQuery = "INSERT INTO book(title, author, isbn) VALUES (?, ?, ?);";
			preparedStatement = connection.prepareStatement(CreateQuery, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, isbn);

			preparedStatement.executeUpdate();

			rs = preparedStatement.getGeneratedKeys();
			int id = -1;
			if (rs.next()) {
				id = rs.getInt(1);
			}

			connection.commit();

			return id;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			;
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	};

	public void update(Book book) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();

			String UpdateQuery = "UPDATE book SET title = ?, author = ?, isbn = ? WHERE id = ?;";
			preparedStatement = connection.prepareStatement(UpdateQuery);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getIsbn());
			preparedStatement.setInt(4, book.getId());

			preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	};

	public void delete(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();

			String DeleteQuery = "DELETE FROM book WHERE id = ?;";
			preparedStatement = connection.prepareStatement(DeleteQuery);
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	};

	public int count() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String CountQuery = "SELECT COUNT(id) AS count FROM book;";
			preparedStatement = connection.prepareStatement(CountQuery);

			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rs.getInt("count");
			}
			return 0;

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			;
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	};
}
