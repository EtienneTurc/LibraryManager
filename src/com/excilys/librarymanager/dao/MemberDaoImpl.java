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
		try {
			Connection connection = ConnectionManager.getConnection();

			String GetAllQuery = "SELECT * FROM Member ORDER BY lastName, firstName;";
			PreparedStatement preparedStatement = connection.prepareStatement(GetAllQuery);

			ResultSet rs = preparedStatement.executeQuery();

			List<Member> res = new ArrayList<Member>();
			while (rs.next()) {
				res.add(new Member(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription")));
			}
			return res;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};

	public Member getById(int id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String GetQuery = "SELECT * FROM Member WHERE id=?;";
			PreparedStatement preparedStatement = connection.prepareStatement(GetQuery);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new Member(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("address"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("subscription"));
			}

			throw new DaoException("Not found");

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	};

	public int create(String last_name, String first_name, String address, String mail, String phone)
			throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String CreateQuery = "INSERT INTO member(lastName, firstName, address, mail, phone, subscription) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(CreateQuery,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, last_name);
			preparedStatement.setString(2, first_name);
			preparedStatement.setString(3, address);
			preparedStatement.setString(4, mail);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, "BASIC");

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

	public void update(Member member) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String UpdateQuery = "UPDATE member SET lastName = ?, firstName = ?, address = ?, mail = ?, phone = ?, subscription = ? WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(UpdateQuery);
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
		}
	};

	public void delete(int id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			String DeleteQuery = "DELETE FROM member WHERE id = ?;";
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

			String CountQuery = "SELECT COUNT(id) AS count FROM member;";
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
