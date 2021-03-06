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
import com.excilys.librarymanager.models.Member;

/**
 * MemberDaoImpl
 */
public class MemberDaoImpl implements MemberDao, Serializable {

	private static final long serialVersionUID = -1235259573546322588L;
	private static MemberDaoImpl instance;

	private MemberDaoImpl() {
	}

	public static MemberDaoImpl getInstance() {
		if (instance == null) {
			instance = new MemberDaoImpl();
		}
		return instance;
	}

	public List<Member> getAll() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String GetAllQuery = "SELECT * FROM Member ORDER BY lastName, firstName;";
			preparedStatement = connection.prepareStatement(GetAllQuery);

			rs = preparedStatement.executeQuery();

			List<Member> res = new ArrayList<Member>();
			while (rs.next()) {
				res.add(new Member(rs.getInt("id"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription")));
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

	public Member getById(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String GetQuery = "SELECT * FROM Member WHERE id=?;";
			preparedStatement = connection.prepareStatement(GetQuery);
			preparedStatement.setInt(1, id);

			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new Member(rs.getInt("id"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));
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

	public int create(String last_name, String first_name, String address, String mail, String phone)
			throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();

			String CreateQuery = "INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES (?, ?, ?, ?, ?, ?);";
			preparedStatement = connection.prepareStatement(CreateQuery, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, last_name);
			preparedStatement.setString(2, first_name);
			preparedStatement.setString(3, address);
			preparedStatement.setString(4, mail);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, "BASIC");

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

	public void update(Member member) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();

			String UpdateQuery = "UPDATE member SET lastName = ?, firstName = ?, address = ?, mail = ?, phone = ?, subscription = ? WHERE id = ?;";
			preparedStatement = connection.prepareStatement(UpdateQuery);
			preparedStatement.setString(1, member.getLastName());
			preparedStatement.setString(2, member.getFirstName());
			preparedStatement.setString(3, member.getAddress());
			preparedStatement.setString(4, member.getMail());
			preparedStatement.setString(5, member.getPhone());
			preparedStatement.setString(6, member.getSubscription());
			preparedStatement.setInt(7, member.getId());

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

			String DeleteQuery = "DELETE FROM member WHERE id = ?;";
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

			String CountQuery = "SELECT COUNT(id) AS count FROM member;";
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
