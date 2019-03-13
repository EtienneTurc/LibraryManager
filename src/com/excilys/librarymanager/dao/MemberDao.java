package com.excilys.librarymanager.dao;

import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.models.Member;

public interface MemberDao {
	public List<Member> getAll() throws DaoException;

	public Member getById(int id) throws DaoException;

	public int create(String last_name, String first_name, String address, String mail, String phone)
			throws DaoException;

	public void update(Member member) throws DaoException;

	public void delete(int id) throws DaoException;

	public int count() throws DaoException;
}
