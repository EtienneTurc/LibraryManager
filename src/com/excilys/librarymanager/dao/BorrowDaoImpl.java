package com.excilys.librarymanager.dao;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalDateTime;

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
		try {
			Connection connection = ConnectionManager.getConnection();

			String GetAllQuery = "SELECT e.id AS id, idMembrer, lastName, firstName, address, mail, phone, subscription, idBook, title, author, isbn, startBorrow, endBorrow FROM Borrow AS e INNER JOIN member ON member.id = e.idMember INNER JOIN book ON book.id = e.idBook ORDER BY endBorrow DESC;";
			PreparedStatement preparedStatement = connection.prepareStatement(GetAllQuery);

			ResultSet rs = preparedStatement.executeQuery();

			List<Borrow> res = new ArrayList<Borrow>();
			while (rs.next()) {
				Book book = new Book(rs.getInt("idBook"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"));
				Member member = new Member(rs.getInt("idMember"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));
				res.add(new Borrow(rs.getInt("id"), member, book, rs.getDate("startBorrow").toLocalDate(),
						rs.getDate("endBorrow").toLocalDate()));
			}
			return res;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};

	public List<Borrow> getListCurrent() throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String GetAllCurrentQuery = "SELECT e.id AS id, idMember, lastName, firstName, address, mail, phone, subscription, idBook, title, author, isbn, startBorrow, endBorrow FROM borrow AS e INNER JOIN member ON member.id = e.idMember INNER JOIN book ON book.id = e.idBook WHERE endBorrow IS NULL;";
			PreparedStatement preparedStatement = connection.prepareStatement(GetAllCurrentQuery);

			ResultSet rs = preparedStatement.executeQuery();

			List<Borrow> res = new ArrayList<Borrow>();
			while (rs.next()) {
				Book book = new Book(rs.getInt("idBook"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"));
				Member member = new Member(rs.getInt("idMember"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));
				res.add(new Borrow(rs.getInt("id"), member, book, rs.getDate("startBorrow").toLocalDate(),
						rs.getDate("endBorrow").toLocalDate()));
			}
			return res;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List<Borrow> getListCurrentByMember(int idMember) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String GetAllCurrentByMemberQuery = "SELECT e.id AS id, idMember, lastName, firstName, address, mail, phone, subscription, idBook, title, author, isbn, startBorrow, endBorrow FROM borrow AS e INNER JOIN member ON member.id = e.idMember INNER JOIN book ON book.id = e.idBook WHERE endBorrow IS NULL AND member.id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(GetAllCurrentByMemberQuery);
			preparedStatement.setInt(1, idMember);

			ResultSet rs = preparedStatement.executeQuery();

			List<Borrow> res = new ArrayList<Borrow>();
			while (rs.next()) {
				Book book = new Book(rs.getInt("idBook"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"));
				Member member = new Member(rs.getInt("idMember"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));
				res.add(new Borrow(rs.getInt("id"), member, book, rs.getDate("startBorrow").toLocalDate(),
						rs.getDate("endBorrow").toLocalDate()));
			}
			return res;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List<Borrow> getListCurrentByLivre(int idBook) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String GetAllCurrentByBookQuery = "SELECT e.id AS id, idMember, lastName, firstName, address, mail, phone, subscription, idBook, title, author, isbn, startBorrow, endBorrow FROM borrow AS e INNER JOIN member ON member.id = e.idMember INNER JOIN book ON book.id = e.idBook WHERE endBorrow IS NULL AND book.id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(GetAllCurrentByBookQuery);
			preparedStatement.setInt(1, idBook);

			ResultSet rs = preparedStatement.executeQuery();

			List<Borrow> res = new ArrayList<Borrow>();
			while (rs.next()) {
				Book book = new Book(rs.getInt("idBook"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"));
				Member member = new Member(rs.getInt("idMember"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));
				res.add(new Borrow(rs.getInt("id"), member, book, rs.getDate("startBorrow").toLocalDate(),
						rs.getDate("endBorrow").toLocalDate()));
			}
			return res;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public Borrow getById(int id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String GetQuery = "SELECT e.id AS id, idMember, lastName, firstName, address, mail, phone, subscription, idBook, title, author, isbn, startBorrow, endBorrow FROM borrow AS e INNER JOIN member ON member.id = e.idMember INNER JOIN book ON book.id = e.idBook WHERE e.id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(GetQuery);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Book book = new Book(rs.getInt("idBook"), rs.getString("title"), rs.getString("author"),
						rs.getString("isbn"));
				Member member = new Member(rs.getInt("idMember"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));
				return new Borrow(rs.getInt("id"), member, book, rs.getDate("startBorrow").toLocalDate(),
						rs.getDate("endBorrow").toLocalDate());
			}

			throw new DaoException("Not found");
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};

	public int create(int idMember, int idBook, LocalDate startBorrow) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String CreateQuery = "INSERT INTO borrow(idMember, idBook, startBorrow, endBorrow) VALUES (?, ?, ?, NULL);";
			PreparedStatement preparedStatement = connection.prepareStatement(CreateQuery,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idMember);
			preparedStatement.setInt(2, idBook);
			preparedStatement.setDate(3, Date.valueOf(startBorrow));

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

	public void update(Borrow borrow) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String UpdateQuery = "UPDATE Borrow SET idMember = ?, idBook = ?, startBorrow = ?, endBorrow = ?, WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(UpdateQuery);
			preparedStatement.setInt(1, borrow.getMember().getId());
			preparedStatement.setInt(2, borrow.getBook().getId());
			preparedStatement.setDate(3, Date.valueOf(borrow.getStartBorrow()));
			preparedStatement.setDate(4, Date.valueOf(borrow.getEndBorrow()));
			preparedStatement.setInt(5, borrow.getId());

			preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};

	public int count() throws DaoException {

		try {
			Connection connection = ConnectionManager.getConnection();

			String CountQuery = "SELECT COUNT(id) AS count FROM borrow;";
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
