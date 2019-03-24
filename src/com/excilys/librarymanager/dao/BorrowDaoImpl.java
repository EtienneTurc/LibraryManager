package com.excilys.librarymanager.dao;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.persistence.ConnectionManager;

import com.excilys.librarymanager.models.Borrow;
import com.excilys.librarymanager.models.Member;
import com.excilys.librarymanager.models.Book;

/**
 * MemberDaoImpl
 */
public class BorrowDaoImpl implements BorrowDao, Serializable {

	private static final long serialVersionUID = -2447131050549585563L;
	private static BorrowDaoImpl instance;

	private BorrowDaoImpl() {
	}

	public static BorrowDaoImpl getInstance() {
		if (instance == null) {
			instance = new BorrowDaoImpl();
		}
		return instance;
	}

	public List<Borrow> getAll() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String GetAllQuery = "SELECT e.id AS id, idMember, lastName, firstName, address, mail, phone, subscription, idBook, title, author, isbn, startBorrow, endBorrow FROM Borrow AS e INNER JOIN member ON member.id = e.idMember INNER JOIN book ON book.id = e.idBook ORDER BY endBorrow DESC;";
			preparedStatement = connection.prepareStatement(GetAllQuery);

			rs = preparedStatement.executeQuery();

			List<Borrow> res = new ArrayList<Borrow>();
			while (rs.next()) {
				Book book = new Book(rs.getInt("idBook"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"));
				Member member = new Member(rs.getInt("idMember"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));

				LocalDate end_borrow = null;
				if (rs.getDate("endBorrow") != null) {
					end_borrow = rs.getDate("endBorrow").toLocalDate();
				}

				res.add(new Borrow(rs.getInt("id"), member, book, rs.getDate("startBorrow").toLocalDate(), end_borrow));
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

	public List<Borrow> getListCurrent() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String GetAllCurrentQuery = "SELECT e.id AS id, idMember, lastName, firstName, address, mail, phone, subscription, idBook, title, author, isbn, startBorrow, endBorrow FROM borrow AS e INNER JOIN member ON member.id = e.idMember INNER JOIN book ON book.id = e.idBook WHERE endBorrow IS NULL;";
			preparedStatement = connection.prepareStatement(GetAllCurrentQuery);

			rs = preparedStatement.executeQuery();

			List<Borrow> res = new ArrayList<Borrow>();
			while (rs.next()) {
				Book book = new Book(rs.getInt("idBook"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"));
				Member member = new Member(rs.getInt("idMember"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));

				LocalDate end_borrow = null;
				if (rs.getDate("endBorrow") != null) {
					end_borrow = rs.getDate("endBorrow").toLocalDate();
				}

				res.add(new Borrow(rs.getInt("id"), member, book, rs.getDate("startBorrow").toLocalDate(), end_borrow));
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
	}

	public List<Borrow> getListCurrentByMember(int idMember) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String GetAllCurrentByMemberQuery = "SELECT e.id AS id, idMember, lastName, firstName, address, mail, phone, subscription, idBook, title, author, isbn, startBorrow, endBorrow FROM borrow AS e INNER JOIN member ON member.id = e.idMember INNER JOIN book ON book.id = e.idBook WHERE endBorrow IS NULL AND member.id = ?;";
			preparedStatement = connection.prepareStatement(GetAllCurrentByMemberQuery);
			preparedStatement.setInt(1, idMember);

			rs = preparedStatement.executeQuery();

			List<Borrow> res = new ArrayList<Borrow>();
			while (rs.next()) {
				Book book = new Book(rs.getInt("idBook"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"));
				Member member = new Member(rs.getInt("idMember"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));

				LocalDate end_borrow = null;
				if (rs.getDate("endBorrow") != null) {
					end_borrow = rs.getDate("endBorrow").toLocalDate();
				}

				res.add(new Borrow(rs.getInt("id"), member, book, rs.getDate("startBorrow").toLocalDate(), end_borrow));
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
	}

	public List<Borrow> getListCurrentByBook(int idBook) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String GetAllCurrentByBookQuery = "SELECT e.id AS id, idMember, lastName, firstName, address, mail, phone, subscription, idBook, title, author, isbn, startBorrow, endBorrow FROM borrow AS e INNER JOIN member ON member.id = e.idMember INNER JOIN book ON book.id = e.idBook WHERE endBorrow IS NULL AND book.id = ?;";
			preparedStatement = connection.prepareStatement(GetAllCurrentByBookQuery);
			preparedStatement.setInt(1, idBook);

			rs = preparedStatement.executeQuery();

			List<Borrow> res = new ArrayList<Borrow>();
			while (rs.next()) {
				Book book = new Book(rs.getInt("idBook"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"));
				Member member = new Member(rs.getInt("idMember"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));

				LocalDate end_borrow = null;
				if (rs.getDate("endBorrow") != null) {
					end_borrow = rs.getDate("endBorrow").toLocalDate();
				}

				res.add(new Borrow(rs.getInt("id"), member, book, rs.getDate("startBorrow").toLocalDate(), end_borrow));
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
	}

	public Borrow getById(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String GetQuery = "SELECT e.id AS id, idMember, lastName, firstName, address, mail, phone, subscription, idBook, title, author, isbn, startBorrow, endBorrow FROM borrow AS e INNER JOIN member ON member.id = e.idMember INNER JOIN book ON book.id = e.idBook WHERE e.id = ?;";
			preparedStatement = connection.prepareStatement(GetQuery);
			preparedStatement.setInt(1, id);

			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Book book = new Book(rs.getInt("idBook"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"));
				Member member = new Member(rs.getInt("idMember"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));
				LocalDate end_borrow = null;
				if (rs.getDate("endBorrow") != null) {
					end_borrow = rs.getDate("endBorrow").toLocalDate();
				}

				return new Borrow(rs.getInt("id"), member, book, rs.getDate("startBorrow").toLocalDate(), end_borrow);
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

	public int create(int idMember, int idBook, LocalDate startBorrow) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String CreateQuery = "INSERT INTO borrow(idMember, idBook, startBorrow, endBorrow) VALUES (?, ?, ?, NULL);";
			preparedStatement = connection.prepareStatement(CreateQuery, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idMember);
			preparedStatement.setInt(2, idBook);
			preparedStatement.setDate(3, Date.valueOf(startBorrow));

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

	public void update(Borrow borrow) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();

			String UpdateQuery = "UPDATE Borrow SET idMember = ?, idBook = ?, startBorrow = ?, endBorrow = ? WHERE id = ?;";
			preparedStatement = connection.prepareStatement(UpdateQuery);
			preparedStatement.setInt(1, borrow.getMember().getId());
			preparedStatement.setInt(2, borrow.getBook().getId());
			preparedStatement.setDate(3, Date.valueOf(borrow.getStartBorrow()));
			preparedStatement.setDate(4, Date.valueOf(borrow.getEndBorrow()));
			preparedStatement.setInt(5, borrow.getId());

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

			String CountQuery = "SELECT COUNT(id) AS count FROM borrow;";
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
